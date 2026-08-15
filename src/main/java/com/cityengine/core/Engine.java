package com.cityengine.core;

import org.lwjgl.glfw.GLFW;

import com.cityengine.core.window.WindowManager;

public class Engine implements Runnable {
	private final WindowManager window;
	private final IGameLogic gameLogic;
	private final Thread gameLogicThread;
	
	public Engine(String windowTitle, int width, int height, boolean vSync, IGameLogic gameLogic) {
		this.gameLogicThread = new Thread(this, "GAME_ENGINE_THREAD");
		this.window = new WindowManager(windowTitle, width, height, vSync);
		this.gameLogic = gameLogic;
	}
	
	public void start() {
		gameLogicThread.start();
	}
	
	@Override
	public void run() {
		try {
            init();
            loop();
        } catch (Exception excp) {
            excp.printStackTrace();
        } finally {
            cleanup();
        }
	}
	
	private void init() throws Exception {
        window.init();
        gameLogic.init(window);
    }
	
	private void loop() {
		long lastTime = System.nanoTime();
		
		while (!window.windowShouldClose()) {
			long currentTime = System.nanoTime();
			
			float deltaTime = (currentTime - lastTime) / 1_000_000_000.0f;
			deltaTime = deltaTime > 0.05f ? 0.05f : deltaTime;
			lastTime = currentTime;
			
			// --- 1. Reading Inputs ---
			gameLogic.input(window);
			
			// --- 2. Update every frame ---
			gameLogic.update(deltaTime);
			
			// --- 3. Render ---
			gameLogic.render(window);
			
			window.update();
		}
	}
	
	private void cleanup() {
        gameLogic.cleanup();
        window.cleanup();
        GLFW.glfwTerminate();
    }
}
