package Entry;

import globalLevel.Controller;

import java.io.File;

public class GUI extends Entry{
    public GUI(File userFile, File softFile){
        super(userFile,softFile);
    }


    @Override
    public Entry run() {
        return null;
    }

    @Override
    public void showInfo(String message) {

    }

    @Override
    public String getMessage(String ask) {
        return null;
    }

    @Override
    public void getInfo() {

    }


}
