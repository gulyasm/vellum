package vellum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vellum {

	private static final Pattern anonymousClass = Pattern.compile("\\$\\d+$");
	private static final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
	private static final String DEBUG_TAG = "DEBUG";
	private static final String INFO_TAG = "INFO";
	private static final String WARNING_TAG = "WARNING";
	private static final String ERROR_TAG = "ERROR";
	public static final int DEBUG = 1;
	public static final int INFO = 2;
	public static final int WARNING = 3;
	public static final int ERROR = 4;
	public static final int OFF = Integer.MAX_VALUE;
	private static int LEVEL = DEBUG;

	private String className() {
		String className = Thread.currentThread().getStackTrace()[4].getClassName();
		Matcher m = anonymousClass.matcher(className);
		if (m != null && m.find()) {
			className = m.replaceAll("");
		}
		return className.substring(className.lastIndexOf('.') + 1);
	}

	private static void log(String tag, String lvltag, int lvl, String msg) {
		if (lvl < LEVEL) return;
		System.out.println(String.format(
				"%-13s %-6s %-20s %-30s",
				formatter.format(new Date()),
				lvltag,
				tag,
				msg));
	}

	public void i(String message) {
		log(className(), INFO_TAG, INFO, message);
	}

	public void d(String message) {
		log(className(), DEBUG_TAG, DEBUG, message);
	}

	public void w(String message) {
		log(className(), WARNING_TAG, WARNING, message);
	}

	public void e(String message) {
		log(className(), ERROR_TAG, ERROR, message);
	}

}
