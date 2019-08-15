package DB;

import model.LoginObject;
import model.RegisterObject;

import java.sql.*;

public class RegisterDatabase implements AutoCloseable{

    static final String DB_URL = "jdbc:h2:~/userdb";
    static final String DRIVER = "org.h2.Driver";

    //Database access credentials
    static final String USER="";
    static final String PASS="";

    private Connection connection;
    private Statement statement;

    //getting DB connection
    static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(DB_URL,USER,PASS);
    }


    //Constructor - Gets connection & creates database table.
    public RegisterDatabase(){

        try {
            //getting connection
            connection = getConnection();

            //creating table
            statement = connection.createStatement();

            String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS userTable"+
                    "(id int NOT NULL AUTO_INCREMENT," +
                    "email VARCHAR(255)," +
                    "password VARCHAR(255)," +
                    "PRIMARY KEY(id))";

            statement.executeUpdate(CREATE_TABLE_QUERY);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public void addHashedPass(RegisterObject registerObject){

        final String ADD_HASHED_PASSWORD = "INSERT INTO userTable (email, password) VALUES (?,?)";

        try(PreparedStatement ps = connection.prepareStatement(ADD_HASHED_PASSWORD)){
            ps.setString(1,registerObject.getEmail());
            ps.setString(2,registerObject.getPassword());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close(){
        try{
            if(connection != null){
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public String authenticateUser(LoginObject loginObject) {
        final String GET_USER_CREDENTIALS = "SELECT * FROM userTable WHERE email = ?";
        String email = null;
        String password = null;

        try (PreparedStatement ps = connection.prepareStatement(GET_USER_CREDENTIALS)) {
            ps.setString(1, loginObject.getEmail());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                email = rs.getString("email");
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (loginObject.getEmail().equals(email)) {
            return password;
        }
        return null;
    }
}

