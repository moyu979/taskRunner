package Entry;

import java.io.File;
import java.util.Scanner;

public class Cmd extends Entry {

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
                    quit();
                    return null;
                }case "newMechine" ->{
                    newMechine();
                }case "run"->{
                    controller.run();
                    System.out.println("started");
                }
                case "gui" ->{
                    return new GUI(userFile,softFile);
                }
            }
        }while(true);
    }

    private void newMechine() {
    }

    private void quit() {
    }
}
