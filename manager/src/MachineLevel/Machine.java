package MachineLevel;

import java.io.*;
import java.util.Calendar;

public class Machine implements Runnable{
    String name;
    String port;

    File file;
    File settingFile;

    Calendar nextExecTime;

    int xPixels,yPixels;
    public Machine(File machineDir){
        file=machineDir;
        settingFile=new File(machineDir,"settings.txt");

        try {
            FileReader fileReader=new FileReader(settingFile);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String s=bufferedReader.readLine();
            while(s!=null){
                String[] ss=s.split(": ");
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
    public Machine(String name, String port){
        this.name=name;
        this.port=port;
        this.writeBack();
    }

    private void writeBack() {

    }


    @Override
    public void run() {
        System.out.println("run");
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

    public Calendar getNextExecTime() {
        return nextExecTime;
    }

    public void setNextExecTime(Calendar nextExecTime) {
        this.nextExecTime = nextExecTime;
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
}
