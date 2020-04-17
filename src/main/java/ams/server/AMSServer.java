package ams.server;

import yapi.runtime.ThreadUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class AMSServer {

    private static List<PrintStream> sockets = new ArrayList<>();
    private static boolean open = true;

    private static volatile Queue<String> messageQueue = new ArrayDeque<>();

    public static void start() {
        try {
            ServerSocket server = new ServerSocket(443);
            acceptor(server);
            send();
        } catch (IOException e) {

        }
    }

    private static void acceptor(ServerSocket server) {
        Runnable runnable = () -> {
            while (open) {
                try {
                    Socket s = server.accept();
                    System.out.println(s);
                    //messageQueue.add("<CENTER><YELLOW>Somebody joined!");
                    sockets.add(new PrintStream(s.getOutputStream(), true));
                    reciever(s);
                } catch (IOException e) {

                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private static void reciever(Socket socket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Runnable runnable = () -> {
                while (!socket.isClosed()) {
                    try {
                        String s = reader.readLine();
                        if (s != null) {
                            System.out.println(s);
                            messageQueue.add(s);
                        } else {
                            break;
                        }
                    } catch (IOException e) {

                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        } catch (IOException e) {

        }
    }

    private static void send() {
        Runnable runnable = () -> {
            while (open) {
                if (!messageQueue.isEmpty()) {
                    String s = messageQueue.remove();
                    for (PrintStream printStream : sockets) {
                        printStream.println(s);
                    }
                }
                ThreadUtils.sleep(100);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

}
