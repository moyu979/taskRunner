package globalLevel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import MachineLevel.Machine;

import static Tools.txts.writeTxt;
import static java.lang.Thread.sleep;

//machine单独一个线程，定时执行
public class Machines implements Runnable {
    HashMap<Machine,Thread> machines;
    HashMap<String,Machine> machineNames;
    boolean allowRun=true;
    public Machines(File userFile){
        System.out.println("init "+this.getClass().getName());
        File[] machineFiles=userFile.listFiles();
        machines=new HashMap<>();
        machineNames=new HashMap<>();

        for (File mFile:machineFiles){
            addMachine(mFile);
        }

        if(machines.size()==0){
            System.out.println("empty machine");
        }else {
            System.out.println("get "+machines.size()+" machine");
        }

    }

    private void addMachine(File mFile) {
        Machine machine=new Machine(mFile);
        Thread thread=new Thread(machine);
        machines.put(machine,thread);
        machineNames.put(machine.getName(),machine);
        thread.run();
    }

    public void addMachine(String name,String port){
        int i=machines.size();
        File file=new File("./users/"+i);
        while(file.exists()){
            i++;
            file=new File("./users/"+i);
        }
        file.mkdir();
        File setFile=new File(file,"settings.txt");
        new File(file,"apps").mkdir();
        String set="name:"+name+"\nport:"+port;
        writeTxt(set,setFile);
        addMachine(file);
    }

    public void addApp(Class myclass){
        for(Machine machine:machines.keySet()){
            machine.addApp(myclass);
        }
    }
    public Machine findMachine(String name){
        return machineNames.get(name);
    }

    public void run(){
        System.out.println("run "+this.getClass().getName());
        while (allowRun) {
            if (machines.size() == 0) {
                ;
            }else{
                machines.forEach((k, v) -> {
                    if (!v.isAlive() && k.ableRun()) {
                        Thread t=new Thread(k);
                        machines.put(k,t);
                        t.start();
                    }
                });
            }
            try {
                sleep(1000 * 1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getInfo(){
        String s="";
        for(Machine m: machines.keySet()){
            s=s+m.getInfo();
        }
        s+="\n";
        return s;
    }

    public HashMap<Machine, Thread> getMachines() {
        return machines;
    }

    public void setMachines(HashMap<Machine, Thread> machines) {
        this.machines = machines;
    }

    public HashMap<String, Machine> getMachineNames() {
        return machineNames;
    }

    public void setMachineNames(HashMap<String, Machine> machineNames) {
        this.machineNames = machineNames;
    }

    public boolean isAllowRun() {
        return allowRun;
    }

    public void setAllowRun(boolean allowRun) {
        this.allowRun = allowRun;
    }

}
