package Entry;

import globalLevel.Controller;

import java.io.File;

public abstract class Entry {
    File userFile;
    File softFile;
    Controller controller;
    //初始运行的构造函数(控制器自动生成)
    public Entry(File userFile,File softFile){
        setUserFile(userFile);
        setSoftFile(softFile);
        controller=new Controller(this);
    }
    //切换控制界面的构造函数(控制器继承自上一个)
    public Entry(File userFile, File softFile,Controller controller){
        setUserFile(userFile);
        setSoftFile(softFile);
        controller=controller;
    }
    //用于控制器和界面交互的回调函数,需要手动实现
    abstract public String getMessage(String ask);
    //获取控制器信息以显示的函数,需要手动实现
    abstract public void getInfo();
    //运行函数
    public Entry run(){
        controller.run();
        return null;
    }







    //getter and setter
    public File getUserFile() {
        return userFile;
    }

    public void setUserFile(File userFile) {
        if(!userFile.exists()){
            System.out.println("new userFile, please new machines manual");
            userFile.mkdir();
        }
        this.userFile = userFile;
    }

    public File getSoftFile() {
        return softFile;
    }

    public void setSoftFile(File softFile) {
        if(!softFile.exists()){
            System.out.println("new softFile, please load jars manal");
            softFile.mkdir();
        }
        this.softFile = softFile;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

}
