package globalLevel;
import Entry.Entry;
import Vars.globalVars;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import static Tools.Bats.runCmd;

public class Controller{
    Entry caller;

    File userFile;
    File softFile;
    File jarFile;
    Thread machineThread;


    public Controller(Entry caller){
        setCaller(caller);

        setSoftFile(caller.getSoftFile());
        setUserFile(caller.getUserFile());
        setJarFile(new File(softFile,"jarFile"));

        globalVars.apps=new Apps(jarFile);
        globalVars.machines=new Machines(this.userFile);

        machineThread=new Thread(globalVars.machines);

    }

    public void run(){
        machineThread.run();
    }

    public boolean isRunning(){
        return machineThread.isAlive();
    }

    public void addMachine(String[] cmds) {
        if(cmds.length==1){
            String result=runCmd("devices");
            String[] results=result.split("\n");
            ArrayList<String> signs=new ArrayList<>();
            int count=1;
            String ips="not registered signs:\n";
            for(int i=1;i<results.length;i++){
                String s=results[i];
                String[] ss=s.split("\t");
                if(globalVars.machines.findMachine(ss[0])==null){
                    ips=ips+i+":\t"+ss[0]+"\n";
                    signs.add(ss[0]);
                }
            }
            ips=ips+"please chooese one(0 to exit)";
            String ans=caller.getMessage(ips);
            int num=Integer.parseInt(ans);
            String name=caller.getMessage("please name it");
            //感觉这里应该是要-1的，但是之前没减好像也没出问题？
            globalVars.machines.addMachine(name,signs.get(num-1));
        }else{
            System.out.println("too much args,maybe you want to set port on one path, but you cannot");
        }

    }
    public void addApp(String[] cmds) {
        if(cmds.length==1){
            String path=caller.getMessage("请输入目标文件名");
            File source=new File(path);
            if(source.isFile()){
                File dest=new File(jarFile,source.getName());
                try {
                    Files.copy(source.toPath(),dest.toPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{
                for(File i:source.listFiles()){
                    File dest=new File(jarFile,i.getName());
                    try {
                        Files.copy(source.toPath(),dest.toPath());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }else{
            System.out.println("too much args,maybe you want to set jars on one command, but you cannot");
        }
    }

    public String getInfo() {
        String toReturn="";
        if(isRunning()){
            toReturn+="running\n";
        }else{
            toReturn+="stopping\n";
        }
        toReturn+="machines\n";
        toReturn=toReturn+globalVars.machines.getInfo();
        toReturn=toReturn+"\napps\n";
        toReturn=toReturn+globalVars.apps.getInfo();
        return toReturn;
    }




    //get and set
    public Entry getCaller() {
        return caller;
    }

    public void setCaller(Entry caller) {
        this.caller = caller;
    }

    public File getUserFile() {
        return userFile;
    }

    public void setUserFile(File userFile) {
        this.userFile = userFile;
    }

    public File getSoftFile() {
        return softFile;
    }

    public void setSoftFile(File softFile) {
        this.softFile = softFile;
    }

    public File getJarFile() {
        return jarFile;
    }

    public void setJarFile(File jarFile) {
        if(!jarFile.exists()){
            System.out.println("new jar File,please add manually");
            jarFile.mkdir();
        }
        this.jarFile = jarFile;
    }
}
