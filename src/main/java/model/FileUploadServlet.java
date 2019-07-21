package model;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns="/fileupload.do")
public class FileUploadServlet extends HttpServlet {

    private final static String UPLOAD_DIR = "uploads";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // getting web application absolute path
        String applicationPath = request.getServletContext().getRealPath("");

        // constructing directory path to save uploaded file
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

        //Declaring a ServletFileUpload object that will help us get the uploaded file
        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());

        try{
            //getting file
            List<FileItem> files = sf.parseRequest(request);

            //storing file in the folder
            for(FileItem item : files){
                item.write(new File(uploadFilePath + item.getName()));
            }
        }catch(Exception e){
            System.out.println(e);
        }

        request.getRequestDispatcher("/WEB-INF/views/downloadpage.jsp").forward(request, response);

    }


}
