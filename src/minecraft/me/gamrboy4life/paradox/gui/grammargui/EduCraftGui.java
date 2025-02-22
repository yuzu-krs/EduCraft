package me.gamrboy4life.paradox.gui.grammargui;

import org.lwjgl.input.Mouse;

import me.gamrboy4life.paradox.module.describe.EduCraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class EduCraftGui extends GuiScreen {

    private static final ResourceLocation IMAGE = new ResourceLocation("yuzuclient/TABGUI_EduCraft.png");

    // 画像サイズ
    private int imageWidth = 384;
    private int imageHeight = 1512;

    // 表示領域
    private int displayWidth = 384; // 表示領域の幅
    private int displayHeight = 216; // 表示領域の高さ (16:9)
    private int scrollY = 0;
    private int scrollBarWidth = 10;
    private boolean isDragging = false;
    private int dragOffsetY = 0;
    private GuiButton closeButton;
    
    private EduCraft module;
    public EduCraftGui(EduCraft module) {
    	this.module=module;
    }
    
    

    @Override
    public void initGui() {
        int centerX = (this.width - displayWidth) / 2;
        int centerY = (this.height - displayHeight) / 2;

        // 画像の下部、ホットバーの位置に閉じるボタンを配置
        int buttonWidth = 80;
        int buttonHeight = 20;
        int buttonX = centerX + (displayWidth - buttonWidth) / 2;
        int buttonY = this.height - 25; // 画面下部に配置
        closeButton = new GuiButton(0, buttonX, buttonY, buttonWidth, buttonHeight, "閉じる");
        this.buttonList.add(closeButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            this.mc.displayGuiScreen(null);
            if(module!=null) {
            	module.falseToggled();
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        mc.getTextureManager().bindTexture(IMAGE);

        int centerX = (this.width - displayWidth) / 2;
        int centerY = (this.height - displayHeight) / 2 - 10;

        // スクロール位置を考慮して画像を描画
        drawModalRectWithCustomSizedTexture(centerX, centerY, 0, scrollY, displayWidth, displayHeight, imageWidth, imageHeight);

        // スクロールバーの描画
        drawScrollBar(centerX + displayWidth + 5, centerY, displayHeight, scrollY, imageHeight - displayHeight);

        // ボタンの描画
        closeButton.drawButton(mc, mouseX, mouseY);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void drawScrollBar(int x, int y, int height, int scrollY, int maxScrollY) {
        int barHeight = Math.max(20, height * height / (maxScrollY + height));
        int barY = y + (height - barHeight) * scrollY / maxScrollY;
        
        // スクロールバーの背景を描画
        drawRect(x - 1, y - 1, x + scrollBarWidth + 1, y + height + 1, 0xFF000000); // 黒の背景縁
        drawRect(x, y, x + scrollBarWidth, y + height, 0xFF555555); // グレーの背景
        
        // スクロールバーを描画
        drawRect(x, barY, x + scrollBarWidth, barY + barHeight, 0xFFAAAAAA);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (keyCode == 1) { // ESCキーでGUIを閉じる
            this.mc.displayGuiScreen(null);
        	module.falseToggled();
        } else if (keyCode == 200) { // UPキー
            scrollY = Math.max(scrollY - 10, 0);
        } else if (keyCode == 208) { // DOWNキー
            scrollY = Math.min(scrollY + 10, imageHeight - displayHeight);
        }
    }

    @Override
    public void handleMouseInput() throws java.io.IOException {
        super.handleMouseInput();
        int scrollAmount = Mouse.getEventDWheel();
        if (scrollAmount != 0) {
            scrollY = Math.max(0, Math.min(scrollY - scrollAmount / 120 * 10, imageHeight - displayHeight));
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws java.io.IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        int centerX = (this.width - displayWidth) / 2;
        int centerY = (this.height - displayHeight) / 2 - 10;
        int scrollBarX = centerX + displayWidth + 5;

        int barHeight = Math.max(20, displayHeight * displayHeight / (imageHeight - displayHeight + displayHeight));
        int barY = centerY + (displayHeight - barHeight) * scrollY / (imageHeight - displayHeight);

        if (mouseButton == 0 && mouseX >= scrollBarX && mouseX <= scrollBarX + scrollBarWidth &&
            mouseY >= barY && mouseY <= barY + barHeight) {
            isDragging = true;
            dragOffsetY = mouseY - barY;
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        isDragging = false;
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        if (isDragging) {
            int centerX = (this.width - displayWidth) / 2;
            int centerY = (this.height - displayHeight) / 2 - 10;
            int barHeight = Math.max(20, displayHeight * displayHeight / (imageHeight - displayHeight + displayHeight));

            int barY = mouseY - dragOffsetY;
            int maxBarY = centerY + displayHeight - barHeight;
            barY = Math.max(centerY, Math.min(barY, maxBarY));

            scrollY = (barY - centerY) * (imageHeight - displayHeight) / (displayHeight - barHeight);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}