package MachineLevel;

import AppLevel.App;
import Vars.globalVars;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.HashMap;

public class Machine implements Runnable{
    String name;
    String port;

    File file;
    Calendar nextExecTime;
    boolean allRun=false;

    int xPixels,yPixels;
    HashMap<String, App> runApps;
    public Machine(File machineDir){
        file=machineDir;
        getBasicSettings(new File(machineDir,"settings.txt"));
        setApps();
        setParas(new File(machineDir,"apps"));
        allRun=true;
        nextExecTime=Calendar.getInstance();
    }

    private void setApps() {
        globalVars.apps.runApps.forEach((k,v)->{
            try {
                App a=(App)v.getDeclaredConstructor(Machine.class).newInstance(this);
                runApps.put(k,a);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void getBasicSettings(File settingFile) {
        try {
            FileReader fileReader=new FileReader(settingFile);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String s=bufferedReader.readLine();
            while(s!=null){
                String[] ss=s.split(":");
                System.out.println(ss[1]);
                switch(ss[0]){
                    case "name" -> {this.name=ss[1];}
                    case "port" -> {this.port=ss[1];}
                }
                s=bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

    }

    public void addApp(Class myclass){
        ;
    }
    public boolean ableRun(){
        if(nextExecTime==null){
            return false;
        }else if(nextExecTime.before(Calendar.getInstance()) && allRun){
            return true;
        }else {
            return false;
        }
    }

    public String getInfo(){
        return null;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
