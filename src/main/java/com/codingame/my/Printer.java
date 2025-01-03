package com.codingame.my;

public class Printer {

    public void println(String command, int parentId, int x, int y, String type, String dir) {
        System.out.println("" + command + " " + parentId + " " + x + " " + y + " " + type + " " + dir);
    }

    public void println(String command, int parentId, int x, int y, String type) {
        println(command, parentId, x, y, type, "");
    }

    public void justwait() {
        System.out.println("WAIT");
    }

    public void basic(int parentId, int x, int y) {
        println("GROW", parentId, x, y, "BASIC");
    }

    public void harvester(int parentId, int x, int y, String dir) {
        println("GROW", parentId, x, y, "HARVESTER", dir);
    }
}
