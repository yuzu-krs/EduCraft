package me.gamrboy4life.paradox.module.status;

import me.gamrboy4life.paradox.mods.impl.ModBlockID;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class BlockID extends Module {

    public BlockID() {
        super("ブロックID", 0, Category.STATUS);
    }

    @Override
    public void onUpdate() {
        ModBlockID modBlockID=new ModBlockID();
        modBlockID.setEnabled(isToggled());
    }
}