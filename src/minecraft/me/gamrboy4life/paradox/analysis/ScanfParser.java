package me.gamrboy4life.paradox.analysis;

import me.gamrboy4life.paradox.Sotuken;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class ScanfParser {
	public void ScanfParseData(String data) {
				
			if(isTestForBlock(data)) {
				executeTestForBlock(data);
			
			}else if(isTestForBlocksAll(data)) {
				executeTestForBlocksAll(data);

			}else if(isTestForBlocksMasked(data)) {
				executeTestForBlocksMasked(data);
			}else {
				//Sotuken.instance.moduleManager.addRunChatMessage(data.toString());
			}
		
		}
	
	private boolean isTestForBlock(String line) {
		return line.startsWith("999999959,");
	}
	
	private void executeTestForBlock(String line) {
		try {
		
			 // lineをカンマで分割
	        String[] parts = line.split(",");
	        if (parts.length == 6) {
	            int x = Integer.parseInt(parts[1]);
	            int y = Integer.parseInt(parts[2]);
	            int z = Integer.parseInt(parts[3]);
	            String blockName = parts[4];
	            int blockInfo = Integer.parseInt(parts[5]);
	
	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/testforblock %d %d %d %s %d",x,y,z,blockName,blockInfo) 
	                )
	            );
	            
	            
	            
	
	            
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:testForBlock関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:testForBlock関数解析: " + e.getMessage());
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private boolean isTestForBlocksAll(String line) {
		return line.startsWith("999999949,");
	}
	
	private void executeTestForBlocksAll(String line) {
		try {
		
			 // lineをカンマで分割
	        String[] parts = line.split(",");
	        if (parts.length == 10) {
	            int x1 = Integer.parseInt(parts[1]);
	            int y1 = Integer.parseInt(parts[2]);
	            int z1 = Integer.parseInt(parts[3]);
	            int x2 = Integer.parseInt(parts[4]);
	            int y2 = Integer.parseInt(parts[5]);
	            int z2 = Integer.parseInt(parts[6]);
	            int x = Integer.parseInt(parts[7]);
	            int y = Integer.parseInt(parts[8]);
	            int z = Integer.parseInt(parts[9]);
	
	
	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/testforblocks %d %d %d %d %d %d %d %d %d all",x1,y1,z1,x2,y2,z2,x,y,z) 
	                )
	            );
	            
	            
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:testForBlocksAll関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:testForBlocksAll関数解析: " + e.getMessage());
	    }
	}
	
	
	
	
	
	
	
	
	private boolean isTestForBlocksMasked(String line) {
		return line.startsWith("999999948,");
	}
	
	private void executeTestForBlocksMasked(String line) {
		try {
		
			 // lineをカンマで分割
	        String[] parts = line.split(",");
	        if (parts.length == 10) {
	            int x1 = Integer.parseInt(parts[1]);
	            int y1 = Integer.parseInt(parts[2]);
	            int z1 = Integer.parseInt(parts[3]);
	            int x2 = Integer.parseInt(parts[4]);
	            int y2 = Integer.parseInt(parts[5]);
	            int z2 = Integer.parseInt(parts[6]);
	            int x = Integer.parseInt(parts[7]);
	            int y = Integer.parseInt(parts[8]);
	            int z = Integer.parseInt(parts[9]);
	
	
	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/testforblocks %d %d %d %d %d %d %d %d %d masked",x1,y1,z1,x2,y2,z2,x,y,z) 
	                )
	            );
	            
	            
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:testForBlocksMasked関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:testForBlocksMasked関数解析: " + e.getMessage());
	    }
	}

}