package me.gamrboy4life.paradox.module.editor;

import java.io.File;
import java.io.IOException;

import me.gamrboy4life.paradox.Sotuken;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

public class Notepad extends Module{
	
	private boolean hasToggled=false;
	
	public Notepad() {
		super("Notepad",0,Category.EDITOR);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()&&!hasToggled) {
			
			
			//ここに処理を書く
			openFileInNotepad("C:/EduCraft/main.c");
			
			
            // 処理が完了した後、フラグをtrueに設定
            hasToggled = true;

            // モジュールのトグルを解除
            toggled = false;
		} 
	}
	
	
    private void openFileInNotepad(String filePath) {
    	try {
    		File file=new File(filePath);
    		
    		//ファイルのディレクトリが存在しない場合は作成する
    		File parentDir=file.getParentFile();
    		if(parentDir!=null&&!parentDir.exists()) {
    			if(parentDir.mkdirs()) {
    				Sotuken.instance.moduleManager.addChatMessage("ディレクトリを新規作成しました: "+parentDir.getPath());
    			}else {
    				Sotuken.instance.moduleManager.addErrChatMessage("ディレクトリの作成に失敗しました: "+parentDir.getPath());
            		Minecraft.getMinecraft().getSoundHandler().playSound(
            			    PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
        			);
    			}
    		}
    		
    		//ファイルが存在しない場合は新規作成
    		if(!file.exists()) {
    			if(file.createNewFile()) {
    				Sotuken.instance.moduleManager.addChatMessage("ファイルを新規作成しました: "+parentDir.getPath());
    			}else {
    				Sotuken.instance.moduleManager.addErrChatMessage("ファイルの作成に失敗しました: "+parentDir.getPath());
            		Minecraft.getMinecraft().getSoundHandler().playSound(
            			    PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
        			);
    			}
    		}
    		
    		
    		//メモ帳で指定のファイルを開く
    		Runtime.getRuntime().exec("notepad.exe "+filePath);
    		
    		Sotuken.instance.moduleManager.addChatMessage("メモ帳でファイルを開きます: " + filePath);
    		
    	}catch(IOException e){
    		Sotuken.instance.moduleManager.addErrChatMessage("メモ帳でファイルを開く際にエラーが発生しました: "+e.getMessage());
    		e.printStackTrace();
    		Minecraft.getMinecraft().getSoundHandler().playSound(
    			    PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
			);
    	}
		
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
