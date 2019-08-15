package Controllers;

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
import java.io.PrintWriter;
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

        //Checking if the request has a file uploaded
        if(!ServletFileUpload.isMultipartContent(request)){
            PrintWriter pw = response.getWriter();
            pw.flush();
            return;
        }

        //Declaring a ServletFileUpload object that will help us get the uploaded file
        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
        String uploadFilePath = "src/" + File.separator + UPLOAD_DIR;

        //Create a new directory if directory does not exist
        File dir = new File(uploadFilePath);
        if(!dir.exists()){
            dir.mkdir();
        }

        try{

            List<FileItem> files = sf.parseRequest(request);

            if(files.size() > 0 && files != null){
                //storing file in the folder
                for(FileItem item : files){

                    if(!item.isFormField()){
                        //get the file name
                        String name = new File(item.getName()).getName();

                        //create its filepath
                        String filepath = uploadFilePath + File.separator + name;

                        //Create its object
                        File uploadedFile = new File(filepath);

                        //store it in the folder
                        item.write(uploadedFile);

                        //sending feedback message
                        request.setAttribute("feedback", "File uploaded! Wait for review...");
                    }
                }
            }
        }catch(Exception e){
            //Sending error feedback
            System.out.println(e);
            request.setAttribute("feedback","Error occurred while uploading file. Try again later");
        }

        //constructing directory path where to save the file.
        request.getRequestDispatcher("/WEB-INF/views/downloadpage.jsp").forward(request, response);

    }

}
