package me.gamrboy4life.paradox.module.c;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import me.gamrboy4life.paradox.Sotuken;
import me.gamrboy4life.paradox.analysis.DataParser;
import me.gamrboy4life.paradox.command.commands.Scanf;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.network.NetHandlerPlayClient;
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
					

			            // コンパイルと実行を行う
			            compileAndRunCFile("C:/EduCraft/main.c");

				}
			}).start();
        
        	
        	
        	
             // 処理が完了した後、フラグをtrueに設定
            hasToggled = true;

            // モジュールのトグルを解除
            toggled = false;
        }
    }
    
    
    
    
    // 入力内容や条件結果を保存するstaticなリスト
    public static List<Object> logIfMscanf = new ArrayList<Object>();
    
    // UndoBlockに入力パターンや条件結果を送るリスト
    // 入力内容や条件結果を保存するstaticなリスト
    public static List<Object> SendlogIfMscanf = new ArrayList<Object>();
    
 // logIfMscanf の内容を SendlogIfMscanf にコピーするメソッド
    public static void copyLogToSendLog() {
        // 既存の内容をクリアしてからコピー
        SendlogIfMscanf.clear();
        SendlogIfMscanf.addAll(logIfMscanf);
        System.out.println("logIfMscanf の内容を SendlogIfMscanf にコピーしました");
    }
    
 // リストの先頭要素を取得して削除するメソッド (SendlogIfMscanf 用)
    public static Object getAndRemoveFirstDataSend() {
        if (!SendlogIfMscanf.isEmpty()) {
            // 先頭の要素を取得
            Object firstData = SendlogIfMscanf.get(0);
            // 先頭の要素をリストから削除
            SendlogIfMscanf.remove(0);
            System.out.println("取得して削除 (Send): " + firstData);
            return firstData;
        } else {
            System.out.println("リスト (Send) が空です");
            return null;
        }
    }

    
 // リストのすべての要素を表示するメソッド
    public static void displayAllData() {
        if (!logIfMscanf.isEmpty()) {
            System.out.println("リストの中身:");
            for (Object data : logIfMscanf) {
                System.out.println(data);
            }
        } else {
            System.out.println("リストが空です");
        }
    }

    
    
 
    private void compileAndRunCFile(String filePath) {
    	
    	
    	//強制終了処理
        try {
        	//taskkill プロセスを終了
        	//F 強制終了
        	//IM 後ろに続くイメージ名をkill
            ProcessBuilder builder = new ProcessBuilder("taskkill", "/F", "/IM", "main.exe");
            Process process = builder.start();
            process.waitFor(); // プロセスが終了するまで待機
            
        } catch (IOException e) {
            Sotuken.instance.moduleManager.addChatMessage("プロセス終了中にエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            Sotuken.instance.moduleManager.addChatMessage("プロセス終了中にエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
        }
    	
        




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
    		if (compileProcess.exitValue() == 0) {
    			
    			logIfMscanf.clear();    // リスト全体をクリアするメソッド

    			
    		    ProcessBuilder runProcessBuilder = new ProcessBuilder("C:/EduCraft/main.exe");

    		    // 実行プロセスの開始
    		    final Process runProcess = runProcessBuilder.start();

    		    // 入力ストリームを作成し、外部プロセスにデータを入力
    		    OutputStream outputStream = runProcess.getOutputStream();
    		    final PrintWriter processInputWriter = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true);

    		    // 出力を非同期的に読み取るスレッドを作成
    		    Thread outputReaderThread = new Thread(new Runnable() {
    		        @Override
    		        public void run() {
    		            DataParser parser = new DataParser();

    		            try {
    		                BufferedReader runReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream(), StandardCharsets.UTF_8));
    		                String line;
    		                while ((line = runReader.readLine()) != null) {
    		                	
    		                	

    		                	
    		                	
    		                    parser.parseData(line);
    		                    Scanf.clearInputText(); // 入力テキストをクリア
    		                    NetHandlerPlayClient.clearBlockFindResult(); // リセット

    		                    if (line.startsWith("999999959") || line.startsWith("999999949") || line.startsWith("999999948")) {
    		                        
    		                    	while (NetHandlerPlayClient.getBlockFindResult() == -1) {
    		                            try {
    		                                Thread.sleep(10); // 10ミリ秒待機
    		                            } catch (InterruptedException e) {
    		                                e.printStackTrace();
    		                            }
    		                        }
    		                    	


    		                        int blockFindResult = NetHandlerPlayClient.getBlockFindResult();
    		                        if (blockFindResult != -1) {
    		                            processInputWriter.println(blockFindResult); // 値を入力として送信
    		                            System.out.println("送信: blockFindResult = " + blockFindResult);
    		                            logIfMscanf.add(blockFindResult); //Undoのためのログを保存
    		                            NetHandlerPlayClient.clearBlockFindResult(); // リセット
    		                        }
    		                        processInputWriter.flush();
    		                        
    		                        
    		                        
    		                        
		                        //プレイヤーのx座標をゲット
    		                    }else if(line.startsWith("999999939")) {
    		                    	
    		                    	
    		                    	
    		                    	processInputWriter.println((int) Math.floor(mc.thePlayer.posX));
    		                    	logIfMscanf.add((int) Math.floor(mc.thePlayer.posX));
    		                    	processInputWriter.flush();
    		                    	
		                        //プレイヤーのy座標をゲット    		                    	
    		                    }else if(line.startsWith("999999938")) {    		                    	

    		                    	
    		                    	processInputWriter.println((int) Math.floor(mc.thePlayer.posY));
    		                    	logIfMscanf.add((int) Math.floor(mc.thePlayer.posY));
    		                    	processInputWriter.flush();
    		                    	
		                        //プレイヤーのz座標をゲット
    		                    }else if(line.startsWith("999999937")) {
    		                    	
    		                    	
    		                    	
    		                    	processInputWriter.println((int) Math.floor(mc.thePlayer.posZ));
    		                    	logIfMscanf.add((int) Math.floor(mc.thePlayer.posZ));
    		                    	processInputWriter.flush();
    		                    	
    		                    	
    		                    } else if (line.startsWith("999999919")) {
    		                        while (Scanf.getInputText().isEmpty()) {
    		                            try {
    		                                Thread.sleep(10); // 10ミリ秒待機
    		                            } catch (InterruptedException e) {
    		                                e.printStackTrace();
    		                            }
    		                        }
    		                        String scanfFindResult = Scanf.getInputText();
    		                        processInputWriter.println(scanfFindResult);
		                        	
    		                        //入力しているようにユーザーに見せている
		                            try {
		                                Thread.sleep(200); // 200ミリ秒待機
		                            } catch (InterruptedException e) {
		                                e.printStackTrace();
		                            }
    		                        
    		                        logIfMscanf.add(scanfFindResult); //Undoのためのログを保存
    		                        
    		                        Scanf.clearInputText(); // 入力テキストをクリア
    		                        processInputWriter.flush();
    		                    }
    		                }
    		            } catch (IOException e) {
    		                Sotuken.instance.moduleManager.addErrChatMessage("出力読み取り中にエラーが発生しました: " + e.getMessage());
    		                e.printStackTrace();
    		                Minecraft.getMinecraft().getSoundHandler().playSound(
    		                    PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
    		                );
    		                
    		            } catch (OutOfMemoryError e) {
    		                // OutOfMemoryErrorをキャッチしてメッセージを表示
    		                Sotuken.instance.moduleManager.addErrChatMessage("プログラムの実行がタイムアウトしました");
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
    		        Sotuken.instance.moduleManager.addErrChatMessage("実行に失敗しました");
    		        Minecraft.getMinecraft().getSoundHandler().playSound(
    		            PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
    		        );
    		    } else {
    		        Sotuken.instance.moduleManager.addChatMessage("実行が成功しました");
    		        Minecraft.getMinecraft().getSoundHandler().playSound(
    		                PositionedSoundRecord.create(new ResourceLocation("random.levelup"), 1.0F)
    		        );
    		        try {
    		            File sourceFile = new File("C:/EduCraft/main.c");
    		            File destFile = new File("C:/EduCraft/Undo/main.c");

    		            File destDir = destFile.getParentFile();
    		            if (!destDir.exists()) {
    		                destDir.mkdirs(); // ディレクトリを作成
    		            }
    		            
    		            copyLogToSendLog(); //入力パターンをUndoに送る

    		            // ファイルのコピー
    		            java.nio.file.Files.copy(sourceFile.toPath(), destFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    		            
    		            

    		        } catch (IOException e) {
    		            Sotuken.instance.moduleManager.addErrChatMessage("ファイルのコピー中にエラーが発生しました: " + e.getMessage());
    		            e.printStackTrace();
    		            Minecraft.getMinecraft().getSoundHandler().playSound(
    		                PositionedSoundRecord.create(new ResourceLocation("note.bass"), 1.0F)
    		            );
    		        }
    		    }
    		}

    		
    		
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