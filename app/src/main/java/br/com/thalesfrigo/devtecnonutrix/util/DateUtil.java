package br.com.thalesfrigo.devtecnonutrix.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by thalesfrigo on 12/21/16.
 */

public class DateUtil {

    public static String formatDate(Date date, String pattern){
        if(date != null && pattern != null){
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.format(date);
        }
        return null;
    }
}
