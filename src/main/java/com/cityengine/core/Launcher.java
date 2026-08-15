package com.cityengine.core;

import com.cityengine.game.CityBuilderGame;

public class Launcher {
	public static void main(String[] args) {
		IGameLogic game = new CityBuilderGame();
		
		Engine engine = new Engine("My City Builder", 1280, 720, true, game);
		
		engine.start();
	}
}
