package Entry;

import java.io.*;

public class Help extends Entry {

    public Help(File userFile, File softFile) {
        super(userFile, softFile);
    }

    @Override
    public Entry run() {
        System.out.println("help model");
        try{
            File file=new File("./src/main/resources/help");
            FileReader fileReader=new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String line=bufferedReader.readLine();
            while(line!=null){
                System.out.println(line);
                line=bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
