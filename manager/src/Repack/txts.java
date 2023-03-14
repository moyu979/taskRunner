package Repack;

import java.io.*;
import java.util.Stack;

public class txts {
    public static String readTxt(String path){
        String toReturn="";
        try {
            File file=new File(path);
            FileReader fileReader= null;
            fileReader = new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);

            String line=bufferedReader.readLine();
            while(line!=null){
                toReturn=toReturn+line+"\n";
                line=bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return toReturn;
    }
    public static void writeTxt(String s,File toWrite){
        try {
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(toWrite));
            bufferedWriter.write(s);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
