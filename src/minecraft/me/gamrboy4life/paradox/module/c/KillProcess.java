package me.gamrboy4life.paradox.module.c;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import me.gamrboy4life.paradox.Sotuken;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class KillProcess extends Module{
	
	private boolean hasToggled = false;
	
	public KillProcess() {
        super("強制終了", Keyboard.KEY_X, Category.RUN);
    }
	
    @Override
    public void onUpdate() {
        // モジュールがトグルされており、処理がまだ実行されていない場合
        if (isToggled() && !hasToggled) {

			//裏で動いているmain.exeをkillする
        	killProcess("main.exe");
        	
        	// 処理が完了した後、フラグをtrueに設定
            hasToggled = true;

            // モジュールのトグルを解除
            toggled = false;
        }
    }
	   
    
    //publicで、Run.javaからも呼び出せるように
    public static void killProcess(String processName) {
        try {
        	//taskkill プロセスを終了
        	//F 強制終了
        	//IM 後ろに続くイメージ名をkill
            ProcessBuilder builder = new ProcessBuilder("taskkill", "/F", "/IM", processName);
            Process process = builder.start();
            process.waitFor(); // プロセスが終了するまで待機
            Sotuken.instance.moduleManager.addChatMessage(processName + " を強制終了しました。");
        } catch (IOException e) {
            Sotuken.instance.moduleManager.addChatMessage("プロセス終了中にエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            Sotuken.instance.moduleManager.addChatMessage("プロセス終了中にエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
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
