package com.cityengine.game;

import org.lwjgl.opengl.GL11;

import com.cityengine.core.IGameLogic;
import com.cityengine.core.window.WindowManager;

public class CityBuilderGame implements IGameLogic {
	private int colorDirection = 1;
	private float colorVal = 0.0f;
	
	@Override
    public void init(WindowManager window) throws Exception {
        System.out.println("City Builder Initialized!");
    }
	
	@Override
    public void input(WindowManager window) {
		//TO-DO Manage key and mouse inputs
	}
	
	@Override
    public void update(float deltaTime) {
		colorVal += colorDirection * 0.5f * deltaTime;
		if (colorVal > 1.0f) {
            colorVal = 1.0f;
            colorDirection = -1;
        } else if (colorVal < 0.0f) {
            colorVal = 0.0f;
            colorDirection = 1;
        }
	}
	
	@Override
    public void render(WindowManager window) {
        GL11.glClearColor(colorVal, 0.2f, 0.3f, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }
	
	@Override
    public void cleanup() {
        System.out.println("Saving... (Nah)");
    }
}
