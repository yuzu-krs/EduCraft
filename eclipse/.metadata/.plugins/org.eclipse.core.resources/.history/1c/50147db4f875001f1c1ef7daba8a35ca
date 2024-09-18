package me.gamrboy4life.paradox.module.grammar;


import me.gamrboy4life.paradox.gui.grammargui.TestForBlocksGui;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.Minecraft;

public class TestForBlocks extends Module {

    public TestForBlocks() {
        super("TestForBlocks関数", 0, Category.GRAMMAR);
    }

    @Override
    public void onEnable() {
    	if(isToggled()) {
    		Minecraft.getMinecraft().displayGuiScreen(new TestForBlocksGui(this));
    	}
    }
    
    public void falseToggled() {
    	toggled=false;
    }
}