package contoller;

import java.io.IOException;

public class XamppExec {

    final String APACHE_START = "C:\\xampp\\apache_start.bat";
    final String MYSQL_START = "C:\\xampp\\mysql_start.bat";
    final String APACHE_STOP = "C:\\xampp\\apache_stop.bat";
    final String MYSQL_STOP = "C:\\xampp\\mysql_stop.bat";
    
    
    public void startApacheAndMysql() {

        try {

            Runtime.getRuntime().exec(APACHE_START);
            Runtime.getRuntime().exec(MYSQL_START);
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public void closeApacheAndMysql() {

        try {

            Runtime.getRuntime().exec(APACHE_STOP);
            Runtime.getRuntime().exec(MYSQL_STOP);
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
