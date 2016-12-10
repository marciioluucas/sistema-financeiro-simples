package empresa.util;


import sun.util.calendar.BaseCalendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by marci on 07/12/2016.
 */
public class Datas {

    public static String retornaData(Date data){
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
        return formatador.format( data );
    }

    public static Date retornaDataTipoDateByString(String data){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
