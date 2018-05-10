package barbosshouse.io;


import barbosshouse.Order;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

abstract class OrderManagerFileSource implements FileSource {
    private String sourcePath = "";
    protected static String TXT_EXTENSION = ".txt";
    protected static String BIN_EXTENSION = ".bin";
    protected static String OBJ_EXTENSION = ".obj";

    @Override
    public String getPath() {
        return sourcePath;
    }
    @Override
    public void setPath(String path) {
        sourcePath = path;
    }

    protected long timeToMilli(LocalDateTime time){
        return time.toEpochSecond(ZoneOffset.UTC);
    }

    protected String filePath(Order menuItems, String extension){
        return getPath()+timeToMilli(menuItems.getOrderTime()) + extension;
    }
}
