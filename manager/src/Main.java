import Entry.*;

import java.io.File;

public class Main {
    static Entry entry=null;
    public static void main(String[] args) {
        //���Խ׶�Ȧ������
        File userFile=new File("./users");
        File softFile=new File("./softSetting");
        args=new String[]{"cmd"};
        //��������
        if(args.length==0){
            entry=new Cmd(userFile,softFile);
        }else{
            switch (args[0]){
                case "help"->entry=new Help(userFile,softFile);
                case "GUI" ->entry=new GUI(userFile,softFile);
                default ->entry=new Cmd(userFile,softFile);
            }
        }
        //������
        do{
            entry=entry.run();
        }while (entry!=null);
    }
}
