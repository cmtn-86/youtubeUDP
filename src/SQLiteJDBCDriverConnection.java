import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLiteJDBCDriverConnection {
    /**
     * Connect to a sample database
     * @return
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:HomeSmart.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Insert a new row into the warehouses table
     *
     * @param name
     * @param capacity
     */
    public void insertServer(String client_name, int port,int data_length, String data) {
        // TODO  date buradan eklensin
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
        String date = sdf.format(new Date());
        String sql = "INSERT INTO Server(client_name,port,data_length,data,received_date) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, client_name);
            pstmt.setInt(2, port);
            pstmt.setInt(3, data_length);
            pstmt.setString(4, data);
            pstmt.setString(5, date);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sq msg "+e.getMessage());
        }
    }
/*

    public static void main(String[] args) {
        //connect();
                    InsertApp app = new InsertApp();
            // insert three new rows
            app.insert("Raw Materials", 3000);
            app.insert("Semifinished Goods", 4000);
            app.insert("Finished Goods", 5000);

    }

    */

}