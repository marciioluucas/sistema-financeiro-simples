package empresa.util;


import sun.util.calendar.BaseCalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by marci on 07/12/2016.
 */
public class Datas {

    public static String retornaData(Date data){
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
        return formatador.format( data );
    }
}
