package Entry;

import globalLevel.Controller;

import java.io.File;
import java.util.Scanner;

public class Cmd extends Entry {

    public Cmd(File userFile, File softFile, Controller controller){
        super(userFile,softFile,controller);
    }
    public Cmd(File userFile, File softFile){
        super(userFile,softFile);
    }
    @Override
    public Entry run() {
        System.out.println("cmd model(e to exit)");
        Scanner scanner=new Scanner(System.in);
        do{
            String cmd=scanner.next();
            switch (cmd){
                case "e"-> {
                    return null;
                }case "newMechine" ->{
                    newMachine();
                }case "newApp" -> {
                    newApp();
                }case "run"-> {
                    controller.run();
                    System.out.println("started");
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
    //newMachine
    private void newMachine() {
        controller.addMachine();
    }
    @Override
    public String getMessage(String ask) {
        System.out.println(ask);
        Scanner scanner=new Scanner(System.in);
        String answer=scanner.next();
        return answer;
    }
    public String getInfo(){
        String s=controller.getInfo();
        System.out.println(s);
        return s;
    }
    private void newApp() {
    }



}
