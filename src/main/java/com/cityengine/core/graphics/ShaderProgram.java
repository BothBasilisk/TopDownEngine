package com.cityengine.core.graphics;

import org.lwjgl.opengl.GL20;

public class ShaderProgram {
	private final String VERTEX_SHADER;
	private final String FRAGMENT_SHADER;
	
	private int programId;
	private int vertexId;
	private int fragmentId;
	
	public ShaderProgram(String vertexShader, String fragmentShader) {
		this.VERTEX_SHADER = vertexShader;
		this.FRAGMENT_SHADER = fragmentShader;
	}
	
	public void init() {
		// --- 1. Create Vertex shader ---
		vertexId = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		GL20.glShaderSource(vertexId, VERTEX_SHADER);
		GL20.glCompileShader(vertexId);
		if(GL20.glGetShaderi(vertexId, GL20.GL_COMPILE_STATUS) == 0) {
			throw new RuntimeException("Error while compiling Vertex Shader: " + GL20.glGetShaderInfoLog(vertexId));
		}
		
		// --- 2. Create Fragment shader ---
		fragmentId = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
		GL20.glShaderSource(fragmentId, FRAGMENT_SHADER);
		GL20.glCompileShader(fragmentId);
		if(GL20.glGetShaderi(fragmentId, GL20.GL_COMPILE_STATUS) == 0) {
			throw new RuntimeException("Error while compiling Fragment Shader: " + GL20.glGetShaderInfoLog(fragmentId));
		}
		
		// --- 3. Create shader program ---
		programId = GL20.glCreateProgram();
		GL20.glAttachShader(programId, vertexId);
		GL20.glAttachShader(programId, fragmentId);
		GL20.glLinkProgram(programId);
		if(GL20.glGetProgrami(programId, GL20.GL_LINK_STATUS) == 0) {
			System.err.println("Error while linking shader program");
			System.err.println(GL20.glGetProgramInfoLog(programId));
		}
	}
	
	public void bind() {
		GL20.glUseProgram(programId);
	}
	
	public void unbind() {
		GL20.glUseProgram(0);
	}
	
	public void cleanup() {
		GL20.glDetachShader(programId, vertexId);
		GL20.glDetachShader(programId, fragmentId);
		
		GL20.glDeleteShader(vertexId);
		GL20.glDeleteShader(fragmentId);
		
		GL20.glDeleteProgram(programId);
	}
}
