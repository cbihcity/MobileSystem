/**
 * 
 */
package by.pvt.heldyieu.mobile.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import by.pvt.heldyieu.mobile.beans.interfaces.Constants;


/**
 * Class for logger various exceptions
 * 
 * @author i.heldyieu
 */
public final class Logger implements Constants {
	public static final String logFileRoot = LOG_FILE_ROOT;
	private static BufferedWriter writer = null;
	
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
	 * @param exception - the entity of Throwable
	 */
	public static void log(final Throwable exception) {
		final StringWriter errors = new StringWriter();
		exception.printStackTrace(new PrintWriter(errors));

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(LOG_FILE_ROOT, true),"utf-8"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			writer.write(sdf.format(Calendar.getInstance().getTime()));
			writer.write(errors.toString()+"\n");
			writer.close();
		} catch (IOException e) {
			Logger.log(e);
		} finally {
			try {
				if (writer!=null) {
					writer.close();
				}
			} catch (Exception e){
				System.err.println("Ошибка закрыти потока вывода : "+e);
				Logger.log(e);
			}
		}
	}

	/**
	 * Writes log in file
	 * @param message - string message
	 */
	public static void log(final String message) {
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(LOG_FILE_ROOT, true),"utf-8"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			writer.write(sdf.format(Calendar.getInstance().getTime()));
			writer.write(message+"\n");
			writer.close();
		} catch (IOException e) {
			Logger.log(e);
		} finally {
			try {
				if (writer!=null) {
					writer.close();
				}
			} catch (Exception e){
				System.err.println("Ошибка закрыти потока вывода : "+e);
				Logger.log(e);
			}
		}
	}
}
