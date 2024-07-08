package me.gamrboy4life.paradox.module;

public enum Category {
	RUN("C言語"),
	Editor("エディタ"),
	STATUS("ステータス");
	
	
	
	
	public String name;
	public int moduleIndex;
	
	Category(String name){
		this.name=name;
	}

}
