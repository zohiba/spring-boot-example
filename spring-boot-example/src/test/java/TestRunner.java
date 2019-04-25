import edu.uci.adcom.util.CryptoJCE_AES;
import java.sql.*;

public class TestRunner {
    static final String USER = "exfiles_webuser";
    static final String PASS = CryptoJCE_AES.getPee(USER);
    static String to_be_displayed = "Start ";
    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String DB_URL = "jdbc:sqlserver://127.0.0.1:4430";

    public static void main(String[] args) {
        //System.out.println(getPassword("exfiles_webuser"));

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT l_contact_seq, s_ucinetid FROM dbo.contact";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("l_contact_seq");
                String first = rs.getString("s_ucinetid");
                to_be_displayed+=id +" ";
                to_be_displayed+= first+ " ";


                //Display values
                System.out.print("ID: " + id);
                System.out.print(", First: " + first);

            }
            System.out.print(to_be_displayed);

            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }
}
