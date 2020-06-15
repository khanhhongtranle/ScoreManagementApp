package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static boolean isDateStringValid(String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
        simpleDateFormat.setLenient(true);
        try{
            Date date = simpleDateFormat.parse(dateStr);
            return true;
        }
        catch(ParseException e){
            return false;
        }
    }
}
