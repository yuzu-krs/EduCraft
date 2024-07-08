package de.Hero.clickgui.util;

import java.awt.Color;

import me.gamrboy4life.paradox.Sotuken;

/**
 *  Made by HeroCode
 *  it's free to use
 *  but you have to credit me
 *
 *  @author HeroCode
 */
public class ColorUtil {
	
	public static Color getClickGUIColor(){
		return new Color((int)Sotuken.instance.settingsManager.getSettingByName("GuiRed").getValDouble(), (int)Sotuken.instance.settingsManager.getSettingByName("GuiGreen").getValDouble(), (int)Sotuken.instance.settingsManager.getSettingByName("GuiBlue").getValDouble());
	}
}
