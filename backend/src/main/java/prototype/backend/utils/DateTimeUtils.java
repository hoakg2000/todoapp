package prototype.backend.utils;

import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
    public static Date increaseDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.add(Calendar.DAY_OF_MONTH, days);

        return calendar.getTime();
    }
}
