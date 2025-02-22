package me.gamrboy4life.paradox.module.grammar;


import me.gamrboy4life.paradox.gui.grammargui.TestForBlockGui;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.Minecraft;

public class TestForBlock extends Module {

    public TestForBlock() {
        super("testForBlock関数", 0, Category.GRAMMAR);
    }

    @Override
    public void onEnable() {
    	if(isToggled()) {
    		Minecraft.getMinecraft().displayGuiScreen(new TestForBlockGui(this));
    	}
    }
    
    public void falseToggled() {
    	toggled=false;
    }
}