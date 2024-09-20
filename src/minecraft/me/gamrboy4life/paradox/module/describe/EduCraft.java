package me.gamrboy4life.paradox.module.describe;


import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class EduCraft extends Module {

    public EduCraft() {
        super("使い方", 0, Category.DESCRIBE);
    }

    @Override
    public void onEnable() {
    	if(isToggled()) {
    		
    	}
    }
    
    public void falseToggled() {
    	toggled=false;
    }
}