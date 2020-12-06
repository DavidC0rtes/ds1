/*
* Esta clase es responsable de mantener la información del mantenimiento entre la BD
* y el usuario, tambien se encarga de sus validaciones.
* */

package sige.user.config;
import  sige.db.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;


public class ConfigControl {
    private static JDBCConnection  DB  = new JDBCConnection();

    private static  LocalDateTime date;
    private static LocalDateTime start;
    private static  LocalDateTime end;
    private static String sms;


    long tiempoDelMantenimiento ;
    private static Duration diff;
    private static boolean active;


    public ConfigControl(){
        date = LocalDateTime.now();

    }

    public static void uptimeTime(){
        date = LocalDateTime.now();
        diff = Duration.between(date, end);

    }

    public static String getSms() {
        return sms;
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
                    sms = resultSet.getString("mensajes");
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
/*UPDATE public.mantenimiento
SET activo = false
WHERE id = 1;*/
    public static void changeData(LocalDateTime inicio, LocalDateTime fin, Boolean state, String sms){
        System.out.println(inicio.toString());
        System.out.println(Timestamp.valueOf(inicio).toString());

        String query = "UPDATE mantenimiento SET inicio = ?::timestamp, " +
                "fin = ?::timestamp, " +
                "activo = ?, " +
                "mensajes = ? " +
                "WHERE id = 1";
        String[] params = {Timestamp.valueOf(inicio).toString(), fin.toString(), state.toString().toLowerCase(), sms};
        DB.updateRecord(query, params);
        System.out.println("Mantenimiento actualizado");

    }

    public static LocalDateTime getStart() {
        return start;
    }

    public static LocalDateTime getEnd() {
        return end;
    }
//Antes de usar este metodo hacer un update
    public static boolean isActive() {
        return active;
    }

    public static void setStart(LocalDateTime start) {
        ConfigControl.start = start;
    }

    public static void setEnd(LocalDateTime end) {
        ConfigControl.end = end;
    }

    public static boolean validateDate(LocalDateTime ldt1, LocalDateTime ldt2){
        if (Duration.between(ldt1, ldt2).getSeconds() <= 0 ){
            return false;
        }
        else{
            return true;
        }
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
