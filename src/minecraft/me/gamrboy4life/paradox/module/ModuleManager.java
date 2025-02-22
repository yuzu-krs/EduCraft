package me.gamrboy4life.paradox.module;

import java.util.ArrayList;
import java.util.List;

import me.gamrboy4life.paradox.Sotuken;
import me.gamrboy4life.paradox.module.c.KillProcess;
import me.gamrboy4life.paradox.module.c.Run;
import me.gamrboy4life.paradox.module.c.UndoBlock;
import me.gamrboy4life.paradox.module.describe.EduCraft;
import me.gamrboy4life.paradox.module.editor.Notepad;
import me.gamrboy4life.paradox.module.editor.VSCode;
import me.gamrboy4life.paradox.module.grammar.Clone;
import me.gamrboy4life.paradox.module.grammar.Fill;
import me.gamrboy4life.paradox.module.grammar.GetPosition;
import me.gamrboy4life.paradox.module.grammar.Mscanf;
import me.gamrboy4life.paradox.module.grammar.SendCommand;
import me.gamrboy4life.paradox.module.grammar.SetBlock;
import me.gamrboy4life.paradox.module.grammar.Summon;
import me.gamrboy4life.paradox.module.grammar.TestForBlock;
import me.gamrboy4life.paradox.module.grammar.TestForBlocks;
import me.gamrboy4life.paradox.module.misc.CopyBlockID;
import me.gamrboy4life.paradox.module.misc.CopyLookingAt;
import me.gamrboy4life.paradox.module.misc.CopyXYZ;
import me.gamrboy4life.paradox.module.status.BlockID;
import me.gamrboy4life.paradox.module.status.Direction;
import me.gamrboy4life.paradox.module.status.LookingAt;
import me.gamrboy4life.paradox.module.status.TabGui;
import me.gamrboy4life.paradox.module.status.XYZ;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class ModuleManager {
	
	private static ArrayList<Module> mods;
	
	public ModuleManager() {
		mods=new ArrayList<Module>();
		
		newMod(new TabGui());
		
		
		//DESCRIBE
		newMod(new EduCraft());
		
		
		//RUN
;
		newMod(new Run());
		newMod(new KillProcess());
		newMod(new UndoBlock());
		
		
		
//		newMod(new ClickGui());
		
		//エディタ
		newMod(new Notepad());
		newMod(new VSCode());
		
		
		//文法情報
		newMod(new Clone());
		newMod(new Fill());	
		newMod(new GetPosition());
		newMod(new Mscanf());
		newMod(new SendCommand());
		newMod(new SetBlock());
		newMod(new Summon());
		newMod(new TestForBlock());
		newMod(new TestForBlocks());
		
		
		
	
		//Status
		newMod(new BlockID());
		newMod(new XYZ());
		newMod(new LookingAt());
		newMod(new Direction());
		
		
		

		//newMod(new Panic());
		
		//MISC
		newMod(new CopyBlockID());
		newMod(new CopyXYZ());
		newMod(new CopyLookingAt());

		

		
		
		
	}
	
	
	public static List<Module> getModulesbyCategory(Category c){
		List<Module> modules=new ArrayList<Module>();
		
		for(Module m:Sotuken.instance.moduleManager.getModules()) {
			if(m.category==c) {
				modules.add(m);
			}
		}
		return modules;
		
	}
	
	
	
	public static void newMod(Module m) {
		mods.add(m);
	}
	
	public static ArrayList<Module> getModules(){
		return mods;
	}
	
	public static void onUpdate() {
		for(Module m:mods) {
			m.onUpdate();
		}
	}
	
	public static void onRender() {
		for(Module m:mods) {
			m.onRender();
		}
	}
	
	public static void onKey(int k) {
		for(Module m:mods) {
			if(m.getKey()==k) {
				m.toggle();
			}
		}
	}

	
	public static void addChatMessage(String message) {
		message="\u00A7e"+Sotuken.name+"\2477: "+message;
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
	}
	
	//コンパイルエラーの場合
	public static void addErrChatMessage(String message) {
	    message = "\u00A7e" + Sotuken.name + "\u00A77: " + "\u00A7c" + message;
	    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
	}
	
	//実行のエラーの場合
	public static void addErrRunChatMessage(String message) {
	    message = "\u00A7e" + Sotuken.name + "\u00A77: " + "\u00A7c" + message;
	    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
	}
	
	//実行結果の場合
	public static void addRunChatMessage(String message) {
	    message = "\u00A7e" + Sotuken.name + "\u00A77: " + "\u00A7f" + message;
	    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
	}
		
		
	
	public static Module getModuleByName(String moduleName) {
		for(Module m:Sotuken.instance.moduleManager.getModules()) {
			if(!m.getName().trim().equalsIgnoreCase(moduleName)&&!m.toString().equalsIgnoreCase(moduleName.trim())) continue;
			return m;
		}
		return null;
	}
	
	
	
	
	
	
	
	
}
