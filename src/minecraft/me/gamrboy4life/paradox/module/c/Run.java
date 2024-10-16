package me.gamrboy4life.paradox.module.c;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import org.lwjgl.input.Keyboard;

import me.gamrboy4life.paradox.Sotuken;
import me.gamrboy4life.paradox.analysis.DataParser;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

public class Run extends Module {

    private boolean hasToggled = false;

    public Run() {
        super("実行", Keyboard.KEY_R , Category.RUN);
    }

    @Override
    public void onUpdate() {
        // モジュールがトグルされており、処理がまだ実行されていない場合
        if (isToggled() && !hasToggled) {
        	
        	
        	
        	new Thread(new Runnable() {
				@Override
				public void run() {
					//コンパイルと実行を行う
					compileAndRunCFile("C:/EduCraft/main.c");
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
    		
    		
    		
    		//既存のmain.exeを削除する
    		File exeFile=new File("C:/EduCraft/main.exe");
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
    		    "C:/EduCraft/main.exe"
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
    			Sotuken.instance.moduleManager.addChatMessage("コンパイルに成功しました");
    		}
    		/*###########*/    		
    		
    		
    		
    		
    		
    		
    		/*###########*/
    		/*実行*/
    		
    		//コンパイルが失敗した場合実行しない
    		if(compileProcess.exitValue()==0) {

    			ProcessBuilder runProcessBuilder=new ProcessBuilder("C:/EduCraft/main.exe");
	    		
	    		//実行processの開始
	    		final Process runProcess=runProcessBuilder.start();
	    		
	    		
	            // 出力を非同期的に読み取るスレッドを作成
	            Thread outputReaderThread = new Thread(new Runnable() {
					@Override
					public void run() {
						
						DataParser parser= new DataParser();
						
					    try {
					    	BufferedReader runReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream(),StandardCharsets.UTF_8));
					        String line;
					        while ((line = runReader.readLine()) != null) {
					        	parser.parseData(line);
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
	            

	            //無限ループのためのタイムアウト設定をする(10秒)
	            boolean completed = runProcess.waitFor(10, TimeUnit.SECONDS);
	            if(!completed) {
	            	
	            	
                    DataParser parser = new DataParser();
                    try {
                        InputStream inputStream = runProcess.getInputStream();
                        BufferedReader runReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

                        StringBuilder fullData = new StringBuilder();
                        
                        //一度に読み取れるバッファサイズを最大1024文字にする
                        char[] buffer = new char[1024]; 
                        int numCharsRead;

                    	//最大1024文字までデータを格納し、numCharsReadに格納する
                        while ((numCharsRead = runReader.read(buffer)) != -1) {
                            fullData.append(buffer, 0, numCharsRead);
                            //何かしらデータがある場合、解析を行う。 
                            if (fullData.length() > 0) {
                                parser.parseData(fullData.toString()); //解析
                                fullData.setLength(0); // 解析後fullDataをリセット
                            }
                        }
                    } catch (IOException e) {
                        Sotuken.instance.moduleManager.addErrChatMessage("出力読み取り中にエラーが発生しました: " + e.getMessage());
                        e.printStackTrace();
                		Minecraft.getMinecraft().getSoundHandler().playSound(
                			    PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
            			);
                    }
	            	

	            	
	            	//タイムアウト発生時の処理
	            	runProcess.destroy(); //プロセスを強制終了
	            	Sotuken.instance.moduleManager.addErrChatMessage("プログラムの実行がタイムアウトしました");
            		Minecraft.getMinecraft().getSoundHandler().playSound(
            			    PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
        			);
	            		            	
	            	
	            	//出力読み取りスレッドを中断
	            	outputReaderThread.interrupt();
	            }else {
	            	//タイムアウトしなかったら、出力を読み取りスレッドの終了を待つ
	            	outputReaderThread.join();
	            	
	        		//実行結果のチェック
		    		if(runProcess.exitValue()!=0) {
		    			Sotuken.instance.moduleManager.addErrChatMessage("実行に失敗しました");
	            		Minecraft.getMinecraft().getSoundHandler().playSound(
	            			    PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
	        			);
		    		}else {
		    			Sotuken.instance.moduleManager.addChatMessage("実行が成功しました");
		    			
		    			//実行が成功した場合main.cをUndoディレクトリにコピーする
		    			try {
		    	            File sourceFile = new File("C:/EduCraft/main.c");
		    	            File destFile = new File("C:/EduCraft/Undo/main.c");
		    	            
		    	            // ディレクトリが存在しない場合は作成
		    	            File destDir = destFile.getParentFile();
		    	            if (!destDir.exists()) {
		    	                destDir.mkdirs();  // ディレクトリを作成
		    	            }
		    	            
		    	            // ファイルのコピー
		    	            java.nio.file.Files.copy(sourceFile.toPath(), destFile.toPath(),
		    	                java.nio.file.StandardCopyOption.REPLACE_EXISTING);
		    	            
		    	            
		    	            
		    				
		    	            Sotuken.instance.moduleManager.addChatMessage("Undoディレクトリにログが保存されました");
		    				
		    				
		    				
		    				
		    			}catch(IOException e) {
		    				
		    	            Sotuken.instance.moduleManager.addErrChatMessage("ファイルのコピー中にエラーが発生しました: " + e.getMessage());
		    	            e.printStackTrace();
		    	            Minecraft.getMinecraft().getSoundHandler().playSound(
		    	                PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
		    	            );
		    				
		    				
		    			}
		    			
		    		}
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