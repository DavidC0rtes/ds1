package sige.user.dashboard;
import  sige.db.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DashboardControl {
    private  static JDBCConnection DB;
    private static int expectedPayments = 0;
    private static int todayPayments = 0;
    private static String date;
    private static int activeUsers = 0;
    private static int activeClients = 0;



    public DashboardControl(){
        DB  = new JDBCConnection();
        date = LocalDate.now().toString();
    }


    public static void  updateData(){
        date = LocalDate.now().toString();
        //Actualizar los pagos realizados hoy
        ResultSet resultSet = DB.getRecords("SELECT * FROM pagos");
        try {
            while (resultSet.next()) {
                
                if (resultSet.getString("fecha").split(" ")[0].equals(date)){
                    todayPayments+=1;
                }
            }

            //Actualizar los pagos esperados hoy

            resultSet = DB.getRecords("SELECT * FROM contratos");
            while (resultSet.next()) {



                if (resultSet.getString("fecha_corte").equals(date.substring(8,10))){
                    expectedPayments+=1;
                }
            }
            //Actualizar los clientes activos
            resultSet = DB.getRecords("SELECT * FROM usuarios");
            while (resultSet.next()) {



                if (resultSet.getBoolean("activo")){
                    activeUsers+=1;
                }

            }
             //Actualizar los usuarios activos
            resultSet = DB.getRecords("SELECT * FROM clientes");
            while (resultSet.next()) {



                if (resultSet.getBoolean("activo")){
                    activeClients+=1;
                }

            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public  int getActiveUsers() {
        return activeUsers;
    }

    public  int getActiveClients() {
        return activeClients;
    }

    public int getExpectedPayments() {
        return expectedPayments;
    }

    public int getTodayPayments() {
        return todayPayments;
    }

}
