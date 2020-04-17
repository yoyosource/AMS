package ams.client;

import yapi.ui.console.Console;
import yapi.ui.console.ConsoleMessageBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

public class AMSClientOLD {

    private static boolean open = false;
    private static Console console = new Console();

    private static BufferedReader reader = null;
    private static PrintWriter writer = null;
    private static Socket socket = null;

    private static String uuid = UUID.randomUUID().toString();
    private static String name = "";

    public static void start() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;

        try {
            while ((s = bufferedReader.readLine()) != null) {
                if (s.startsWith("!")) {
                    if (s.startsWith("!connect ")) {
                        try {
                            socket = new Socket(s.substring("!connect ".length()), 443);
                            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            writer = new PrintWriter(socket.getOutputStream(), true);
                            open = true;
                            reciever(reader);
                            if (name.isEmpty()) {
                                writer.println("<CENTER><GREEN>" + uuid + "<YELLOW> joined!");
                            } else {
                                writer.println("<CENTER><YELLOW>" + name + " <DEFAULT:COLOR,DEFAULT:ATTRIBUTE,YELLOW>joined!");
                            }
                        } catch (IOException e) {
                            console.send(ConsoleMessageBuilder.build("<RED><CENTER>Connection refused"));
                            console.send(ConsoleMessageBuilder.build("<RED><CENTER>" + e.getLocalizedMessage()));
                        }
                    } else if (s.startsWith("!exit") && socket != null && reader != null && writer != null) {
                        if (name.isEmpty()) {
                            writer.println("<CENTER><YELLOW>Somebody left!");
                        } else {
                            writer.println("<CENTER><YELLOW>" + name + " <DEFAULT:COLOR,DEFAULT:ATTRIBUTE,YELLOW>left!");
                        }
                        writer.close();
                        reader.close();
                        socket.close();
                        open = false;

                        writer = null;
                        reader = null;
                        socket = null;
                    } else if (s.startsWith("!quit")) {
                        break;
                    } else if (s.startsWith("!name ")) {
                        String prevName = name;
                        name = s.substring("!name ".length()).replaceAll("(\n|\t|\r)", "");
                        console.send(ConsoleMessageBuilder.build("<GREEN>Nickname changed to: <YELLOW>" + name));
                        if (writer != null) {
                            if (name.isEmpty()) {
                                if (prevName.isEmpty()) {
                                    writer.println("<GREEN><CENTER>" + uuid + "<DEFAULT:COLOR,DEFAULT:ATTRIBUTE> removed the nickname");
                                } else {
                                    writer.println("<GREEN><CENTER>" + prevName + "<DEFAULT:COLOR,DEFAULT:ATTRIBUTE> removed the nickname");
                                }
                            } else if (prevName.isEmpty()) {
                                writer.println("<GREEN><CENTER>" + uuid + "<DEFAULT:COLOR,DEFAULT:ATTRIBUTE> changed name to <YELLOW>" + name);
                            } else {
                                writer.println("<YELLOW><CENTER>" + prevName + "<DEFAULT:COLOR,DEFAULT:ATTRIBUTE> changed name to <YELLOW>" + name);
                            }
                        }
                    }
                    else {
                        console.send(ConsoleMessageBuilder.build("<LEFT><RED>Unknown Command\n<DEFAULT:COLOR>Valid Commands are:\n!connect [IP]\n!name <NAME>\n!exit\n!quit"));
                    }
                } else if (reader != null && writer != null) {
                    if (name.isEmpty()) {
                        writer.println("<GREEN>" + uuid + "<DEFAULT:COLOR,DEFAULT:ATTRIBUTE> -> " + s);
                    } else {
                        writer.println("<GREEN>" + uuid + "<DEFAULT:COLOR,DEFAULT:ATTRIBUTE> [<YELLOW>" + name + "<DEFAULT:COLOR,DEFAULT:ATTRIBUTE>] -> " + s);
                    }
                }
            }
        } catch (IOException e) {

        }
    }

    private static void reciever(BufferedReader reader) {
        Runnable runnable = () -> {
            while (open) {
                try {
                    String s = reader.readLine();
                    if (s == null) {
                        writer.close();
                        AMSClientOLD.reader.close();
                        socket.close();
                        open = false;

                        writer = null;
                        AMSClientOLD.reader = null;
                        socket = null;

                        console.send(ConsoleMessageBuilder.build("<CENTER><RED>Connection lost"));
                        break;
                    }
                    if (!s.startsWith("<GREEN>" + uuid)) {
                        console.send(ConsoleMessageBuilder.build("<DEFAULT:COLOR><LEFT>" + s));
                    }
                } catch (IOException e) {

                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

}
