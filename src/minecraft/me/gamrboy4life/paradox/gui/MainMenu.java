package me.gamrboy4life.paradox.gui;

import me.gamrboy4life.paradox.Sotuken;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class MainMenu extends GuiScreen{

    private boolean useFirstBackground = true;

    public MainMenu() {

    }

    public void initGui() {
    	Sotuken.getDiscordRP().update("Idle", "Main Menu");
    }

    public void drawScreen(int mouseX,int mouseY,float partialticks) {
        mc.getTextureManager().bindTexture(new ResourceLocation("yuzuclient/EduCraft-Main.png"));
        this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0,this.width, this.height, this.width, this.height);
        this.drawGradientRect(0, height-120, width, height, 0x00000000, 0xff000000);

        String[] buttons= {"シングルプレイ","マルチプレイ","設定","言語","終了"};
        int count=0;
        for(String name:buttons) {
            float x=(width/buttons.length)*count+(width/buttons.length)/2f+8-mc.fontRendererObj.getStringWidth(name)/2f;
            float y=height-20;

            boolean hovered=(mouseX>=x&&mouseY>=y&&mouseX<x+mc.fontRendererObj.getStringWidth(name)&&mouseY<y+mc.fontRendererObj.FONT_HEIGHT);

            this.drawCenteredString(fontRendererObj, name, (width/buttons.length)*count+(width/buttons.length/2f+8), y,hovered?0xFFE600:-1 );
            
//            FontUtil.normal.drawCenteredString(name, (width/buttons.length)*count+(width/buttons.length/2f+8), y,hovered?0xFFE600:-1 );
            count++;
        }


        GlStateManager.pushMatrix();
        GlStateManager.translate(width/2f,height/2f,0);
        GlStateManager.scale(2,2,1);
        GlStateManager.translate(-(width/2f),-(height/2f),0);
        this.drawCenteredString(mc.fontRendererObj, "ver-1.0 by Sotuken", width/2f, height/1.8f- mc.fontRendererObj.FONT_HEIGHT/2f, -1);
        GlStateManager.popMatrix();

    }

    public void mouseClicked(int mouseX,int mouseY,int button) {
        String[] buttons= {"シングルプレイ","マルチプレイ","設定","言語","終了"};

        int count=0;
        for(String name : buttons) {
            float x = (width / buttons.length) * count + (width / buttons.length) / 2f + 8
                      - mc.fontRendererObj.getStringWidth(name) / 2f;
            float y = height - 20;

            if(mouseX >= x && mouseY >= y && mouseX < x + mc.fontRendererObj.getStringWidth(name) && mouseY < y + mc.fontRendererObj.FONT_HEIGHT) {
                if(name.equals("シングルプレイ")) {
                    mc.displayGuiScreen(new GuiSelectWorld(this));
                } else if(name.equals("マルチプレイ")) {
                    mc.displayGuiScreen(new GuiMultiplayer(this));
                } else if(name.equals("設定")) {
                    mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
                } else if(name.equals("言語")) {
                    mc.displayGuiScreen(new GuiLanguage(this, mc.gameSettings, mc.getLanguageManager()));
                } else if(name.equals("終了")) {
                    mc.shutdown();
                }
                Minecraft.getMinecraft().getSoundHandler().playSound(
                		PositionedSoundRecord.create(new ResourceLocation("random.click"),1.0F )
        		);
            }
            count++;
        }
    }
}

