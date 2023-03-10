package globalLevel;

import AppLevel.App;
import AppLevel.head;
import MachineLevel.Machine;
import Vars.globalVars;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

public class Apps {
    public HashMap<String, Class> runApps;
    Apps(File jar){
        System.out.println("init "+this.getClass().getName());
        File[] jars=jar.listFiles();

        runApps=new HashMap<>();

        App app=new head();
        runApps.put(app.getClass().getName(),app.getClass());

        for(File f:jars){
            addApp(f);
        }
    }

    public void addApp(File jar){
        try {
            URL url=new URL("file:"+jar.getPath());
            URLClassLoader urlClassLoader=new URLClassLoader(new URL[]{url});
            Class myClass=(Class)urlClassLoader.loadClass("AppLevel.runner");
            runApps.put(jar.getName().replace(".jar",""),myClass);
            if(globalVars.machines!=null){
                globalVars.machines.addApp(myClass);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public HashMap<String, Class> getRunApps() {
        return runApps;
    }

    public void setRunApps(HashMap<String, Class> runApps) {
        this.runApps = runApps;
    }

     public String getInfo(){
        String s="";
        for(String name:runApps.keySet()){
            s=s+name+"\n";
        }
        s+="\n";
        return s;
     }
}
