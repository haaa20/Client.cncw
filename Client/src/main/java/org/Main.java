package org;

import client.Client;

public class Main {
    public static void main(String[] args) {
        Client me = new Client();

        me.connect("localhost", 9002);
        me.sayHello();
    }
}