package tools;

import java.io.File;
import java.io.IOException;

public class FileAndXml {
    /**
     * 通过给定的路径和子目录列表新建文件或文件夹，判定是文件或文件夹的方式是后面有没有扩展名
     * @param f 新建文件的父目录
     * @param path 子目录列表
     * @return
     */
    public static File makeFile(File f, String... path) {
        File father=f;
        if(!father.exists()){
            newfile(father);
        }
        for (String c:path){
            father=new File(father,c);
            if(!father.exists()){
                newfile(father);
            }
        }
        return father;
    }

    /**
     * 观测指定目录是否存在并视情况新建文件或文件夹
     * @param father
     */
    private static void newfile(File father) {
        if(father.getName().contains(".")) {
            try {
                father.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            father.mkdir();
        }
    }
}
