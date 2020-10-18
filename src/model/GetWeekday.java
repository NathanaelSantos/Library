
package model;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class GetWeekday {

    public String weekDay(Calendar calendar) {
        return new DateFormatSymbols().getWeekdays()[calendar.get(Calendar.DAY_OF_WEEK)];
    }
}
