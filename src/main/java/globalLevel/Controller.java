package globalLevel;

import Entry.Entry;

import java.io.File;
import java.util.Calendar;

import static java.lang.Thread.sleep;

public class Controller{
    Entry caller;//回调，传递信息用
    File userFile;
    File softFile;

    Machines machines;
    Apps apps;
    Calendar nextRun;
    public Controller(Entry caller){
        this.caller=caller;
        this.userFile=caller.getUserFile();
        this.softFile=caller.getSoftFile();

        this.machines=new Machines(this.userFile.listFiles());
        Calendar nextRun=Calendar.getInstance();

    }

    public void run(){};
    public void loadMechines() {

    }
    public void loadUsers() {

    }
}
