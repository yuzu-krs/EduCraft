package me.gamrboy4life.paradox.module.c;
import java.io.File;
import java.io.IOException;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class Run extends Module {

    private boolean hasToggled = false;

    public Run() {
        super("Run", 0, Category.RUN);
    }

    @Override
    public void onUpdate() {
        // モジュールがトグルされており、処理がまだ実行されていない場合
        if (isToggled() && !hasToggled) {
        	
        	//コンパイルと実行を行う
        	compileAndRunCFile("C:/EduCraft/main.c");
        	
             // 処理が完了した後、フラグをtrueに設定
            hasToggled = true;

            // モジュールのトグルを解除
            toggled = false;
        }
    }
 
    private void compileAndRunCFile(String filePath) {
    	try {
    		
    		File file=new File(filePath);
    		
    		//ファイルのディレクトリが存在しない場合は作成する
    		File parentDir=file.getParentFile();
    		if(parentDir!=null&&!parentDir.exists()) {
    			if(parentDir.mkdirs()) {
    				System.out.println("ディレクトリを新規作成しました:"+parentDir.getPath());
    			}else {
    				System.out.println("ディレクトリの作成に失敗しました: "+parentDir.getPath());
    			}
    		}
    		
    		//ファイルが存在しない場合は新規作成
    		if(!file.exists()) {
    			if(file.createNewFile()) {
    				System.out.println("ファイルを新規作成しました:"+parentDir.getPath());
    			}else {
    				System.out.println("ファイルの作成に失敗しました: "+parentDir.getPath());
    			}
    		}
    		
    		/*コンパイル*/
    		ProcessBuilder compileProcessBuilder=new ProcessBuilder("C:/EduCraft/bin/gcc",filePath,"-o","C:/EduCraft/main.exe");
    		
    		//コンパイル時のエラーを出力している
    		compileProcessBuilder.inheritIO();

    		//コンパイルの開始と終了待ち
    		Process compileProcess=compileProcessBuilder.start();
    		compileProcess.waitFor();
    		
    		if(compileProcess.exitValue()!=0) {
    			System.err.println("コンパイルに失敗しました");
    		}
    		/*コンパイル*/
    		
    		
    		/*実行*/
    		ProcessBuilder runProcessBuilder=new ProcessBuilder("C:/EduCraft/main.exe");
    		//標準入出力のリダイレクト
    		runProcessBuilder.inheritIO();
    		
    		//実行processの開始と終了待ち
    		Process runProcess=runProcessBuilder.start();
    		runProcess.waitFor();
    		
    		//実行結果のチェック
    		if(runProcess.exitValue()!=0) {
    			System.out.println("実行に失敗しました");
    		}else {
    			System.out.println("実行が成功しました");
    			
    		}
    		/*実行*/
    		
    		
    	}catch(IOException e) {
    		System.err.println("IOエラーが発生しました: " + e.getMessage());
    		e.printStackTrace();
    	}catch(InterruptedException e) {
    		System.err.println("プロセスが中断されました: " + e.getMessage());	
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