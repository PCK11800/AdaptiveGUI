package tools.OSHandler;

import java.io.File;

public class OSChecker {
    public enum OS {
        WINDOWS, LINUX, MAC, SOLARIS
    };

    private static OS os = null;

    private static OS getOS() {
        if (os == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                os = OS.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                os = OS.LINUX;
            } else if (operSys.contains("mac")) {
                os = OS.MAC;
            } else if (operSys.contains("sunos")) {
                os = OS.SOLARIS;
            }
        }
        return os;
    }

    public static void handleLinux()
    {
        if (getOS() == OS.LINUX) {
            File lib = new File("src/main/java/tools/OSHandler/" + System.mapLibraryName("fixXInitThreads"));
            System.load(lib.getAbsolutePath());
        }

        System.out.println("LINUX");
    }
}
