package globalLevel;

import Entry.Entry;
import MechineLevel.Machine;
import tools.Bat;

import java.io.File;
import java.util.ArrayList;
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
        this.nextRun=Calendar.getInstance();

        this.machines=new Machines(this.userFile.listFiles());

        File jarFile=new File(softFile,"appPackages");
        this.apps=new Apps(jarFile.listFiles());
    }

    public void run(){};

    public void addMachine(){
        System.out.println("adb devices");
        String result=Bat.run("adb devices");
        String[] results=result.split("\n");
        ArrayList<String> signs=new ArrayList<>();
        int count=1;
        String ips="not registered signs:\n";
        for(String s:results){
            String[] ss=s.split("\t");
            if(ss[ss.length-1].equals("device") && machines.findMachine(ss[0])==null){
                ips=ips+count+"\t"+ss[0]+"\n";
                signs.add(ss[0]);
                count++;
            }
        }
        ips=ips+"please chooese one(0 to exit)";
        String ans=caller.getMessage(ips);

        int num=Integer.parseInt(ans);
        String name=caller.getMessage("please name it");

        Machine machine=new Machine(name,signs.get(num));
        machines.addMachine(machine);
    }

    public void deleteMachine(){
        ;
    }
    public String getInfo(){
        String s="machines:\n";
        s=s+machines.getInfo();
        s=s+"apps\n";
        s=s+apps.getInfo();
        return s;
    }
}
