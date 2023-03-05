package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Bat {
    public static String run(String cmd) {
        Process p= null;
        String res="";
        try {
            p=Runtime.getRuntime().exec(cmd);
            InputStream inputStream=p.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String s=null;
            while ((s=bufferedReader.readLine())!= null){
                res=res+s+"\n";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}
