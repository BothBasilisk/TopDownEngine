package com.cityengine.core.window;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class WindowManager {
	private final String title;
	private int width, height;
	private long windowHandle;
	private boolean vSync;
	
	public WindowManager(String title, int width, int height, boolean vSync) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.vSync = vSync;
	}
	
	public void init() {
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
		
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE);
		
		// --- 4. Window creation ---
		windowHandle = GLFW.glfwCreateWindow(1280, 720, "City Builder Engine - v0.1", 0, 0);
		if (windowHandle == 0) {
			throw new RuntimeException("Creation of GLFW window failed");
		}
		
		// --- Resize ---
		GLFW.glfwSetFramebufferSizeCallback(windowHandle, (window, w, h) -> {
			this.width = w;
			this.height = h;
		});
		
		// --- 5. OpenGL context ---
		GLFW.glfwMakeContextCurrent(windowHandle);
		
		// --- 6. V-Sync ---
		if (isvSync()) {
			GLFW.glfwSwapInterval(1);
		} else {
			GLFW.glfwSwapInterval(0);
		}
		
		// --- 7. Show window ---
		GLFW.glfwShowWindow(windowHandle);
		
		GL.createCapabilities();
		GL11.glClearColor(0.1f, 0.2f, 0.3f, 1.0f);
	}
	
	public void update() {
		GLFW.glfwSwapBuffers(windowHandle);
		GLFW.glfwPollEvents();
	}
	
	public void cleanup() {
		GLFW.glfwDestroyWindow(windowHandle);
	}
	
	public boolean windowShouldClose() {
		return GLFW.glfwWindowShouldClose(windowHandle);
	}
	
	public boolean isKeyPressed(int keycode) {
		return GLFW.glfwGetKey(windowHandle, keycode) == GLFW.GLFW_PRESS;
	}
	
	public String getTitle() { return title; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public boolean isvSync() { return vSync; }
}
