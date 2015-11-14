package com.guidepointsecurity.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public Utils () {}

    public String currentDateTime() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy.mm.dd - hh:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String intToString(int number, int groupSize) {

        StringBuilder result = new StringBuilder();

        for(int i = 31 ; i >= 0 ; i--) {

            int mask = 1 << i;
            result.append((number & mask) != 0 ? "1" : "0");

            if(i % groupSize == 0) {
                result.append(" ");
            }

            result.replace(result.length() - 1, result.length(), "");
        }

        return result.toString();
    }
}
