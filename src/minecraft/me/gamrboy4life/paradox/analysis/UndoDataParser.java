package me.gamrboy4life.paradox.analysis;

import me.gamrboy4life.paradox.Sotuken;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class UndoDataParser {
	
	public void undoParseData(String data) {
		
		if(isSetBlockReplace(data)) {
			executeSetBlockReplace(data);
		}else if(isFillReplace(data)) {
			executeFillReplace(data);
		
		}else if(isFillReplaceBlock(data)) {
			executeFillReplaceBlock(data);
		}else {
			//Sotuken.instance.moduleManager.addRunChatMessage(data.toString());
		}
	
	}
	
	private boolean isSetBlockReplace(String line) {
		return line.startsWith("999999999,");
	}
	
	//関数を実行
	private void executeSetBlockReplace(String line) {
		try {
			//lineをカンマで分割
			String[] parts=line.split(",");
			//6パーツあるか
			if(parts.length==6) {
				int x=Integer.parseInt(parts[1]);
				int y=Integer.parseInt(parts[2]);
				int z=Integer.parseInt(parts[3]);
				//String blockName=parts[4];
				//int blockInfo=Integer.parseInt(parts[5]);
				
				//setblockコマンドをマイクラのチャットに送信
				Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
                    new C01PacketChatMessage(
                    		String.format("/setblock %d %d %d air 0 replace",x,y,z)
                    )
				);
			}else {
				Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:setBlockReplace関数の形式が違います");
			}
		}catch (NumberFormatException e) {
			Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:setBlockReplace関数解析: "+e.getMessage());
		}
	}
	
	private boolean isFillReplace(String line) {
		return line.startsWith("999999992,");
	}
	
	private boolean isFillReplaceBlock(String line) {
		return line.startsWith("999999991,");
	}
	
	private void executeFillReplace(String line) {
		try {
	        // lineをカンマで分割
	        String[] parts = line.split(",");
	        // 9パーツあるか
	        if (parts.length == 9) {
	            int x1 = Integer.parseInt(parts[1]);
	            int y1 = Integer.parseInt(parts[2]);
	            int z1 = Integer.parseInt(parts[3]);
	            int x2 = Integer.parseInt(parts[4]);
	            int y2 = Integer.parseInt(parts[5]);
	            int z2 = Integer.parseInt(parts[6]);
	            //String blockName = parts[7];
	            //int blockInfo = Integer.parseInt(parts[8]);

	            // fillコマンドをマイクラのチャットに送信
	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/fill %d %d %d %d %d %d air 0 ", 
	                        x1, y1, z1, x2, y2, z2)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillReplace関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillReplace関数解析: " + e.getMessage());
	    }
	}
	
	
	private void executeFillReplaceBlock(String line) {
		try {
	        // lineをカンマで分割
	        String[] parts = line.split(",");
	        // 9パーツあるか
	        if (parts.length == 11) {
	            int x1 = Integer.parseInt(parts[1]);
	            int y1 = Integer.parseInt(parts[2]);
	            int z1 = Integer.parseInt(parts[3]);
	            int x2 = Integer.parseInt(parts[4]);
	            int y2 = Integer.parseInt(parts[5]);
	            int z2 = Integer.parseInt(parts[6]);
	            String blockName1 = parts[7];
	            int blockInfo1 = Integer.parseInt(parts[8]);
	            String blockName2 = parts[9];
	            int blockInfo2 = Integer.parseInt(parts[10]);

	            // fillコマンドをマイクラのチャットに送信
	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/fill %d %d %d %d %d %d %s %d replace %s %d", 
	                        x1, y1, z1, x2, y2, z2, blockName1, blockInfo1,blockName2,blockInfo2)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillReplaceBlock関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillReplaceBlock関数解析: " + e.getMessage());
	    }
	}
	
	
		
}