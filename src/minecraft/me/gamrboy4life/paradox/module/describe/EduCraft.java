package me.gamrboy4life.paradox.module.describe;


import me.gamrboy4life.paradox.gui.grammargui.EduCraftGui;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.Minecraft;

public class EduCraft extends Module {

    public EduCraft() {
        super("使い方", 0, Category.DESCRIBE);
    }

    @Override
    public void onEnable() {
    	if(isToggled()) {
    		Minecraft.getMinecraft().displayGuiScreen(new EduCraftGui(this));
    	}
    }
    
    public void falseToggled() {
    	toggled=false;
    }
}