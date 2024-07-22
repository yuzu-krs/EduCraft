package me.gamrboy4life.paradox.module.info;


import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.Minecraft;

public class BuildingBlock extends Module {

    public BuildingBlock() {
        super("建築ブロック", 0, Category.INFO);
    }

    @Override
    public void onEnable() {
    	if(isToggled()) {
    		Minecraft.getMinecraft().displayGuiScreen(new BuildingBlockGui(this));
    	}
    }
    
    public void falseToggled() {
    	toggled=false;
    }
}