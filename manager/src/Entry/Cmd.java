package Entry;

import globalLevel.Controller;

import java.io.File;
import java.util.Scanner;

public class Cmd extends Entry {
    //初始化函数
    public Cmd(File userFile, File softFile){
        super(userFile,softFile);
    }
    //二次生成函数
    public Cmd(File userFile, File softFile, Controller controller){
        super(userFile,softFile,controller);
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
                    return new GUI(userFile,softFile,this.controller);
                }
            }
        }while(true);
    }




    private void runController() {
        if(controller.isRunning()){
            System.out.println("controller already run");
        }else{
            controller.run();
            System.out.println("controller start to run");
        }
    }
    private void newMachine(String cmd) {
        String[] cmds=cmd.split(" ");
        controller.addMachine(cmds);
    }
    private void newApp(String cmd) {
        String[] cmds=cmd.split(" ");
        controller.addApp(cmds);
    }

    @Override
    public String getMessage(String ask) {
        System.out.println(ask);
        Scanner scanner=new Scanner(System.in);
        String answer=scanner.next();
        return answer;
    }

    @Override
    public void getInfo() {
        String s=controller.getInfo();
        System.out.println(s);
    }


}
