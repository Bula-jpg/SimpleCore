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
}

