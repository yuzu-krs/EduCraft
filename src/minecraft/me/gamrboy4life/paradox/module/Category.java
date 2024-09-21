package me.gamrboy4life.paradox.module;

public enum Category {
	DESCRIBE("概要"),
	RUN("C言語"),
	EDITOR("エディタ"),
	GRAMMAR("基本文法"),
	STATUS("ステータス"),
	MISC("その他");
	
	
	
	
	public String name;
	public int moduleIndex;
	
	Category(String name){
		this.name=name;
	}

}
