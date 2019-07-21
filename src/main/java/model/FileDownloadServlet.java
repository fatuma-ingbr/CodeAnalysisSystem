package model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns="/filedownload.do")
public class FileDownloadServlet extends HttpServlet {

    //private final static String DIR_NAME = "uploads";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //Getting application absolute path
        //String applicationPath = request.getServletContext().getRealPath("");

        //Constructing path to file location
        //String filePath = applicationPath + File.separator + DIR_NAME;
        String filePath = "C:/Users/Fatuma Ingabire/Desktop/Labs/CodeAnalysisSystem/src/main/webapp/";

        String filename = "uploadstestFile.txt";

        response.setContentType("APPLICATION/OCTET_STREAM");
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"" );

        FileInputStream fs = new FileInputStream(filePath + filename);
        int i;
        while((i = fs.read()) != -1){
            out.write(i);
        }

        fs.close();
        out.close();
    }

//    //Method to get file name from folder
//    private String getFileName(String filepath){
//        File folder = new File(filepath);
//        File[] files = folder.listFiles();
//
//        String filename = "";
//
//        for(int i = 0; i<files.length; i++){
//            if(files[i].isFile()){
//                filename = files[i].getName();
//            }
//        }
//
//        return filename;
//    }
}
