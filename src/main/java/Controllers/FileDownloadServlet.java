package Controllers;

import model.FileReaderSystem;

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

    private final static String DIR_NAME = "uploads";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //Constructing path to file location
        String filePath = "src" + File.separator + DIR_NAME + File.separator;

        //Getting the file name
        if(DirectoryService.getFile(filePath) != null){
            String filename = DirectoryService.getFile(filePath).getName();
            File file = FileReaderSystem.reviewFile();

            response.setContentType("APPLICATION/OCTET_STREAM");
            response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"" );

            if(file != null){
                //FileInputStream fs = new FileInputStream(filePath + filename);
                FileInputStream fs = new FileInputStream(file);
                int i;
                while((i = fs.read()) != -1){
                    out.write(i);
                }

                fs.close();
                out.close();
            }
        }

    }

}
