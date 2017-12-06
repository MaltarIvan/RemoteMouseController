package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	    String sound = System.getProperty("user.dir") + "SndVol.lnk";
        ProcessBuilder pb = new ProcessBuilder("cmd", "D:", sound);
        try {
            Process process = pb.start();
            process = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
