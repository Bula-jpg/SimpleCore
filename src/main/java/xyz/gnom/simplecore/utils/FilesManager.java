package xyz.gnom.simplecore.utils;

import xyz.gnom.simplecore.SimpleCore;

import java.io.File;

public class FilesManager {
    public static void setup() {
        SimpleCore p = SimpleCore.getInstance();
        File folder = new File(p.getDataFolder() + "/data");
        if (!folder.exists()) {
            folder.mkdir();
        }
    }
}
