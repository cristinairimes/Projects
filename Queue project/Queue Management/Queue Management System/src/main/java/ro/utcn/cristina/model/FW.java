package ro.utcn.cristina.model;

import java.io.FileWriter;
import java.io.IOException;

public class FW {
     static FileWriter fileWriter=null;

    public FW(String f) {
        try {
            fileWriter= new FileWriter(f, false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void write(String s) {
        try {
            fileWriter.write(s);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
