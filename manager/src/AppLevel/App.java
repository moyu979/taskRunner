package AppLevel;

import MachineLevel.Machine;

import java.io.File;
import java.util.Calendar;

public abstract class App {
    Calendar nextRun;
    public App(){;}
    public App(Machine m){
        nextRun=Calendar.getInstance();
    }
    public void loadSetting(File f){
        ;
    }
    public Calendar run(){
        return null;
    }

    public void newSetFile(File gameset) {
    }
}
