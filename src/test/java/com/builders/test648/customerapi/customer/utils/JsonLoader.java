package com.builders.test648.customerapi.customer.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.util.StreamUtils;

public class JsonLoader {

	public static String getJsonFromFilename(String filename) {
		try {
			InputStream stream = JsonLoader.class.getResourceAsStream(filename);
			String json = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
			return json;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
