package MachineLevel;

import AppLevel.App;
import Vars.globalVars;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Machine implements Runnable{
    String name;
    String port;

    File file;
    Calendar nextExecTime;
    boolean allRun=false;

    int xPixels=0,yPixels=0;
    HashMap<String, App> runApps;
    public Machine(File machineDir){
        runApps=new HashMap<>();
        System.out.println("init "+this.getClass().getName());
        file=machineDir;
        getBasicSettings(new File(machineDir,"settings.txt"));
        setApps();
        setParas(new File(machineDir,"apps"));
        allRun=true;
        nextExecTime=null;
    }

    private void setParas(File apps) {
        for(String s:runApps.keySet()){
            File gameset=new File(apps,s+".txt");
            if(gameset.exists()){
                runApps.get(s).loadSetting();
            }else{
                try {
                    gameset.createNewFile();
                    runApps.get(s).newSetFile(gameset);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

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
                String[] ss=s.split(":",2);
                System.out.println(ss[1]);
                switch(ss[0]){
                    case "name" -> {this.name=ss[1];}
                    case "port" -> {this.port=ss[1];}
                    case "xPixels" -> {this.xPixels=Integer.parseInt(ss[1]);}
                    case "yPixels" -> {this.yPixels=Integer.parseInt(ss[1]);}
                }
                s=bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        checkSets();
    }

    private void checkSets() {
        String cmd="adb -s "+port+" shell wm size";
        Process p= null;
        int y,x;
        try {
            p = Runtime.getRuntime().exec(cmd);

            InputStream inputStream=p.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String s= null;
            s = bufferedReader.readLine();
            if(s==null){
                System.out.println("machine named "+this.name+" no found");
                return;
            }
            s=s.replace("Physical size: ","");
            String[] mm=s.split("x");
            System.out.println();
            y=Integer.valueOf(mm[1]);
            x=Integer.valueOf(mm[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(x==xPixels && y==yPixels){
            ;
        }else{
            System.out.println("px changed");
            xPixels=x;
            yPixels=y;
        }
    }

    @Override
    public void run() {
        ArrayList<Calendar> runtime=new ArrayList<>();
        nextExecTime=null;
        for(String name: runApps.keySet()){
            Calendar appRunTime=runApps.get(name).run();
            runtime.add(appRunTime);
        }
        if(runtime.size()==0){
            ;
        }else{
            Calendar n=runtime.get(0);
            for(Calendar c:runtime){
                if(n.after(c)){
                    n=c;
                }
            }
            nextExecTime=n;
        }

    }

    public void addApp(Class myclass){
        App a= null;
        try {
            a = (App)myclass.getDeclaredConstructor(Machine.class).newInstance(this);
            runApps.put(myclass.getName(), a);
            //如果加入的app是第一个，那么nextExecTime会在一次试运行后被置null，需要添加app后唤醒。
            if(nextExecTime==null){
                nextExecTime=Calendar.getInstance();
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean ableRun(){
        System.out.println("a");
        if(nextExecTime==null){
            return false;
        }else if(nextExecTime.before(Calendar.getInstance()) && allRun){
            System.out.println("true");
            return true;
        }else {
            return false;
        }
    }

    public String getInfo(){
        return "name:"+name+"\nport:"+port+"\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Calendar getNextExecTime() {
        return nextExecTime;
    }

    public void setNextExecTime(Calendar nextExecTime) {
        this.nextExecTime = nextExecTime;
    }

    public boolean isAllRun() {
        return allRun;
    }

    public void setAllRun(boolean allRun) {
        this.allRun = allRun;
    }

    public int getxPixels() {
        return xPixels;
    }

    public void setxPixels(int xPixels) {
        this.xPixels = xPixels;
    }

    public int getyPixels() {
        return yPixels;
    }

    public void setyPixels(int yPixels) {
        this.yPixels = yPixels;
    }

    public HashMap<String, App> getRunApps() {
        return runApps;
    }

    public void setRunApps(HashMap<String, App> runApps) {
        this.runApps = runApps;
    }
}
