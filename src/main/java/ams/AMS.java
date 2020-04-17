package ams;

import ams.client.AMSClient;
import ams.client.AMSClientOLD;
import ams.server.AMSServer;
import yapi.runtime.RuntimeUtils;

public class AMS {

    public static void main(String[] args) {
        /*for (int i = 0; i < 1_000_000; i++) {
            String input = new NumberRandom().getString(100);
            try {
                ConsoleMessageBuilder.build(input);
            } catch (Exception e) {
                System.out.println(input);
                e.printStackTrace();
            }
        }
        if (true) {
            return;
        }*/

        /*System.out.println(StringFormatting.toHex(StringCrypting.hash(" ", HashType.MD5)));
        if (true) {
            return;
        }*/
        if (RuntimeUtils.isHeadless()) {
            startServer();
        } else if (args.length != 1) {
            startClient();
        } else if (args[0].equals("-client")) {
            startClient();
        } else if (args[0].equals("-server")) {
            startServer();
        }

        /*
        System.out.println(createKey(0));

        System.out.println(passwordStrength("Hello World"));
        System.out.println(passwordStrength("H3ll0 W0rld"));
        System.out.println(passwordStrength("penis123"));
        System.out.println(passwordStrength("p3nis123"));

        System.out.println(NumberRandom.getInstance().getString(173));

        System.out.println();
        String k = "lul";
        System.out.println(StringFormatting.unformatText(k));
        int padding = 62;
        System.out.println("?? ".repeat(41) + StringFormatting.toHex((StringFormatting.unformatText(k) + "&&{$x0003}".repeat(padding)).getBytes(), true));
        String s = toHex(encrypt((StringFormatting.unformatText(k) + "&&{$x0003}".repeat(padding)).getBytes(), createKey("1101", 8)));
        System.out.println(s);
        System.out.println(same("?? ".repeat(41) + StringFormatting.toHex(StringFormatting.unformatText(k).getBytes(), true), s));
        String d = s;
        System.out.println(StringFormatting.formatText(EncryptionSymmetric.toString(decrypt(toBytes(d), createKey("1101", 8)))));
        System.out.println();
        d = "99 56 4D EF 3C 8F F5 8E D3 0C 55 42 B8 09 9B E6 E0 C6 5E 56 BC BA 50 AA B9 0C 81 C2 F4 C5 AB 70 08 F6 07 57 07 63 6D B8 18 91 EA 61 BA F4 D7 90 15 83 7F F7 30";
        System.out.println(StringFormatting.formatText(EncryptionSymmetric.toString(decrypt(toBytes(d), createKey("1101", 8)))));
        */
    }

    private static String same(String input, String encrypted) {
        String[] inputs = input.split(" ");
        String[] encrypteds = encrypted.split(" ");

        if (inputs.length != encrypteds.length) {
            return "";
        }

        StringBuilder st = new StringBuilder();
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].equals(encrypteds[i])) {
                st.append("^^ ");
            } else {
                st.append("   ");
            }
        }
        return st.toString();
    }

    private static void startServer() {
        System.out.println("Start Server");
        AMSPackage.init(AMSPackage.AMSType.SERVER);
        AMSServer.start();
    }

    private static void startClient() {
        System.out.println("Start Client");
        AMSPackage.init(AMSPackage.AMSType.CLIENT);
        //AMSClientOLD.start();
        AMSClient.start();
    }

}
