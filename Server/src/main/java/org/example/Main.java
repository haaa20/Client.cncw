package org.example;

import Server.Server;

public class Main {
    public static void main(String[] args) {
        Server you = new Server(9002);
        you.echo();
    }
}