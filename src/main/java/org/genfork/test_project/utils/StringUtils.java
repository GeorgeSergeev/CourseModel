package org.genfork.test_project.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * User: <a href="http://gencloud.solutions">GenCloud</a>
 * Date: 2017/08
 */
public class StringUtils {
	public static <T> String ToJson(Object o, String opcode) {
		final Gson generator = new GsonBuilder().setPrettyPrinting().setLenient().disableHtmlEscaping().create();
		return opcode + generator.toJson(o);
	}
}
