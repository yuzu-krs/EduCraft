package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.mods.impl.ModLookingAt;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class LookingAt extends Module {

    public LookingAt() {
        super("視点先の座標", 0, Category.STATUS);
    }

    @Override
    public void onUpdate() {
    	ModLookingAt modLookingAt=new ModLookingAt();
    	modLookingAt.setEnabled(isToggled());
    }
}