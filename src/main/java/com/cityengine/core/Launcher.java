package com.cityengine.core;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Launcher {
	private long window;
	
	public static void main(String[] args) {
		new Launcher().run();
	}
	
	public void run() {
		System.out.println("Avvio City Engine...");
		
		init();
		loop();
		
		// Cleaning
		GLFW.glfwDestroyWindow(window);
		GLFW.glfwTerminate();
		GLFW.glfwSetErrorCallback(null).free();
	}
	
	private void init() {
		// --- 1. Errors managing setup ---
		GLFWErrorCallback.createPrint(System.err).set();
		
		// --- 2. GLFW initialization ---
		if (!GLFW.glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		
		// --- 3. Window configuration ---
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
		
		// --- 4. Window creation ---
		window = GLFW.glfwCreateWindow(1280, 720, "City Builder Engine - v0.1", 0, 0);
		if (window == 0) {
			throw new RuntimeException("Creation of GLFW window failed");
		}
		
		// --- 5. OpenGL context ---
		GLFW.glfwMakeContextCurrent(window);
		
		// --- 6. V-Sync ---
		GLFW.glfwSwapInterval(1);
		
		// --- 7. Show window ---
		GLFW.glfwShowWindow(window);
	}
	
	private void loop() {
		GL.createCapabilities();
		
		GL11.glClearColor(0.1f, 0.2f, 0.3f, 1.0f);
		
		// --- Game Loop ---
		while (!GLFW.glfwWindowShouldClose(window)) {
			// Clean the screen every frame
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
			GLFW.glfwSwapBuffers(window);
			
			// Listen for events
			GLFW.glfwPollEvents();
		}
	}
}
