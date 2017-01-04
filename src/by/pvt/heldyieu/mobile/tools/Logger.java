/**
 * 
 */
package by.pvt.heldyieu.mobile.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import by.pvt.heldyieu.mobile.beans.interfaces.Constants;


/**
 * Class for loger various exceptions
 * 
 * @author i.heldyieu
 * @version 1.0
 */
public final class Logger implements Constants {
	public static final String logFileRoot = LOG_FILE_ROOT;
	
	static {
		File f = new File(logFileRoot);
		try {
			f.createNewFile();
		} catch (IOException e) {
			System.out.println("ОШИБКА. Невозможно создать файл логирования.");
		}
	}
	
	/**
	 * Closed constructor
	 */
	private Logger() {
	}

	/**
	 * Writes log in file
	 * 
	 * @param exception - the entity of Throwable
	 */
	public static void log(final Throwable exception) {
		final StringWriter errors = new StringWriter();
		exception.printStackTrace(new PrintWriter(errors));

		try (PrintWriter printwriter = new PrintWriter(new BufferedWriter(
				new FileWriter(new File(logFileRoot), true)))) {
			final SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			printwriter.println(sdf.format(Calendar.getInstance().getTime()));
			printwriter.println(exception.getMessage());
			printwriter.println(errors.toString());
		} catch (IOException e) {
			Logger.log(e);
		}
	}

	/**
	 * Writes log in file
	 * 
	 * @param message - string message
	 */
	public static void log(final String message) {
		try(PrintWriter printwriter = new PrintWriter(new BufferedWriter(new FileWriter(new File(logFileRoot), true)))){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			printwriter.println(sdf.format(Calendar.getInstance().getTime()));
			printwriter.println(message);
		} catch (IOException e) {
			Logger.log(e);
		}
	}
}
