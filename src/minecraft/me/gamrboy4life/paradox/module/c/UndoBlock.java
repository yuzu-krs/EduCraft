package me.gamrboy4life.paradox.module.c;

import org.lwjgl.input.Keyboard;

import me.gamrboy4life.paradox.module.Category;
import me.gamrboy4life.paradox.module.Module;

public class UndoBlock extends Module {

    private boolean hasToggled = false;

    public UndoBlock() {
        super("Undo", Keyboard.KEY_U , Category.RUN);
    }

    @Override
    public void onUpdate() {
        // モジュールがトグルされており、処理がまだ実行されていない場合
        if (isToggled() && !hasToggled) {
        	
        	
        	
        	        	
        	
        	
             // 処理が完了した後、フラグをtrueに設定
            hasToggled = true;

            // モジュールのトグルを解除
            toggled = false;
        }
    }


	
	
	
	
	
	@Override
    public void onEnable() {
        super.onEnable();
        hasToggled = false; 
    }

    @Override
    public void onDisable() {
        super.onDisable();
        hasToggled = false; 
    }
}