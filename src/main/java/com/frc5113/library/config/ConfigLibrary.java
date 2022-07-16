package com.frc5113.library.config;

public class ConfigLibrary {
    private ConfigLibrary() {}

    private static String mainBotMAC = "";

    public static void setMainBotMAC(String newMAC) {
        mainBotMAC = newMAC;
    }

    public static String getMainBotMac() {
        return mainBotMAC;
    }
}
