package Entry;

import globalLevel.Controller;

import java.io.File;
public class GUI extends Entry {
    public GUI(File userFile, File softFile, Controller controller){
        super(userFile,softFile,controller);
    }
    public GUI(File userFile, File softFile){
        super(userFile,softFile);
    }
    @Override
    public Entry run() {
        return null;
    }
}
