package com.cityengine.core;

import com.cityengine.core.window.WindowManager;

public interface IGameLogic {
	void init(WindowManager window) throws Exception;
	
	void input(WindowManager window);
	
	void update(float deltaTime);
	
	void render(WindowManager window);
	
	void cleanup();
}
