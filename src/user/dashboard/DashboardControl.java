package user.dashboard;
import db.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DashboardControl {
    private  JDBCConnection DB;

    public DashboardControl(){
        DB  = new JDBCConnection();
    }


    public  int  getTodayPayments(){
        String date = LocalDate.now().toString();
        int count = 0;
        ResultSet resultSet = DB.getRecords("SELECT * FROM pagos");
        try {
            while (resultSet.next()) {
                
                if (resultSet.getString("fecha").split(" ")[0].equals(date)){
                    count+=1;
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return count;
    }


}
