package hu.gulyasm.vellum;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Lightweight logging class.
 * 
 * @author Mate Gulyas
 * @version 1.0
 */
public class Vellum {

	private static final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
	/* Level tags */
	private static final String DEBUG_TAG = "DEBUG";
	private static final String INFO_TAG = "INFO";
	private static final String WARNING_TAG = "WARNING";
	private static final String ERROR_TAG = "ERROR";
	/* Level constants */
	/** Debug level constant */
	public static final int DEBUG = 1;
	/** Info level constant */
	public static final int INFO = 2;
	/** Warning level constant */
	public static final int WARNING = 3;
	/** Error level constant */
	public static final int ERROR = 4;
	/** No logging enabled */
	public static final int OFF = Integer.MAX_VALUE;
	/* Current level */
	private static int level = DEBUG;
	/* Instance tag */
	private final String tag;

	public Vellum(String tag) {
		this.tag = tag;
	}

	private static void log(String tag, String lvltag, int lvl, String msg) {
		if (lvl < level) return;
		System.out.println(String.format("%-13s %-6s %-20s %-30s", formatter.format(new Date()), lvltag, tag, msg));
	}

	/**
	 * {@link #INFO} level logging.
	 * 
	 * @since 1.0
	 * @param message
	 *            to log
	 */
	public void i(String message) {
		log(tag, INFO_TAG, INFO, message);
	}

	/**
	 * {@link #DEBUG} level logging.
	 * 
	 * @since 1.0
	 * @param message
	 *            to log
	 */
	public void d(String message) {
		log(tag, DEBUG_TAG, DEBUG, message);
	}

	/**
	 * {@link #WARNING} level logging.
	 * 
	 * @since 1.0
	 * @param message
	 *            to log
	 */
	public void w(String message) {
		log(tag, WARNING_TAG, WARNING, message);
	}

	/**
	 * {@link #ERROR} level logging.
	 * 
	 * @since 1.0
	 * @param message
	 *            to log
	 */
	public void e(String message) {
		log(tag, ERROR_TAG, ERROR, message);
	}

	public static void setLevel(int level) {
		Vellum.level = level;
	}

}