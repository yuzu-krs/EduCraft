package me.gamrboy4life.paradox.mods;

import me.gamrboy4life.paradox.gui.hud.HUDManager;
import me.gamrboy4life.paradox.mods.impl.ModLookingAt;
import me.gamrboy4life.paradox.mods.impl.ModPerspective;
import me.gamrboy4life.paradox.mods.impl.ModXYZ;
import me.gamrboy4life.paradox.mods.impl.DirectionMod.DirectionMod;

public class ModInstances {

    

    private static ModXYZ modXYZ;
    
    private static ModPerspective modPerspective;
    
    private static DirectionMod direction;
    
    private static ModLookingAt lookingAt;


    public static void register(HUDManager api) {
  
        modXYZ=new ModXYZ();
        api.register(modXYZ);

        		
		direction=new DirectionMod();
		api.register(direction);
        
        modPerspective=new ModPerspective();
        api.register(modPerspective);
        
        lookingAt=new ModLookingAt();
        api.register(lookingAt);
    }
    
    public static ModPerspective getModPerspective() {
    	return modPerspective;
    }
    
}