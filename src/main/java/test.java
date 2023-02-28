import tools.Bat;

public class test {
    public static void main(String[] args) {
        String s=Bat.run("adb devices");
        String[] ss=s.split("\n");
        for(String sss:ss){
            System.out.println(sss+"|");
        }

    }
}
