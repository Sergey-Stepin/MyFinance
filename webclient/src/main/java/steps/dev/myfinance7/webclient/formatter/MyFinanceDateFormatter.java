/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.webclient.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author stepin
 */
public class MyFinanceDateFormatter implements Formatter<Date> {

    public static String ttt = "test text";

    public static final String FORMAT = "yyyy-MM-dd";

    public MyFinanceDateFormatter() {
        super();
    }

    @Override
    public String print(Date date, Locale locale) {
        return new SimpleDateFormat(FORMAT).format(date);
    }

    @Override
    public Date parse(String strDate, Locale locale) throws ParseException {
        return new SimpleDateFormat(FORMAT).parse(strDate);
    }

//    public static Date getCurrentDate() {
//        return new Date();
//    }

}
