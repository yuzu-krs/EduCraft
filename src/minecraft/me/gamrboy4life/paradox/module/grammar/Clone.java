package me.gamrboy4life.paradox.module.grammar;


import me.gamrboy4life.paradox.gui.grammargui.CloneGui;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.Minecraft;

public class Clone extends Module {

    public Clone() {
        super("clone関数", 0, Category.GRAMMAR);
    }

    @Override
    public void onEnable() {
    	if(isToggled()) {
    		Minecraft.getMinecraft().displayGuiScreen(new CloneGui(this));
    	}
    }
    
    public void falseToggled() {
    	toggled=false;
    }
}