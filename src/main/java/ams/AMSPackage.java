package ams;

import yapi.file.FileUtils;
import yapi.manager.log.LogManager;
import yapi.runtime.Hook;
import yapi.runtime.RuntimeUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.UUID;

public class AMSPackage {

    public enum AMSType {
        CLIENT("client"),
        SERVER("server");

        private String name;

        AMSType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private static File amsDIR = new File(FileUtils.getUserHome() + "/AMS");
    private static File amsLOG = null;
    private static File amsCLIENTS = null;

    private static File amsUSERConfig = new File(FileUtils.getUserHome() + "/AMS/.user.config");
    private static File amsSERVERConfig = new File(FileUtils.getUserHome() + "/AMS/.server.config");

    static void init(AMSType type) {
        if (!amsDIR.exists()) {
            amsDIR.mkdirs();
        }
        amsLOG = new File(amsDIR.getAbsolutePath() + "/" + type.getName() + "/log");
        if (!amsLOG.exists()) {
            amsLOG.mkdirs();
        }
        amsCLIENTS = new File(amsDIR.getAbsolutePath() + "/" + type.getName() + "/clients");
        if (!amsCLIENTS.exists()) {
            amsCLIENTS.mkdirs();
        }
        LogManager.setAllowLog(amsLOG);

        if (type.equals(AMSType.CLIENT)) {
            initClient();
        } else if (type.equals(AMSType.SERVER)) {
            initServer();
        }
    }

    private static void initClient() {
        if (amsUSERConfig.exists()) {
            return;
        }

        try {
            amsUSERConfig.createNewFile();
            FileUtils.dump(amsUSERConfig, "TOKEN: " + UUID.randomUUID().toString() + "-" + UUID.randomUUID().toString() + "\n" +
                    "PASSWORD: " + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initServer() {
        if (amsSERVERConfig.exists()) {
            return;
        }

        try {
            amsSERVERConfig.createNewFile();
        } catch (IOException e) {

        }
    }

}
