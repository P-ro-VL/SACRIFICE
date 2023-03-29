package vn.provl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date begin = dateFormat.parse("27/05/2023");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(begin);
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        System.out.println(dateFormat.format(calendar.getTime()));
    }
}
