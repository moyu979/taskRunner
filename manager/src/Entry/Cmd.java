package Entry;

import Vars.globalVars;
import globalLevel.Controller;

import java.io.File;
import java.util.Scanner;

public class Cmd extends Entry {
    //初始化函数
    public Cmd(File userFile, File softFile){
        super(userFile,softFile);
    }


    @Override
    public Entry run() {
        super.run();

        System.out.println("cmd model(e to exit)");
        Scanner scanner=new Scanner(System.in);
        do{
            String cmd=scanner.next();
            switch (cmd){
                case "e"-> {
                    return null;
                }case "run"-> {
                    runController();
                }
                case "newMachine" ->{
                    newMachine(cmd);
                }case "newApp" -> {
                    newApp(cmd);
                }
                case "getInfo"-> {
                    getInfo();
                }
                case "gui" ->{
                    return new GUI(userFile,softFile);
                }
            }
        }while(true);
    }




    private void runController() {
        if(globalVars.controller.isRunning()){
            System.out.println("controller already run");
        }else{
            globalVars.controller.run();
            System.out.println("controller start to run");
        }
    }
    private void newMachine(String cmd) {
        String[] cmds=cmd.split(" ");
        globalVars.controller.addMachine(cmds);
    }
    private void newApp(String cmd) {
        String[] cmds=cmd.split(" ");
        globalVars.controller.addApp(cmds);
    }

    @Override
    public String getMessage(String ask) {
        System.out.println(ask);
        Scanner scanner=new Scanner(System.in);
        String answer=scanner.next();
        return answer;
    }

    @Override
    public void showInfo(String message) {
        System.out.println(message);
    }
    @Override
    public void getInfo() {
        String s=globalVars.controller.getInfo();
        System.out.println(s);
    }


}
