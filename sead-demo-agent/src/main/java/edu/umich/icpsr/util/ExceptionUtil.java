package edu.umich.icpsr.util;

import java.io.Writer;

public class ExceptionUtil {
	public static void print(Throwable t, Writer w) {
		try {
			Throwable cause = getRootCause(t);
			if (cause != null) {
				StackTraceElement[] stackTrace = cause.getStackTrace();
				w.write("------------------Stacktrace-BEGIN------------------------------------------------------");
				w.write("\n");
				for (StackTraceElement stackTraceElement : stackTrace) {
					w.write(stackTraceElement.toString());
					w.write("\n");
				}
				w.write("------------------Stacktrace-END------------------------------------------------------");
				w.write("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Throwable getRootCause(Throwable t) {
		if (t != null && t.getCause() == null) {
			return t;
		}
		return getRootCause(t.getCause());
	}
}
