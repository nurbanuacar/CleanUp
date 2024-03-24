/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author nurbanu.acar
 */
public class StaticMethods {

    public static Date getYesterdayDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    public static Date getLastWeekDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

    public static Date getLastMonthDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        return cal.getTime();
    }

    public static Date convertStringToDate(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
        return dateFormat.parse(dateString);
    }

    public static int[] arrayOfZero() {
        int[] array = new int[7];

        for (int i = 0; i < array.length; i++) {
            array[i] = 0;
        }
        return array;
    }

    public static int getDayOfWeek() {
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        int dayOfWeekValue = dayOfWeek.getValue();
        return dayOfWeekValue;
    }

}
