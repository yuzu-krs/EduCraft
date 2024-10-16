package me.gamrboy4life.paradox.gui;

import java.util.HashSet;

import com.google.common.collect.Sets;

import de.Hero.clickgui.ClickGUI;
import me.gamrboy4life.paradox.Sotuken;
import me.gamrboy4life.paradox.module.Module;
import me.gamrboy4life.paradox.module.status.Panic;
import me.gamrboy4life.paradox.utils.ColorUtils;
import me.gamrboy4life.paradox.utils.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class GuiIngameHook extends GuiIngame {

    public GuiIngameHook(Minecraft mcIn) {
        super(mcIn);
    }

    public void renderGameOverlay(float p_175180_1_) {
        super.renderGameOverlay(p_175180_1_);

        if (!mc.gameSettings.showDebugInfo) {
			mc.getTextureManager().bindTexture(new ResourceLocation("yuzuclient/EduCraft.png"));
			GuiScreen.drawModalRectWithCustomSizedTexture(1, 4, 1, 5, 80,23, 80,23);
            
            
            renderArrayList();
        }
    }

    private HashSet<String> modBlackList = Sets.newHashSet(ClickGUI.class.getName(), Panic.class.getName());

    public boolean isModBlackListed(Module m) {
        return modBlackList.contains(m.getClass().getName());
    }

    private void renderArrayList() {
        int yCount = 0;
        int index = 0;
        long x = 0;
        for (Module m : Sotuken.instance.moduleManager.getModules()) {
            m.onRender();

            ScaledResolution sr = new ScaledResolution(mc);
            double offset = yCount * (Wrapper.fr.FONT_HEIGHT + 6);

            if (m.isToggled()) {
                if (!modBlackList.contains(m.getClass().getName())) {
                    int rainbowColor = ColorUtils.rainbowEffect(index + x * 200000000L, 1.0f).getRGB();

                    // Draw gray background
                    Gui.drawRect(sr.getScaledWidth() - Wrapper.fr.getStringWidth(m.getName()) - 15, (int) offset, sr.getScaledWidth(), (int) (6 + Wrapper.fr.FONT_HEIGHT + offset), 0x80000000);

                    // Draw blurred rainbow border
                    drawBlurredRainbowBorder(sr.getScaledWidth() - Wrapper.fr.getStringWidth(m.getName()) - 15, (int) offset, sr.getScaledWidth(), (int) (6 + Wrapper.fr.FONT_HEIGHT + offset), index);

                    Wrapper.fr.drawString("- " + m.getName(), sr.getScaledWidth() - Wrapper.fr.getStringWidth(m.getName()) - 13, 4 + offset, 0xFFFFFFFF);
                    yCount++;
                    index++;
                    x++;
                }
            }
        }
    }

    private void drawBlurredRainbowBorder(int left, int top, int right, int bottom, int index) {
        //int color = ColorUtils.rainbowEffect(index * 200000000L, 1.0f).darker().getRGB() & 0x7FFFFFFF; // Make color darker and semi-transparent
    	int color = 0xBFFFFFFF;
    	
        // Draw top border
        Gui.drawRect(left - 1, top - 1, right + 1, top, color);
        // Draw bottom border
        Gui.drawRect(left - 1, bottom - 1, right + 1, bottom, color);
        // Draw left border
        Gui.drawRect(left - 1, top, left, bottom, color);
        // Draw right border
        Gui.drawRect(right, top, right + 1, bottom, color);
    }
}