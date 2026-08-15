package com.cityengine.core.utils;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Utilities {
	public static String loadResource(String filePath) throws Exception {
	    return Files.readString(Paths.get(filePath));
	}
}
