package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.Sotuken;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class Panic extends Module{

	public Panic() {
		super("Panic",0,Category.STATUS);
	}
	
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			for(Module m:Sotuken.instance.moduleManager.getModules()) {
				m.setToggled(false);
			}
			Sotuken.instance.moduleManager.addChatMessage("すべてのMODが無効になりました!");
		} 
		

	}

}
