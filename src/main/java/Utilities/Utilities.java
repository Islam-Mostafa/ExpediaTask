package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.WebElement;

public class Utilities {
	public static String generateRandomChars(int length) {
		String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
		}

		return sb.toString();
	}

	public static List<String> getTextFromWebElements(List<WebElement> elements) {
		List<String> names = new ArrayList<>();
		for (WebElement e : elements) {
			names.add(e.getText());
		}
		return names;
	}

	public static Map<String, String> getApplicationData() {
		Map<String,String> applicationData = new HashMap<String, String>();
		Properties prop = new Properties();
		InputStream input = null;
		String url = null;
		String browserName = null;
		try {

			input = new FileInputStream("src/test/resources/Configs/application.properties");

			prop.load(input);

			// get the property value and print it out
			url = prop.getProperty("url");
			browserName = prop.getProperty("browser");
			applicationData.put("Browser", browserName);
			applicationData.put("Url", url);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return applicationData;
	}
}
