package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        if (args.length != 2) {
            System.out.println("Please input 2 arguments when running this file.");
            System.out.println("e.g. java */App C:\\directory filename.txt");
            System.out.println("Number of args: " + args.length);
            return;
        }

        String filePath = args[0];
        String fileName = args[1];
        String filePathName = filePath + File.separator + fileName;

        // create new directory if it does not exist
        File newDir = new File(filePath);
        if(newDir.exists()) {
            System.out.println("Directory " + filePath + " already exists.");
        } else {
            newDir.mkdir();
            System.out.println("New directory created.");
        }

        // create new file if it does not exist
        File newFile = new File(filePathName);
        if(newFile.exists()) {
            System.out.println("File " + fileName + " already exists in " + filePath + ".");
        } else {
            newFile.createNewFile();
            System.out.println("New file created.");
        }

        // write test data to file
        String dataStr = "This is a sentence to be written to the file.\n";
        FileWriter fw = new FileWriter(filePathName, true);
        // FileWriter fwFalse = new FileWriter(filePathName, false);
        // // fw.write(dataStr);
        // fwFalse.write("this is a new test \n");
        fw.write(dataStr);
        fw.flush();
        fw.close();
        // fwFalse.flush();
        // fwFalse.close();

        // decorator example
        String dataStr2 = "This is another sentence for testing.\n";
        FileWriter fw2 = new FileWriter(filePathName, true);
        BufferedWriter bw = new BufferedWriter(fw2);
        bw.append(dataStr2);
        bw.flush();
        bw.close();
        fw2.close();

        // another example using FileOutputStream
        String dataStr3 = "This computer is a potato.\n";
        FileOutputStream fos = new FileOutputStream(filePathName, true);
        byte[] byteDataStr3 = dataStr3.getBytes();
        fos.write(byteDataStr3);
        fos.flush();
        fos.close();

        // another example with decorator
        String dataStr4 = "Hello parallel world.\n";
        FileOutputStream fos2 = new FileOutputStream(filePathName, true);
        DataOutputStream dos = new DataOutputStream(fos2);
        dos.writeBytes(dataStr4);
        dos.flush();
        dos.close();
        fos2.close();

        // reading from file
        FileReader fr = new FileReader(filePathName);
        int dataRead = fr.read();
        while (dataRead != -1) {
            System.out.printf("%c", (char)dataRead);
            dataRead = fr.read();
        }
        fr.close();

        // another example using BufferedReader
        FileReader fr2 = new FileReader(filePathName);
        BufferedReader br = new BufferedReader(fr2);
        String line = "";
        line = br.readLine();

        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }
        br.close();
        fr2.close();

        // another example with decorator
        FileInputStream fis = new FileInputStream(filePathName);
        DataInputStream dis = new DataInputStream(fis);
        // String dataStrUTF = dis.readUTF();
        int dataRead2 = dis.read();
        while (dataRead2 != -1) {
            System.out.print((char) dataRead2);
            dataRead2 = dis.read();
        }
        dis.close();
        fis.close();
    }
}
