package me.gamrboy4life.paradox.module.misc;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import me.gamrboy4life.paradox.Sotuken;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.ResourceLocation;

public class CopyLookingAt extends Module{
	
	private boolean hasToggled = false;
	
	public CopyLookingAt() {
        super("視点先の座標をコピー", 0 , Category.MISC);
    }
	
    @Override
    public void onUpdate() {
        // モジュールがトグルされており、処理がまだ実行されていない場合
        if (isToggled() && !hasToggled) {

        	//プレイヤーが見て理宇ブロックの座標を取得
        	BlockPos lookingAtPos=getLookingAtBlockPos();
        	String coordinates;
        	
        	if(lookingAtPos!=null) {
        		coordinates=String.format("%d,%d,%d",
        				lookingAtPos.getX(),
        				lookingAtPos.getY(),
        				lookingAtPos.getZ());
        		Sotuken.instance.moduleManager.addChatMessage("視点先の座標がクリップボードにコピーされました: " + coordinates);
            	//クリップボードにコピー
            	copyToClipboard(coordinates);

        	}else {
        		Sotuken.instance.moduleManager.addErrChatMessage("目の前にブロックがないため、コピーできませんでした");
        		Minecraft.getMinecraft().getSoundHandler().playSound(
        			    PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
        			);
        		
        	}
        	
        	
        	
        	// 処理が完了した後、フラグをtrueに設定
            hasToggled = true;

            // モジュールのトグルを解除
            toggled = false;
        }
    }
	   

    //プレイヤーが見ているブロックの座標を取得
    private BlockPos getLookingAtBlockPos() {
    	//カーソル先のオブジェクトをゲット
    	MovingObjectPosition objectMouseOver=mc.objectMouseOver;
    	//プレイヤーがブロックに対してカーソルを合わせている場合
    	if(objectMouseOver!=null&&objectMouseOver.typeOfHit==MovingObjectType.BLOCK) {
    		return objectMouseOver.getBlockPos();
    	}
    	return null;
    }
    
    //文字列をクリップボードにコピーする
    private void copyToClipboard(String text) {
    	StringSelection stringSelection=new StringSelection(text);
    	//クリップ内容が変更されても通知されないようにnull
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
