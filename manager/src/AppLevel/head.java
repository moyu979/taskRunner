package AppLevel;

import MachineLevel.Machine;
import Vars.globalVars;

import java.util.Calendar;

public class head extends App {
    public head() {
        ;
    }
    public head(Machine m) {
        super(m);
    }
    @Override
    public Calendar run(){
        System.out.println("head Running");
        nextRun=Calendar.getInstance();
        nextRun.add(Calendar.SECOND,5);
        return nextRun;
    }
}
