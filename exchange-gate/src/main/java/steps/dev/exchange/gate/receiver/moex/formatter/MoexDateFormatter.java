/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.exchange.gate.receiver.moex.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**
 *
 * @author stepin
 */

//@Component
public class MoexDateFormatter implements Formatter<Date> {

    public static final String FORMAT = "yyyy-MM-dd";

    public MoexDateFormatter() {
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

}
