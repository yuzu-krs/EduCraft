package me.gamrboy4life.paradox.module.c;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import org.lwjgl.input.Keyboard;

import me.gamrboy4life.paradox.Sotuken;
import me.gamrboy4life.paradox.analysis.UndoDataParser;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;


public class UndoBlock extends Module {

    private boolean hasToggled = false;

    public UndoBlock() {
        super("Undo", Keyboard.KEY_U , Category.RUN);
    }

    @Override
    public void onUpdate() {
        // モジュールがトグルされており、処理がまだ実行されていない場合
        if (isToggled() && !hasToggled) {
        	
        	
        	
        	new Thread(new Runnable() {
				@Override
				public void run() {
					//コンパイルと実行を行う
					compileAndRunCFile("C:/EduCraft/Undo/main.c");
				}
			}).start();
        
        	
        	
        	
             // 処理が完了した後、フラグをtrueに設定
            hasToggled = true;

            // モジュールのトグルを解除
            toggled = false;
        }
    }
 
    private void compileAndRunCFile(String filePath) {
    	try {
    		
    		File file=new File(filePath);

    		//既存のmain.exeを削除する
    		File exeFile=new File("C:/EduCraft/Undo/main.exe");
    		if(exeFile.exists()) {
    			if(exeFile.delete()) {
    				//Sotuken.instance.moduleManager.addChatMessage("既存のexeファイルを削除しました:");
    			}else {
    				//Sotuken.instance.moduleManager.addChatMessage("既存のexeファイルの削除に失敗しました:");    				
    			}
    		}
    		
    		
    		

    		/*###########*/
    		
    		//コンパイル時の警告を検出するフラグ
    		boolean hasWarnings = false;
    		
    		/*コンパイル*/

    		String gccDir = "C:/EduCraft/bin/gcc";
    		String minecraftDir = "C:/EduCraft/function_minecraft";
    		String minecraftFile = "C:/EduCraft/function_minecraft/minecraft.c";

    		ProcessBuilder compileProcessBuilder = new ProcessBuilder(
    		    gccDir,
    		    "-I" + minecraftDir,
    		    filePath,
    		    minecraftFile,
    		    "-o",
    		    "C:/EduCraft/Undo/main.exe"
    		);

    		// エラーを標準出力にリダイレクト
    		compileProcessBuilder.redirectErrorStream(true);

    		// コンパイルの開始
    		Process compileProcess = compileProcessBuilder.start();

    		// コンパイルprocessの出力をUTF-8で読み取る
    		BufferedReader compileReader = new BufferedReader(new InputStreamReader(compileProcess.getInputStream(), StandardCharsets.UTF_8));
    		String line;
    		while ((line = compileReader.readLine()) != null) {
    			
    		    // "warning"が出力内に含まれていたら、警告があったと判断する
    		    if(line.toLowerCase().contains("warning")) {
    		    	hasWarnings=true;
    		    }
    			
    		    // 行が76文字を超える場合は分割して表示
    		    while (line.length() > 76) {
    		        Sotuken.instance.moduleManager.addErrChatMessage(line.substring(0, 76));
    		        line = line.substring(76);
    		    }
    		    
    		    Sotuken.instance.moduleManager.addErrChatMessage(line);
    		}

    		// コンパイル終了待ち
    		compileProcess.waitFor();

    		if (compileProcess.exitValue() != 0) {
    		    Sotuken.instance.moduleManager.addErrChatMessage("コンパイルに失敗しました");
        		Minecraft.getMinecraft().getSoundHandler().playSound(
        			    PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
    			);
    		}else if(hasWarnings){
    			Sotuken.instance.moduleManager.addErrChatMessage("コンパイルは成功しましたが、警告があります");
        		Minecraft.getMinecraft().getSoundHandler().playSound(
        			    PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
    			);
    		}else {
    			//Sotuken.instance.moduleManager.addChatMessage("コンパイルに成功しました");
    		}
    		/*###########*/    		
    		
    		
    		
    		
    		
    		
    		/*###########*/
    		/*実行*/
    		
    		//コンパイルが失敗した場合実行しない
    		if(compileProcess.exitValue()==0) {
    			
    			Run.displayAllData();
    			

    		    ProcessBuilder runProcessBuilder = new ProcessBuilder("C:/EduCraft/Undo/main.exe");

    		    // 実行プロセスの開始
    		    final Process runProcess = runProcessBuilder.start();

    		    // 入力ストリームを作成し、外部プロセスにデータを入力
    		    OutputStream outputStream = runProcess.getOutputStream();
    		    final PrintWriter processInputWriter = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true);

    		    // 出力を非同期的に読み取るスレッドを作成
    		    Thread outputReaderThread = new Thread(new Runnable() {
    		        @Override
    		        public void run() {
    		            UndoDataParser parser = new UndoDataParser();

    		            try {
    		                BufferedReader runReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream(), StandardCharsets.UTF_8));
    		                String line;
    		                while ((line = runReader.readLine()) != null) {
    		                    parser.undoParseData(line);
    		                    System.out.println("jafoiwjeijfoiawji"+line);
    		                    if (line.startsWith("999999959") || line.startsWith("999999949") || line.startsWith("999999948")||line.startsWith("999999919")) {
    		                        //Run.javaでためた入力パターンの先頭をゲットして削除

    		                    	processInputWriter.println(Run.getAndRemoveFirstData());
    		                    }
    		                }
    		            } catch (IOException e) {
    		                Sotuken.instance.moduleManager.addErrChatMessage("出力読み取り中にエラーが発生しました: " + e.getMessage());
    		                e.printStackTrace();
    		                Minecraft.getMinecraft().getSoundHandler().playSound(
    		                    PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
    		                );
    		            }
    		        }
    		    });
    		    outputReaderThread.start();

    		    // プロセスが完了するまで待機し、エラー時にのみメッセージを表示
    		    outputReaderThread.join();

    		    if (runProcess.exitValue() != 0) {
    		        Sotuken.instance.moduleManager.addErrChatMessage("Undoに失敗しました");
    		        Minecraft.getMinecraft().getSoundHandler().playSound(
    		            PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
    		        );
    		    } else {
    		        Sotuken.instance.moduleManager.addChatMessage("Undoが成功しました");
    		        
    		        
    		        
    		    }
    		}

    		/*###########*/

    		
    		
    	}catch(IOException e) {
    		Sotuken.instance.moduleManager.addErrChatMessage("IOエラーが発生しました: " + e.getMessage());
    		e.printStackTrace();
    		Minecraft.getMinecraft().getSoundHandler().playSound(
    			    PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
			);
    	}catch(InterruptedException e) {
    		Sotuken.instance.moduleManager.addErrChatMessage("プロセスが中断されました: " + e.getMessage());	
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