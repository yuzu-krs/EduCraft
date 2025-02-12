package me.gamrboy4life.paradox.mods.impl;

import me.gamrboy4life.paradox.gui.hud.ScreenPosition;
import me.gamrboy4life.paradox.mods.ModDraggable;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;

public class ModBlockID extends ModDraggable {

    private static boolean enabled = true; // 表示の有効/無効を管理

    @Override
    public int getWidth() {
        return font.getStringWidth(getBlockNameString());
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        if (enabled) {
            font.drawString(getBlockNameString(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
        }
    }

    private String getBlockNameString() {
        if (mc.theWorld == null || mc.thePlayer == null) {
            return "BlockID: N/A";
        }

        MovingObjectPosition mop = mc.objectMouseOver;
        if (mop == null || mop.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) {
            return "BlockID: None";
        }

        BlockPos pos = mop.getBlockPos();
        Block block = mc.theWorld.getBlockState(pos).getBlock();

        // ブロックのリソース名を取得（minecraft:wool → wool）
        ResourceLocation blockRegistryName = Block.blockRegistry.getNameForObject(block);
        if (blockRegistryName == null) {
            return "BlockID: Unknown";
        }

        String blockName = blockRegistryName.toString(); // "minecraft:wool" のような形式
        if (blockName.startsWith("minecraft:")) {
            blockName = blockName.substring(10); // "minecraft:" を削除
        }

        return String.format("BlockID: %s", blockName);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        if (enabled) {
            font.drawString(getBlockNameString(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1); // ダミー表示も実際のメソッドを使用
        }
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean isEnabled) {
        ModBlockID.enabled = isEnabled;
    }
}
