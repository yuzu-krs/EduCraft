package me.gamrboy4life.paradox.analysis;

import me.gamrboy4life.paradox.Sotuken;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class UndoDataParser {
	
	public void undoParseData(String data) {
		
		if(isSetBlockReplace(data)) {
			executeSetBlockReplace(data);
			
		}else if(isSetBlockKeep(data)) {
			executeSetBlockKeep(data);
			
		}else if(isSetBlockDestroy(data)) {
			executeSetBlockDestroy(data);
			
			
			
		}else if(isFillReplace(data)) {
			executeFillReplace(data);
		
		}else if(isFillReplaceBlock(data)) {
			executeFillReplaceBlock(data);
			
			
		}else if(isFillDestroy(data)) {
			executeFillDestroy(data);
			
		}else if(isFillKeep(data)) {
			executeFillKeep(data);
			
		}else if(isFillHollow(data)) {
			executeFillHollow(data);
			
			
		}else if(isFillOutline(data)) {
			executeFillOutline(data);
			
		}else if(isCloneReplaceNormal(data)) {
			executeCloneReplaceNormal(data);
		}else if(isCloneReplaceForce(data)) {
			executeCloneReplaceForce(data);
		}else if(isCloneReplaceMove(data)) {
			executeCloneReplaceMove(data);
			
			
			
		}else if(isCloneMaskedNormal(data)) {
			executeCloneMaskedNormal(data);
			
		}else if(isCloneMaskedForce(data)) {
			executeCloneMaskedForce(data);
			
		}else if(isCloneMaskedMove(data)) {
			executeCloneMaskedMove(data);
			
			
			
		}else if(isCloneFilteredNormal(data)) {
			executeCloneFilteredNormal(data);
			
		}else if(isCloneFilteredForce(data)) {
			executeCloneFilteredForce(data);
			
		}else if(isCloneFilteredMove(data)) {
			executeCloneFilteredMove(data);
		}else if(isTestForBlock(data)) {
			executeTestForBlock(data);
			
			
		}else if(isTestForBlocksAll(data)) {
			executeTestForBlocksAll(data);
			
		}else if(isTestForBlocksMasked(data)) {
			executeTestForBlocksMasked(data);
			
			
		}else if(isgetPositionX(data)) {

			
		}else if(isgetPositionY(data)) {

			
		}else if(isgetPositionZ(data)) {
			
			
		}else if(isScanf(data)) {
			//scanfの場合は飛ばす
			
		}else {
			//Sotuken.instance.moduleManager.addRunChatMessage(data.toString());
		}
	
	}
	
	
	
	private boolean isCloneFilteredNormal(String line) {
		return line.startsWith("999999983,");
	}
	
	private boolean isCloneFilteredForce(String line) {
		return line.startsWith("999999982,");
	}
	
	private boolean isCloneFilteredMove(String line) {
		return line.startsWith("999999981,");
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
	
	private boolean isScanf(String line) {
		return line.startsWith("999999919");
		
	}
	
	
	private boolean isgetPositionX(String line) {
		return line.startsWith("999999939");
	}
	
	private boolean isgetPositionY(String line) {
		return line.startsWith("999999938");
	}
	
	private boolean isgetPositionZ(String line) {
		return line.startsWith("999999937");
	}
	
	
	
	
	
	private void executeCloneFilteredNormal(String line) {
		try {
	        // lineをカンマで分割
	        String[] parts = line.split(",");
	        if (parts.length == 12) {
	            int x1 = Integer.parseInt(parts[1]);
	            int y1 = Integer.parseInt(parts[2]);
	            int z1 = Integer.parseInt(parts[3]);
	            int x2 = Integer.parseInt(parts[4]);
	            int y2 = Integer.parseInt(parts[5]);
	            int z2 = Integer.parseInt(parts[6]);
	            int x = Integer.parseInt(parts[7]);
	            int y = Integer.parseInt(parts[8]);
	            int z = Integer.parseInt(parts[9]);
	            String blockName = parts[10];
	            int blockInfo = Integer.parseInt(parts[11]);
	            
	            int dx=Math.abs(x2-x1);
	            int dy=Math.abs(y2-y1);
	            int dz=Math.abs(z2-z1);
	            
	            int newX=x+dx;
	            int newY=y+dy;
	            int newZ=z+dz;

	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/fill %d %d %d %d %d %d air 0 replace %s %d", 
	                        x,y,z,newX,newY,newZ,blockName,blockInfo)
	                )
	            );

	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneFilteredNormal関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneFilteredNormal関数解析: " + e.getMessage());
	    }
	}
	
	private void executeCloneFilteredForce(String line) {
		try {
	        // lineをカンマで分割
	        String[] parts = line.split(",");
	        if (parts.length == 12) {
	            int x1 = Integer.parseInt(parts[1]);
	            int y1 = Integer.parseInt(parts[2]);
	            int z1 = Integer.parseInt(parts[3]);
	            int x2 = Integer.parseInt(parts[4]);
	            int y2 = Integer.parseInt(parts[5]);
	            int z2 = Integer.parseInt(parts[6]);
	            int x = Integer.parseInt(parts[7]);
	            int y = Integer.parseInt(parts[8]);
	            int z = Integer.parseInt(parts[9]);
	            String blockName = parts[10];
	            int blockInfo = Integer.parseInt(parts[11]);

	            int dx=Math.abs(x2-x1);
	            int dy=Math.abs(y2-y1);
	            int dz=Math.abs(z2-z1);
	            
	            int newX=x+dx;
	            int newY=y+dy;
	            int newZ=z+dz;

	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/fill %d %d %d %d %d %d air 0 replace %s %d", 
	                        x,y,z,newX,newY,newZ,blockName,blockInfo)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneFilteredForce関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneFilteredForce関数解析: " + e.getMessage());
	    }
	}
	
	private void executeCloneFilteredMove(String line) {
		try {
	        // lineをカンマで分割
	        String[] parts = line.split(",");
	        if (parts.length == 12) {
	            int x1 = Integer.parseInt(parts[1]);
	            int y1 = Integer.parseInt(parts[2]);
	            int z1 = Integer.parseInt(parts[3]);
	            int x2 = Integer.parseInt(parts[4]);
	            int y2 = Integer.parseInt(parts[5]);
	            int z2 = Integer.parseInt(parts[6]);
	            int x = Integer.parseInt(parts[7]);
	            int y = Integer.parseInt(parts[8]);
	            int z = Integer.parseInt(parts[9]);
	            String blockName = parts[10];
	            int blockInfo = Integer.parseInt(parts[11]);

	            int dx=Math.abs(x2-x1);
	            int dy=Math.abs(y2-y1);
	            int dz=Math.abs(z2-z1);
	            
	            int newX=x+dx;
	            int newY=y+dy;
	            int newZ=z+dz;
	            
	            int minX = Math.min(x1, x2);
	            int minY = Math.min(y1, y2);
	            int minZ = Math.min(z1, z2);

	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/clone %d %d %d %d %d %d %d %d %d filtered move %s %d", 
	                        x,y,z,newX,newY,newZ,minX,minY,minZ,blockName,blockInfo)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneFilteredMove関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneFilteredMove関数解析: " + e.getMessage());
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private boolean isCloneMaskedNormal(String line) {
		return line.startsWith("999999986,");
	}
	
	private boolean isCloneMaskedForce(String line) {
		return line.startsWith("999999985,");
	}
	
	private boolean isCloneMaskedMove(String line) {
		return line.startsWith("999999984,");
	}
	
	
	
	
	private void executeCloneMaskedNormal(String line) {
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

	            int dx=Math.abs(x2-x1);
	            int dy=Math.abs(y2-y1);
	            int dz=Math.abs(z2-z1);
	            
	            int newX=x+dx;
	            int newY=y+dy;
	            int newZ=z+dz;
	            		

	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/fill %d %d %d %d %d %d air 0", 
	                        x,y,z,newX,newY,newZ)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneMaskedNormal関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneMaskedNormal関数解析: " + e.getMessage());
	    }
	}
	
	private void executeCloneMaskedForce(String line) {
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

	            int dx=Math.abs(x2-x1);
	            int dy=Math.abs(y2-y1);
	            int dz=Math.abs(z2-z1);
	            
	            int newX=x+dx;
	            int newY=y+dy;
	            int newZ=z+dz;
	            		

	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/fill %d %d %d %d %d %d air 0", 
	                        x,y,z,newX,newY,newZ)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneMaskedForce関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneMaskedForce関数解析: " + e.getMessage());
	    }
	}
	
	
	private void executeCloneMaskedMove(String line) {
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

	            int dx=Math.abs(x2-x1);
	            int dy=Math.abs(y2-y1);
	            int dz=Math.abs(z2-z1);
	            
	            int newX=x+dx;
	            int newY=y+dy;
	            int newZ=z+dz;
	            
	            int minX = Math.min(x1, x2);
	            int minY = Math.min(y1, y2);
	            int minZ = Math.min(z1, z2);

	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/clone %d %d %d %d %d %d %d %d %d replace move", 
	                        x,y,z,newX,newY,newZ,minX,minY,minZ)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneMaskedMove関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneMaskedMove関数解析: " + e.getMessage());
	    }
	}
	
		
	
	
	
	
	
	
	private boolean isCloneReplaceMove(String line) {
		return line.startsWith("999999987,");
	}
	
	private void executeCloneReplaceMove(String line) {
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
	            
	            
	            int dx=Math.abs(x2-x1);
	            int dy=Math.abs(y2-y1);
	            int dz=Math.abs(z2-z1);
	            
	            int newX=x+dx;
	            int newY=y+dy;
	            int newZ=z+dz;
	            
	            int minX = Math.min(x1, x2);
	            int minY = Math.min(y1, y2);
	            int minZ = Math.min(z1, z2);
	            
	            	            
	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/clone %d %d %d %d %d %d %d %d %d replace move", 
	                        x,y,z,newX,newY,newZ,minX,minY,minZ)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneReplaceMove関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneReplaceMove関数解析: " + e.getMessage());
	    }
	}
	
	
	
	
	
	private boolean isCloneReplaceForce(String line) {
		return line.startsWith("999999988,");
	}
	
	private void executeCloneReplaceForce(String line) {
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

	            int dx=Math.abs(x2-x1);
	            int dy=Math.abs(y2-y1);
	            int dz=Math.abs(z2-z1);
	            
	            int newX=x+dx;
	            int newY=y+dy;
	            int newZ=z+dz;
	            		

	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/fill %d %d %d %d %d %d air 0", 
	                        x,y,z,newX,newY,newZ)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneReplaceForce関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneReplaceForce関数解析: " + e.getMessage());
	    }
	}
	
	
	
	
	
	private boolean isCloneReplaceNormal(String line) {
		return line.startsWith("999999989,");
	}
	
	
	private void executeCloneReplaceNormal(String line) {
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
	            
	            int dx=Math.abs(x2-x1);
	            int dy=Math.abs(y2-y1);
	            int dz=Math.abs(z2-z1);
	            
	            int newX=x+dx;
	            int newY=y+dy;
	            int newZ=z+dz;
	            		

	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/fill %d %d %d %d %d %d air 0", 
	                        x,y,z,newX,newY,newZ)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneReplaceNormal関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneReplaceNormal関数解析: " + e.getMessage());
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
	
	
	
	private boolean isSetBlockKeep(String line) {
		return line.startsWith("999999998,");
	}
	
	
	private void executeSetBlockKeep(String line) {
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
                    		String.format("/fill %d %d %d %d %d %d air 0 replace %s %d"
                    				,x,y,z,x,y,z,blockName,blockInfo)
                    )
				);
			}else {
				Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:setBlockKeep関数の形式が違います");
			}
		}catch (NumberFormatException e) {
			Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:setBlockKeep関数解析: "+e.getMessage());
		}
	}	
	
	
	
	
	
	private boolean isSetBlockDestroy(String line) {
		return line.startsWith("999999997,");
	}
	
	
	private void executeSetBlockDestroy(String line) {
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
                    		String.format("/setblock %d %d %d air 0 destroy",x,y,z)
                    )
				);
			}else {
				Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:setBlockDestroy関数の形式が違います");
			}
		}catch (NumberFormatException e) {
			Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:setBlockDestroy関数解析: "+e.getMessage());
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
	                        x1, y1, z1, x2, y2, z2,blockName2,blockInfo2,blockName1, blockInfo1)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillReplaceBlock関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillReplaceBlock関数解析: " + e.getMessage());
	    }
	}
	
	private boolean isFillDestroy(String line) {
		return line.startsWith("999999996,");
	}
	
	
	//fill系を実行
		private void executeFillDestroy(String line) {
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
		                        String.format("/fill %d %d %d %d %d %d air 0 destroy", 
		                        x1, y1, z1, x2, y2, z2)
		                )
		            );
		        } else {
		            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillDestroy関数の形式が違います");
		        }
		    } catch (NumberFormatException e) {
		        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillDestroy関数解析: " + e.getMessage());
		    }
		}
		
		
		private boolean isFillKeep(String line) {
			return line.startsWith("999999995,");
		}
		
		private void executeFillKeep(String line) {
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
		            String blockName = parts[7];
		            int blockInfo = Integer.parseInt(parts[8]);

		            // fillコマンドをマイクラのチャットに送信
		            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
		                new C01PacketChatMessage(
		                        String.format("/fill %d %d %d %d %d %d air 0 replace %s %d ", 
		                        x1, y1, z1, x2, y2, z2, blockName, blockInfo)
		                )
		            );
		        } else {
		            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillKeep関数の形式が違います");
		        }
		    } catch (NumberFormatException e) {
		        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillKeep関数解析: " + e.getMessage());
		    }
		}
		
		
		
		private boolean isFillHollow(String line) {
			return line.startsWith("999999994,");
		}
		
		private void executeFillHollow(String line) {
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
		                        String.format("/fill %d %d %d %d %d %d air 0 hollow", 
		                        x1, y1, z1, x2, y2, z2)
		                )
		            );
		        } else {
		            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillHollow関数の形式が違います");
		        }
		    } catch (NumberFormatException e) {
		        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillHollow関数解析: " + e.getMessage());
		    }
		}
		
		
		
		
		
		
		
		
		private boolean isFillOutline(String line) {
			return line.startsWith("999999993,");
		}
		
		private void executeFillOutline(String line) {
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
//		            String blockName = parts[7];
		            //int blockInfo = Integer.parseInt(parts[8]);

		            // fillコマンドをマイクラのチャットに送信
		            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
		                new C01PacketChatMessage(
		                        String.format("/fill %d %d %d %d %d %d air 0 outline", 
		                        x1, y1, z1, x2, y2, z2)
		                )
		            );
		        } else {
		            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillOutline関数の形式が違います");
		        }
		    } catch (NumberFormatException e) {
		        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillOutline関数解析: " + e.getMessage());
		    }
		}
		
		
}