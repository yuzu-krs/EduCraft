package me.gamrboy4life.paradox.module.editor;

import java.io.File;
import java.io.IOException;

import me.gamrboy4life.paradox.Sotuken;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

public class VSCode extends Module{
	
	private boolean hasToggled=false;
	
	public VSCode() {
		super("VSCode",0,Category.EDITOR);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()&&!hasToggled) {
			
			//ここに処理を書く
			openFileInVSCode("C:/EduCraft/main.c");
			
			//処理が完了した後、フラグをtrueに設定
			hasToggled=true;
			
			//モジュールのトグルを解除
			toggled=false;
			
		} 
	}
	
	private void openFileInVSCode(String filePath) {
		try {
    		File file=new File(filePath);
    		
    		//ファイルのディレクトリが存在しない場合は作成する
    		File parentDir=file.getParentFile();
    		if(parentDir!=null&&!parentDir.exists()) {
    			if(parentDir.mkdirs()) {
    				Sotuken.instance.moduleManager.addChatMessage("ディレクトリを新規作成しました:"+parentDir.getPath());
    			}else {
    				Sotuken.instance.moduleManager.addErrChatMessage("ディレクトリの作成に失敗しました:"+parentDir.getPath());
            		Minecraft.getMinecraft().getSoundHandler().playSound(
            			    PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
        			);
    				
    			}
    		}
    		
    		//ファイルが存在しない場合は新規作成
    		if(!file.exists()) {
    			if(file.createNewFile()) {
    				Sotuken.instance.moduleManager.addChatMessage("ファイルを新規作成しました:"+parentDir.getPath());
    			}else {
    				Sotuken.instance.moduleManager.addErrChatMessage("ファイルの作成に失敗しました:"+parentDir.getPath());
            		Minecraft.getMinecraft().getSoundHandler().playSound(
            			    PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
        			);
    			}
    		}
    		
    		//VSCodeを起動し、指定のファイルを開く
    		ProcessBuilder pb=new ProcessBuilder("cmd.exe","/c","code",filePath);
    		pb.start();
    		
    		Sotuken.instance.moduleManager.addChatMessage("VSCodeでファイルを開きます: "+filePath);
    		
    		
    		
    	}catch(IOException e){
    		Sotuken.instance.moduleManager.addErrChatMessage("VSCodeでファイルを開く際にエラーが発生しました: " + e.getMessage());
    		e.printStackTrace();
    		Minecraft.getMinecraft().getSoundHandler().playSound(
    			    PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
			);
    	}
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		hasToggled=false;
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		hasToggled=false;
		
	}
	
	
	
	
	
}
