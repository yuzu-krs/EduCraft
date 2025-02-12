package me.gamrboy4life.paradox.module.misc;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import me.gamrboy4life.paradox.Sotuken;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.ResourceLocation;

public class CopyBlockID extends Module {
    
    private boolean hasToggled = false;

    public CopyBlockID() {
        super("視点先のブロックIDをコピー", 0, Category.MISC);
    }

    @Override
    public void onUpdate() {
        if (isToggled() && !hasToggled) {
            String blockID = getLookingAtBlockID();

            if (blockID != null) {
                Sotuken.instance.moduleManager.addChatMessage("視点先のブロックIDがクリップボードにコピーされました: " + blockID);
                copyToClipboard(blockID);
            } else {
                Sotuken.instance.moduleManager.addErrChatMessage("目の前にブロックがないため、コピーできませんでした");
                Minecraft.getMinecraft().getSoundHandler().playSound(
                    PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
                );
            }

            hasToggled = true;
            toggled = false;
        }
    }

    private String getLookingAtBlockID() {
        MovingObjectPosition objectMouseOver = mc.objectMouseOver;
        if (objectMouseOver != null && objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
            BlockPos pos = objectMouseOver.getBlockPos();
            Block block = mc.theWorld.getBlockState(pos).getBlock();
            ResourceLocation blockRegistryName = Block.blockRegistry.getNameForObject(block);
            return (blockRegistryName != null) ? blockRegistryName.getResourcePath() : "Unknown"; // ← 修正！
        }
        return null;
    }

    private void copyToClipboard(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        hasToggled = false;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        hasToggled = false;
    }
}

