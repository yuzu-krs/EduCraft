package me.gamrboy4life.paradox.module.grammar;


import me.gamrboy4life.paradox.gui.grammargui.GetPositionGui;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.Minecraft;

public class GetPosition extends Module {

    public GetPosition() {
        super("getposition関数", 0, Category.GRAMMAR);
    }

    @Override
    public void onEnable() {
    	if(isToggled()) {
    		Minecraft.getMinecraft().displayGuiScreen(new GetPositionGui(this));
    	}
    }
    
    public void falseToggled() {
    	toggled=false;
    }
}