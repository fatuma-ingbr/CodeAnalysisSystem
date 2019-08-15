package model;

import Controllers.DirectoryService;

import java.io.*;

public class FileReaderSystem {

    private static final String DIR_PATH = "src/uploads/";
    private static final String REVIEWED_PATH = "src/reviewed/";

    public static File reviewFile(){

        if(DirectoryService.getFile(DIR_PATH) != null) {

            File file = DirectoryService.getFile(DIR_PATH);

           try{
               if(file.exists()){
                   FileWriter fileWriter = new FileWriter(file,true);
                   BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
                   bufferWriter.write("I am working fine.");
                   bufferWriter.close();
                   fileWriter.close();
               }
           }catch(IOException e){
               System.out.println(e);
           }

        }

        return null;
    }

}
