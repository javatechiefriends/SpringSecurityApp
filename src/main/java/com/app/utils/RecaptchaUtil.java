package com.app.utils;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RecaptchaUtil {

	public String generateRecap() {

		String str = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789";
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i <= 5; i++) {
			sb.append(str.charAt(random.nextInt(34)));
		}
		return new String(sb);
	}
}
