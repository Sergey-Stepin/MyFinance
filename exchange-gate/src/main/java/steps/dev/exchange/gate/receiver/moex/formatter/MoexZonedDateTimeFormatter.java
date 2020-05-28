/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.exchange.gate.receiver.moex.formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;
import java.util.Locale;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**
 *
 * @author stepin
 */
//@Component
public class MoexZonedDateTimeFormatter implements Formatter<ZonedDateTime> {

    public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(FORMAT, Locale.forLanguageTag("ru_RU"));

    public MoexZonedDateTimeFormatter() {
        super();
    }

    @Override
    public String print(ZonedDateTime dateTime, Locale locale) {
        //return new SimpleDateFormat(FORMAT).format(dateTime);
        return ISO_ZONED_DATE_TIME.format(dateTime);
    }

    @Override
    public ZonedDateTime parse(String strDateTime, Locale locale) throws ParseException {
        LocalDateTime localDateTime = LocalDateTime.parse(strDateTime, DATE_TIME_FORMATTER);
        return ZonedDateTime.of(localDateTime, ZoneId.of("Europe/Moscow"));
    }

}
