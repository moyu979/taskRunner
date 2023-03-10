package Entry;

import Vars.globalVars;
import globalLevel.Controller;

import java.io.File;

import static Tools.txts.readTxt;

public class Help extends Entry{
    public Help(File userFile, File softFile) {
        super(userFile, softFile);
    }
    //运行函数
    @Override
    public Entry run() {
        String help=readTxt("resources/help");
        System.out.println(help);
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
