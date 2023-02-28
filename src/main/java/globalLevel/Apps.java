package globalLevel;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

public class Apps {
    HashMap<String, Class> runApps;
    public boolean AddApp(){return true;}
    public boolean stopApp(){return true;}
    Apps(File[] jars){
        runApps=new HashMap<>();
        for(File f:jars){
            try {
                URL url=new URL("file:"+f.getPath());
                URLClassLoader urlClassLoader=new URLClassLoader(new URL[]{url});
                Class myClass=(Class)urlClassLoader.loadClass("AppLevel.runner");
                runApps.put(f.getName().replace(".jar",""),myClass);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Class findApp(String name){
        Class app=runApps.get(name);
        if(app==null){
            System.out.println("not exist app");
        }
        return app;
    }
    public void addAnApp(String name,Class app){
        ;
    }
    public String toString(){
        String toReturn="";
        for(String name: runApps.keySet()){
            toReturn=toReturn+name;
        }
        return toReturn;
    }
    public String getInfo(){
        String s="";
        for(String name:runApps.keySet()){
            s=s+name+"\n";

        }
        return s;
    }
}
