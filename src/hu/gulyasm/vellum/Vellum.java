package hu.gulyasm.vellum;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
	private static int level = INFO;
	/* Instance tag */
	private final String tag;
	/* Stream to log to */
	private OutputStream stream;

	public Vellum(String tag, OutputStream stream) {
		this.tag = tag;
		this.stream = stream;
	}

	public Vellum(String tag) {
		this(tag, System.out);
	}

	public void setStream(OutputStream stream) {
		this.stream = stream;
	}

	private void log(String tag, String lvltag, int lvl, String msg) {
		if (lvl < level) return;
		String message = String.format("%-13s %-6s %-20s %-30s", formatter.format(new Date()), lvltag, tag, msg);
		try {
			stream.write(message.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@link #INFO} level logging.
	 * 
	 * @since 1.0
	 * @param message
	 *            to log, called with {@link Object#toString()}
	 */
	public void i(Object message) {
		log(tag, INFO_TAG, INFO, message.toString());
	}

	/**
	 * {@link #DEBUG} level logging.
	 * 
	 * @since 1.0
	 * @param message
	 *            to log, called with {@link Object#toString()}
	 */
	public void d(Object message) {
		log(tag, DEBUG_TAG, DEBUG, message.toString());
	}

	/**
	 * {@link #WARNING} level logging.
	 * 
	 * @since 1.0
	 * @param message
	 *            to log, called with {@link Object#toString()}
	 */
	public void w(Object message) {
		log(tag, WARNING_TAG, WARNING, message.toString());
	}

	/**
	 * {@link #ERROR} level logging.
	 * 
	 * @since 1.0
	 * @param message
	 *            to log, called with {@link Object#toString()}
	 */
	public void e(Object message) {
		log(tag, ERROR_TAG, ERROR, message.toString());
	}

	/**
	 * Sets the logging level. Logging level is static in this type, so the
	 * scope of this setting is the classloader;
	 * 
	 * @param level
	 *            the new level
	 */
	public static void setLevel(int level) {
		Vellum.level = level;
	}

}