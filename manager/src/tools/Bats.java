package Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Bats {
        public static String runCmd(String cmd) {
            Process p= null;
            String res="";
            String command="adb "+cmd;//以后可能替换成自己的adb
            try {
                p=Runtime.getRuntime().exec(command);
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
