package globalLevel;

import java.io.File;
import java.util.HashMap;

public class Apps {
    HashMap<String,Class> runApps;
    public boolean AddApp(){return true;}
    public boolean stopApp(){return true;}
    Apps(File[] jars){

    }

    public String toString(){
        String toReturn="";
        for(String name: runApps.keySet()){
            toReturn=toReturn+name;
        }
        return toReturn;
    }
}
