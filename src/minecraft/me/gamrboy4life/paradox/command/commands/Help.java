package me.gamrboy4life.paradox.command.commands;

import me.gamrboy4life.paradox.Sotuken;
import me.gamrboy4life.paradox.command.Command;

public class Help extends Command{
	
	
	public Help() {
		super("Help","コマンドの説明","help","h");
	}
	
	@Override
	public void onCommand(String[] args, String command) {
		
		if(args.length==0) {
			Sotuken.instance.moduleManager.addChatMessage("大文字小文字問いません");
			Sotuken.instance.moduleManager.addChatMessage("キーバインド  .[b|bind] <Mod名> <key>");
			Sotuken.instance.moduleManager.addChatMessage("キーバインド初期化  .[b|bind] clear");
			Sotuken.instance.moduleManager.addChatMessage("Mod即時オン  .[t|toggle] <Mod名>");
			Sotuken.instance.moduleManager.addChatMessage("Chatコマンド  .[c|chat] <任意の文字列>");
			Sotuken.instance.moduleManager.addChatMessage("Scanfコマンド .[s|scanf] <任意の文字列>");

		}else{
			Sotuken.instance.moduleManager.addChatMessage("そのようなモデルはありません! [.help]");
		}
		
	}
}
