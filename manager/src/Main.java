import Entry.*;

import java.io.File;

public class Main {
    static Entry entry=null;
    public static void main(String[] args) {
        //测试阶段圈定参数
        File userFile=new File("./users");
        File softFile=new File("./softSetting");
        args=new String[]{"cmd"};
        //参数解析
        if(args.length==0){entry=new Cmd(userFile,softFile);}
        else{
            switch (args[0]){
                //以help形式运行
                case "help"->entry=new Help(userFile,softFile);
                //以Gui形式运行
                case "GUI" ->entry=new GUI(userFile,softFile);
                //默认是cmd形式
                default ->entry=new Cmd(userFile,softFile);
            }
        }
        //不断跑
        do{
            entry=entry.run();
        }while (entry!=null);
    }
}