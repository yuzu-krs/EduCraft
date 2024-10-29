package me.gamrboy4life.paradox.command.commands;

import me.gamrboy4life.paradox.command.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class Scanf extends Command {

    private static String inputText = ""; // 入力文字列を保存する静的フィールド

    public Scanf() {
        super("Scanf", "Cの入力待ちに対してマイクラチャットから入力", "scanf", "s");
    }

    @Override
    public void onCommand(String[] args, String command) {
        inputText = String.join(" ", args); // 入力文字列を保存
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(inputText));
    }

    // 入力文字列を取得する静的メソッド
    public static String getInputText() {
        return inputText;
    }

    // 入力文字列をクリアする静的メソッド
    public static void clearInputText() {
        inputText = "";
    }
}
