package me.gamrboy4life.paradox.module.grammar;


import me.gamrboy4life.paradox.gui.grammargui.SendCommandGui;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.Minecraft;

public class SendCommand extends Module {

    public SendCommand() {
        super("SendCommand関数", 0, Category.GRAMMAR);
    }

    @Override
    public void onEnable() {
    	if(isToggled()) {
    		Minecraft.getMinecraft().displayGuiScreen(new SendCommandGui(this));
    	}
    }
    
    public void falseToggled() {
    	toggled=false;
    }
}