package me.gamrboy4life.paradox.command.commands;

import me.gamrboy4life.paradox.command.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class Chat extends Command{

	public Chat() {
		super("Chat","Chatで「.」を含むメッセージもチャットできる","chat","c");
	}
	
	@Override
	public void onCommand(String[] args,String command) {
		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(String.join(" ", args)));
	}

}