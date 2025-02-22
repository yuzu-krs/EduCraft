package me.gamrboy4life.paradox.module.misc;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import me.gamrboy4life.paradox.Sotuken;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class CopyXYZ extends Module{
	
	private boolean hasToggled = false;
	
	
	
	public CopyXYZ() {
        super("現在位置の座標をコピー", 0 , Category.MISC);
    }
	
    @Override
    public void onUpdate() {
        // モジュールがトグルされており、処理がまだ実行されていない場合
        if (isToggled() && !hasToggled) {
        	
        	//x,y,z座標を文字形式にする
        	String coordinates=String.format("%d,%d,%d", 
                    (int) Math.floor(mc.thePlayer.posX),  // 切り捨てて整数に
                    (int) Math.floor(mc.thePlayer.posY),  // Y座標も切り捨て
                    (int) Math.floor(mc.thePlayer.posZ)   // Z座標も切り捨て
                    );
        	
        	copyToClipboard(coordinates);
        	
        	//メッセージを表示
        	Sotuken.instance.moduleManager.addChatMessage("現在位置の座標がクリップボードにコピーされました: " + coordinates);
        	
        	
        	
        	
        	// 処理が完了した後、フラグをtrueに設定
            hasToggled = true;

            // モジュールのトグルを解除
            toggled = false;
        }
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

