package Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static boolean isDateStringValid(String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setLenient(true);
        try{
            Date date = simpleDateFormat.parse(dateStr);
            return true;
        }
        catch(ParseException e){
            return false;
        }
    }

    public static int compareDateWithDate(String d1, String d2){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setLenient(true);
        Date date1 = new Date();
        Date date2 = new Date();
        try{
            date1 = simpleDateFormat.parse(d1);
            date2 = simpleDateFormat.parse(d2);
        }
        catch(ParseException e){
            e.printStackTrace();
        }
        //return date1.compareTo(date2);
        if (date1.after(date2)) {
            //System.out.println("Date1 is after Date2");
            return 1;
        }

        if (date1.before(date2)) {
            //System.out.println("Date1 is before Date2");
            return -1;
        }

        if (date1.equals(date2)) {
            //System.out.println("Date1 is equal Date2");
            return 0;
        }

        return 0;
        // > 0: date1 sau date2
        // == 0 : date1 bang date2
        // < 0 : date1 truoc date2
    }

    public static String getCurrDate(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(date);
    }
}
