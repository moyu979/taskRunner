package globalLevel;

import MachineLevel.Machine;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Machines {
    Map<Machine,Thread> machines;
    public Machines(File[] machineFiles){
        System.out.println("dd");
        machines=new HashMap<>();
        for(File i:machineFiles){
            addMachine(i);
        }
        if(machines.size()==0){
            System.out.println("empty machine");
        }else {
            System.out.println("get "+machines.size()+" machine");
        }
        machines.forEach((k,v)-> v.start());
    }

    public void addMachine(File machineFile){
        Machine machine=new Machine(machineFile);
        Thread thread=new Thread(machine);
        machines.put(machine,thread);
    }
    public void addMachine(Machine machine){
        Thread thread=new Thread(machine);
        machines.put(machine,thread);
    }
    public void addMachine(String name,String port){
        File file=new File("./users/"+name);
        file.mkdir();
        File setFile=new File(file,"settings.txt");
        try {
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(setFile));
            bufferedWriter.write("name:"+name+"\n");
            bufferedWriter.write("port:"+port+"\n");
            bufferedWriter.close();
            addMachine(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public Machine findMachine(String sign){
        for(Machine machine:machines.keySet()){
            if(machine.getPort().equals(sign)){
                return machine;
            }
        }
        return null;
    }
    public String getInfo(){
        String s="";
        for(Machine m: machines.keySet()){
            s=s+m.getInfo();
        }
        return s;
    }
}
