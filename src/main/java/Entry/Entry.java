package Entry;

import globalLevel.Controller;

import java.io.File;

public class Entry {
    File userFile;
    File softFile;

    Controller controller;
    public Entry(File userFile, File softFile){
        this.userFile=userFile;
        this.softFile=softFile;
        if(!this.userFile.exists()){
            this.userFile.mkdir();
        }
        if(!this.softFile.exists()){
            this.softFile.mkdir();
        }
        controller=new Controller(this);
    }
    //执行主体，本线程逻辑
    public Entry run() {
        return null;
    }

    //回调函数，显示要显示的信息
    public void getMessage(){ return; }


//get/set函数
    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public File getUserFile() {
        return userFile;
    }

    public void setUserFile(File userFile) {
        if(!userFile.exists()){
            userFile.mkdir();
        }
        this.userFile = userFile;
    }

    public File getSoftFile() {
        return softFile;
    }

    public void setSoftFile(File softFile) {
        if(!softFile.exists()){
            softFile.mkdir();
        }
        this.softFile = softFile;
    }

}
