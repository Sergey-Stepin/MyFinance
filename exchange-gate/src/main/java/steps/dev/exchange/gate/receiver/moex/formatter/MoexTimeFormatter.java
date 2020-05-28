/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.exchange.gate.receiver.moex.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import static steps.dev.exchange.gate.receiver.moex.formatter.MoexZonedDateTimeFormatter.FORMAT;

/**
 *
 * @author stepin
 */

//@Component
public class MoexTimeFormatter implements Formatter<LocalTime> {

    public static final String FORMAT = "HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(FORMAT, Locale.forLanguageTag("ru_RU"));

    public MoexTimeFormatter() {
        super();
    }

    @Override
    public String print(LocalTime localTime, Locale locale) {
        return DATE_TIME_FORMATTER.format(localTime);
    }

    @Override
    public LocalTime parse(String strTime, Locale locale) throws ParseException {
        return LocalTime.parse(strTime, DATE_TIME_FORMATTER);
    }

}
