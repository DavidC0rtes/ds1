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
    private LocalDateTime date;
    private LocalDateTime start;
    private LocalDateTime end;
    long tiempoDelMantenimiento ;
    private Duration diff;


    public ConfigControl(){
        DB  = new JDBCConnection();
        date = LocalDateTime.now();

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
                    System.out.println("start " + start);
                    System.out.println("end " + end);
                    System.out.println(LocalDateTime.now());

                    tiempoDelMantenimiento = Duration.between(date, end).getSeconds();
                    System.out.println("Segundos + " + tiempoDelMantenimiento);
                    diff = Duration.between(date, end);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getHMS(){
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
