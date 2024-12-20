package me.gamrboy4life.paradox.analysis;
import me.gamrboy4life.paradox.Sotuken;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class DataParser {
	
	public void parseData(String data) {
		
		if(isSetBlockReplace(data)) {
			executeSetBlockReplace(data);
			
		}else if(isSetBlockKeep(data)) {
			executeSetBlockKeep(data);
			
		}else if(isSetBlockDestroy(data)) {
			executeSetBlockDestroy(data);

			
			
			
		}else if(isFillDestroy(data)) {
			executeFillDestroy(data);
			
		}else if(isFillKeep(data)) {
			executeFillKeep(data);
			
		}else if(isFillHollow(data)) {
			executeFillHollow(data);
			
		}else if(isFillOutline(data)) {
			executeFillOutline(data);
			
		}else if(isFillReplace(data)) {
			executeFillReplace(data);
		
		}else if(isFillReplaceBlock(data)) {
			executeFillReplaceBlock(data);
			
			
			
			
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
			
			
			
			
		}else if(isSummon(data)) {
			executeSummon(data);
		
			
			
			
		}else if(isSendCommand(data)) {
			executeSendCommand(data);
			
			
			
			
			
		}else if(isTestForBlock(data)) {
			executeTestForBlock(data);
			
		}else if(isErr(data)) {
			executeErr(data);
			
			
			
			
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
			Sotuken.instance.moduleManager.addRunChatMessage(data.toString());
		}
	
	}
	

	//関数を判定
	
	private boolean isSetBlockReplace(String line) {
		return line.startsWith("999999999,");
	}
	
	private boolean isSetBlockKeep(String line) {
		return line.startsWith("999999998,");
	}
	
	private boolean isSetBlockDestroy(String line) {
		return line.startsWith("999999997,");
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
                    		String.format("/setblock %d %d %d %s %d keep",x,y,z,blockName,blockInfo)
                    )
				);
			}else {
				Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:setBlockKeep関数の形式が違います");
			}
		}catch (NumberFormatException e) {
			Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:setBlockKeep関数解析: "+e.getMessage());
		}
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
				String blockName=parts[4];
				int blockInfo=Integer.parseInt(parts[5]);
				
				//setblockコマンドをマイクラのチャットに送信
				Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
                    new C01PacketChatMessage(
                    		String.format("/setblock %d %d %d %s %d destroy",x,y,z,blockName,blockInfo)
                    )
				);
			}else {
				Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:setBlockDestroy関数の形式が違います");
			}
		}catch (NumberFormatException e) {
			Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:setBlockDestroy関数解析: "+e.getMessage());
		}
	}
	
	
	
	
	
	
	//fill系関数を判定
	private boolean isFillDestroy(String line) {
		return line.startsWith("999999996,");
	}
	
	private boolean isFillKeep(String line) {
		return line.startsWith("999999995,");
	}
	
	private boolean isFillHollow(String line) {
		return line.startsWith("999999994,");
	}
	
	private boolean isFillOutline(String line) {
		return line.startsWith("999999993,");
	}
	
	private boolean isFillReplace(String line) {
		return line.startsWith("999999992,");
	}
	
	private boolean isFillReplaceBlock(String line) {
		return line.startsWith("999999991,");
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
	            String blockName = parts[7];
	            int blockInfo = Integer.parseInt(parts[8]);

	            // fillコマンドをマイクラのチャットに送信
	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/fill %d %d %d %d %d %d %s %d destroy", 
	                        x1, y1, z1, x2, y2, z2, blockName, blockInfo)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillDestroy関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillDestroy関数解析: " + e.getMessage());
	    }
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
	                        String.format("/fill %d %d %d %d %d %d %s %d keep", 
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
	            String blockName = parts[7];
	            int blockInfo = Integer.parseInt(parts[8]);

	            // fillコマンドをマイクラのチャットに送信
	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/fill %d %d %d %d %d %d %s %d hollow", 
	                        x1, y1, z1, x2, y2, z2, blockName, blockInfo)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillHollow関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillHollow関数解析: " + e.getMessage());
	    }
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
	            String blockName = parts[7];
	            int blockInfo = Integer.parseInt(parts[8]);

	            // fillコマンドをマイクラのチャットに送信
	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/fill %d %d %d %d %d %d %s %d outline", 
	                        x1, y1, z1, x2, y2, z2, blockName, blockInfo)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillOutline関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:fillOutline関数解析: " + e.getMessage());
	    }
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
	            String blockName = parts[7];
	            int blockInfo = Integer.parseInt(parts[8]);

	            // fillコマンドをマイクラのチャットに送信
	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/fill %d %d %d %d %d %d %s %d ", 
	                        x1, y1, z1, x2, y2, z2, blockName, blockInfo)
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
	
	
	
	private boolean isCloneReplaceNormal(String line) {
		return line.startsWith("999999989,");
	}
	
	private boolean isCloneReplaceForce(String line) {
		return line.startsWith("999999988,");
	}
	
	private boolean isCloneReplaceMove(String line) {
		return line.startsWith("999999987,");
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
	
	
	private boolean isCloneFilteredNormal(String line) {
		return line.startsWith("999999983,");
	}
	
	private boolean isCloneFilteredForce(String line) {
		return line.startsWith("999999982,");
	}
	
	private boolean isCloneFilteredMove(String line) {
		return line.startsWith("999999981,");
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

	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/clone %d %d %d %d %d %d %d %d %d replace normal", 
	                        x1, y1, z1, x2, y2, z2, x,y,z)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneReplaceNormal関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneReplaceNormal関数解析: " + e.getMessage());
	    }
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

	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/clone %d %d %d %d %d %d %d %d %d replace force", 
	                        x1, y1, z1, x2, y2, z2, x,y,z)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneReplaceForce関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneReplaceForce関数解析: " + e.getMessage());
	    }
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

	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/clone %d %d %d %d %d %d %d %d %d replace move", 
	                        x1, y1, z1, x2, y2, z2, x,y,z)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneReplaceMove関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneReplaceMove関数解析: " + e.getMessage());
	    }
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

	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/clone %d %d %d %d %d %d %d %d %d masked normal", 
	                        x1, y1, z1, x2, y2, z2, x,y,z)
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

	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/clone %d %d %d %d %d %d %d %d %d masked force", 
	                        x1, y1, z1, x2, y2, z2, x,y,z)
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

	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/clone %d %d %d %d %d %d %d %d %d masked move", 
	                        x1, y1, z1, x2, y2, z2, x,y,z)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneMaskedMove関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneMaskedMove関数解析: " + e.getMessage());
	    }
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

	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/clone %d %d %d %d %d %d %d %d %d filtered normal %s %d", 
	                        x1, y1, z1, x2, y2, z2, x,y,z,blockName,blockInfo)
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

	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/clone %d %d %d %d %d %d %d %d %d filtered force %s %d", 
	                        x1, y1, z1, x2, y2, z2, x,y,z,blockName,blockInfo)
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

	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/clone %d %d %d %d %d %d %d %d %d filtered move %s %d", 
	                        x1, y1, z1, x2, y2, z2, x,y,z,blockName,blockInfo)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneFilteredMove関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:cloneFilteredMove関数解析: " + e.getMessage());
	    }
	}
	
	
	
	private boolean isSummon(String line) {
		return line.startsWith("999999979,");
	}
	
	private void executeSummon(String line) {
		try {
	        // lineをカンマで分割
	        String[] parts = line.split(",");
	        if (parts.length >= 6) {
	        	
	        	String entity = parts[1];
	            int x = Integer.parseInt(parts[2]);
	            int y = Integer.parseInt(parts[3]);
	            int z = Integer.parseInt(parts[4]);
	            
	            //parts[5]以降をすべて結合する
	            StringBuilder statusBuilder =new StringBuilder();
	            for(int i=5;i<parts.length;i++) {
	            	if(i>5) {
	            		statusBuilder.append(",");
	            	}
	            	statusBuilder.append(parts[i]);
	            }
	            String status = statusBuilder.toString();
	            	            
	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage(
	                        String.format("/summon %s %d %d %d %s", 
	                        entity,x,y,z,status)
	                )
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:summon関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:summon関数解析: " + e.getMessage());
	    }
	}
	
	
	
	
	private boolean isSendCommand(String line) {
		return line.startsWith("999999969,");
	}
	
	private void executeSendCommand(String line) {
		try {
		
			 // コマンド部分を分割して処理
	        String[] parts = line.split(",", 2);
	        if (parts.length == 2) {
	            String command = parts[1];  // コマンド部分を取得
	            
	            // Minecraftのチャットにコマンドを送信
	            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
	                new C01PacketChatMessage("/"+command)
	            );
	        } else {
	            Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:sendCommand関数の形式が違います");
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:sendCommand関数解析: " + e.getMessage());
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
	
	
	private boolean isErr(String line) {
		return line.startsWith("-999999959,");
	}
	
	private void executeErr(String line) {
		try {
		
			 // lineをカンマで分割
	        String[] parts = line.split(",");
	        if (parts.length == 2) {
	            String err = parts[1];
	        
	            Sotuken.instance.moduleManager.addErrRunChatMessage(String.format("%s", err));
	        }
	    } catch (NumberFormatException e) {
	        Sotuken.instance.moduleManager.addErrRunChatMessage("エラー:testForBlock系関数解析: " + e.getMessage());
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
	
	
    
	
	
}