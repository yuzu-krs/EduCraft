package me.gamrboy4life.paradox.module.status;



import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.Setting;
import me.gamrboy4life.paradox.Sotuken;
import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class ClickGui extends Module{

    public ClickGui() {
        super("ClickGui", Keyboard.KEY_RSHIFT, Category.STATUS);
    }

    @Override
    public void setup() {
        ArrayList<String> options = new ArrayList<String>();
        options.add("New");
        options.add("JellyLike");
        Sotuken.instance.settingsManager.rSetting(new Setting("Design", this, "New", options));
        Sotuken.instance.settingsManager.rSetting(new Setting("Sound", this, false));
        Sotuken.instance.settingsManager.rSetting(new Setting("GuiRed", this, 255, 0, 255, true));
        Sotuken.instance.settingsManager.rSetting(new Setting("GuiGreen", this, 26, 0, 255, true));
        Sotuken.instance.settingsManager.rSetting(new Setting("GuiBlue", this, 42, 0, 255, true));
    }

    @Override
    public void onEnable() {
        super.onEnable();

        mc.displayGuiScreen(Sotuken.instance.clickGUI);
        toggle();
    }
}