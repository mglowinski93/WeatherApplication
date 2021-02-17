package org.app.model;

import java.text.Format;
import java.text.SimpleDateFormat;

public class DateFormatter {

    public static String getDateFromUTC(Integer utcString) {

        java.util.Date time = new java.util.Date((long) utcString * 1000);
        Format formatter = new SimpleDateFormat("dd/MM/yyyy E");
        return formatter.format(time);

    }

}
