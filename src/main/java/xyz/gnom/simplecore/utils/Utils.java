package xyz.gnom.simplecore.utils;

import java.io.*;

public class Utils {
    public static String stringBuilder(final String[] args, final int liczOdArgumentu) {
        String msg = "";
        for (int i = liczOdArgumentu; i < args.length; ++i) {
            msg = String.valueOf(msg) + args[i];
            if (i <= args.length - 2) {
                msg = String.valueOf(msg) + " ";
            }
        }
        return msg;
    }
    public static void copy(final InputStream source, final File file) {
        try {
            final OutputStream out = new FileOutputStream(file);
            final byte[] buf = new byte[1024];
            int len = 0;
            while ((len = source.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.close();
            source.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

