package me.gamrboy4life.paradox.module.status;

import java.util.List;

import org.lwjgl.input.Keyboard;

import me.gamrboy4life.paradox.Sotuken;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;
import me.gamrboy4life.paradox.utils.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class TabGui extends Module{
	
	public int currentTab;
	public boolean expanded;

	public TabGui() {
		super("TabGui",0,Category.STATUS);
		toggled=true;
	}
	
	public void draw() {
		if(this.isToggled()) {
			Gui.drawRect(5, 20, 68, 102, 0xAA1E1E1E);
			Gui.drawRect(8, 23+currentTab*13, 7+58, 34+currentTab*13,0xFF2E8B57);
			
			int count=0;
			
			for(Category c:Category.values()) {
				Wrapper.fr.drawStringWithShadow(c.name,10,25+count*13,0xFFFFFFFF);
				count++;
			}
			
			if(expanded) {
				Category category=Category.values()[currentTab];
				List<Module> modules=Sotuken.instance.moduleManager.getModulesbyCategory(category);
				
				if(modules.size()==0) {
					return;
				}
				
				int maxLenModule=0;
				for(Module module:modules) {
					if(Wrapper.fr.getStringWidth(module.name)>maxLenModule)maxLenModule=Wrapper.fr.getStringWidth(module.name);
				}
				
				Gui.drawRect(68, 20, 48+maxLenModule+30, 25+modules.size()*13, 0xAA1E1E1E);
				Gui.drawRect(70, 23+category.moduleIndex*13, 63+maxLenModule+12, 34+category.moduleIndex*13,0xFF4682B4);
				
				count=0;
				
				for(Module m:modules) {
					Wrapper.fr.drawStringWithShadow(m.name,73, 25+count*13, 0xFFFFFFFF );
					count++;
				}
				
				
			}
			
			
		}
	}
	
	public void keyPressed(int k) {
		Category category=Category.values()[currentTab];
		List<Module> modules=Sotuken.instance.moduleManager.getModulesbyCategory(category);
		switch(k) {
		
		case Keyboard.KEY_UP:
			if(expanded) {
				if(category.moduleIndex<=0) {
					category.moduleIndex=modules.size()-1;
				}else {
					category.moduleIndex--;
				}
			}else {
				if(currentTab<=0) {
					currentTab=Category.values().length-1;
				}else {
					currentTab--;
				}
				
			}
			// 効果音を再生
            Minecraft.getMinecraft().thePlayer.playSound("random.click", 1.0F, 1.0F);

			break;
			
		case Keyboard.KEY_DOWN:
			if(expanded) {
				if(category.moduleIndex>=modules.size()-1) {
					category.moduleIndex=0;
				}else {
					category.moduleIndex++;
				}
			}else {
				if(currentTab>=Category.values().length-1) {
					currentTab=0;
				}else {
					currentTab++;
				}
			}
			
			// 効果音を再生
            Minecraft.getMinecraft().thePlayer.playSound("random.click", 1.0F, 1.0F);

			break;
			
		case Keyboard.KEY_RIGHT:
			if(expanded&&modules.size()!=0) {
				Module module=modules.get(category.moduleIndex);
				if(!module.name.equals("TabGui"))
					module.toggle();
				// モジュールトグル時の効果音
                Minecraft.getMinecraft().thePlayer.playSound("random.pop", 1.0F, 1.0F);

			}else {
				if(modules.size()!=0) {
					expanded=true;
					category.moduleIndex=0;
					// タブ展開時の効果音
                    Minecraft.getMinecraft().thePlayer.playSound("random.click", 1.0F, 1.0F);
 				}
			}
			break;
			
		case Keyboard.KEY_LEFT:
			expanded=false;
			// タブ閉じる時の効果音
            Minecraft.getMinecraft().thePlayer.playSound("random.click", 1.0F, 1.0F);
            break;

			
			
			
			
			
		
		}
		
	}

}