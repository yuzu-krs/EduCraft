package me.gamrboy4life.paradox.analysis;

import me.gamrboy4life.paradox.Sotuken;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class UndoDataParser {
	
	public void undoParseData(String data) {
		
		if(isSetBlockReplace(data)) {
			executeSetBlockReplace(data);
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
				String blockName=parts[4];
				int blockInfo=Integer.parseInt(parts[5]);
				
				//setblockコマンドをマイクラのチャットに送信
				Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
                    new C01PacketChatMessage(
                    		String.format("/setblock %d %d %d %s %d replace",x,y,z,blockName,blockInfo)
                    )
				);
			}else {
				Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:setBlockReplace関数の形式が違います");
			}
		}catch (NumberFormatException e) {
			Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:setBlockReplace関数解析: "+e.getMessage());
		}
	}
	
		
}