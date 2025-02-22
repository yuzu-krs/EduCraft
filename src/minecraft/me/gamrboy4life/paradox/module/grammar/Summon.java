package me.gamrboy4life.paradox.module.grammar;


import me.gamrboy4life.paradox.gui.grammargui.SummonGui;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.Minecraft;

public class Summon extends Module {

    public Summon() {
        super("summon関数", 0, Category.GRAMMAR);
    }

    @Override
    public void onEnable() {
    	if(isToggled()) {
    		Minecraft.getMinecraft().displayGuiScreen(new SummonGui(this));
    	}
    }
    
    public void falseToggled() {
    	toggled=false;
    }
}