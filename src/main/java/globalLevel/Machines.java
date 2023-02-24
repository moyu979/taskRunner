package globalLevel;

import java.io.File;
import MechineLevel.Machine;

import java.util.Map;

public class Machines {
    Map<Machine,Thread> machines;
    public Machines(File[] machineFiles){
        for(File i:machineFiles){
            Machine machine=new Machine(i);
            Thread thread=new Thread(machine);
            machines.put(machine,thread);
        }
    }
}
