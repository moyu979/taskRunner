package AppLevel;

import MachineLevel.Machine;

import Repack.Bat;
import Repack.Log;


import java.io.File;

import java.util.Calendar;
import java.util.HashMap;


public abstract class App {
    public Machine machine;
    public Calendar nextRun;
    public Log log;
    public Bat bat;

    HashMap<String, Boolean> run;
    boolean reload;

    public App(){;}

    public App(Machine m){
        ;
    }
    public void loadSetting(){
        ;
    }

    public Calendar run() {
        return null;
    }

    public boolean ableRun(){
        return false;
    }
    public void newSetFile(File gameset) {
        ;
    }
    public void getInfo(){;}
}
