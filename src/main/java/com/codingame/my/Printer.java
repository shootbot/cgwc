package com.codingame.my;

public class Printer {

    public void println(String command, int x, int y, String type, String dir) {
        System.out.println("" + command + " " + x + " " + y + " " + type + " " + dir);
    }

    public void println(String command, int x, int y, String type) {
        println(command, x, y, type, "");
    }

    public void printwait() {
        System.out.println("WAIT");
    }
}
