package me.gamrboy4life.paradox.mods.impl;

import me.gamrboy4life.paradox.gui.hud.ScreenPosition;
import me.gamrboy4life.paradox.mods.ModDraggable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;

public class ModLookingAt extends ModDraggable {

    private static boolean enabled = true;

    @Override
    public int getWidth() {
        return font.getStringWidth(getXYZString());
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        if (enabled) {
            font.drawString(getXYZString(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
        }
    }

    private String getXYZString() {
        BlockPos lookingAtPos = getLookingAtBlockPos();
        if (lookingAtPos != null) {
            return String.format(
                "Looking at: %d / %d / %d",
                lookingAtPos.getX(),
                lookingAtPos.getY(),
                lookingAtPos.getZ()
            );
        } else {
            return "Looking at: None";
        }
    }

    private BlockPos getLookingAtBlockPos() {
        MovingObjectPosition objectMouseOver = mc.objectMouseOver;
        if (objectMouseOver != null && objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
            return objectMouseOver.getBlockPos();
        }
        return null;
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        if (enabled) {
            font.drawString(getXYZString(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
        }
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean isEnabled) {
        ModLookingAt.enabled = isEnabled;
    }
}