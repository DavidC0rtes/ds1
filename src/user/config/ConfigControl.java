package user.config;
import db.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;

public class ConfigControl {
    private  JDBCConnection DB;
    private static  LocalDateTime date;
    private static LocalDateTime start;
    private static  LocalDateTime end;


    long tiempoDelMantenimiento ;
    private static Duration diff;
    private boolean active;


    public ConfigControl(){
        DB  = new JDBCConnection();
        date = LocalDateTime.now();

    }

    public static void uptimeTime(){
        date = LocalDateTime.now();
        diff = Duration.between(date, end);

    }
    public  void  updateData(){
        date = LocalDateTime.now();
        //Actualizar los pagos realizados hoy
        ResultSet resultSet = DB.getRecords("SELECT * FROM mantenimiento");
        try {
            while (resultSet.next()) {
                if (resultSet.getInt(1) == 1){
                    start = resultSet.getObject(2, LocalDateTime.class);
                    end = resultSet.getObject(3, LocalDateTime.class);
                    active = resultSet.getBoolean(4);
                    System.out.println("start " + start);
                    System.out.println("end " + end);
                    System.out.println(LocalDateTime.now());

                    tiempoDelMantenimiento = Duration.between(date, end).getSeconds();
                    System.out.println("Segundos + " + tiempoDelMantenimiento);
                    diff = Duration.between(date, end);
                   if (tiempoDelMantenimiento <= 0) {
                       active = false;
                   }
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public static LocalDateTime getStart() {
        return start;
    }

    public static LocalDateTime getEnd() {
        return end;
    }
//Antes de usar este metodo hacer un update
    public boolean isActive() {
        return active;
    }

    public static void setStart(LocalDateTime start) {
        ConfigControl.start = start;
    }

    public static void setEnd(LocalDateTime end) {
        ConfigControl.end = end;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public static String getHMS(){
        String hms = String.format("%d:%02d:%02d",
                diff.toHours(),
                diff.toMinutesPart(),
                diff.toSecondsPart());
        return hms;
    }

    public long tiempoDelMantenimiento(){
        return tiempoDelMantenimiento;
    }
}
