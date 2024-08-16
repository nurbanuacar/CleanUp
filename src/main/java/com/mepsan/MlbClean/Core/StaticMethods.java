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
        System.out.println("============== DATE ============= " + cal.getTime());
        return cal.getTime();
    }

    public static Date getStartOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println("============== START OF TODAY ============= " + cal.getTime());
        return cal.getTime();
    }

    public static Date getEndOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        System.out.println("============== END OF TODAY ============= " + cal.getTime());
        return cal.getTime();
    }

    public static Date getStartOfYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println("============== START OF YESTERDAY ============= " + cal.getTime());
        return cal.getTime();
    }

    public static Date getEndOfYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        System.out.println("============== END OF YESTERDAY ============= " + cal.getTime());
        return cal.getTime();
    }

    public static Date getLastWeekDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -6);
        return cal.getTime();
    }

    public static Date getLastMonthDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    public static Date convertStringToDate(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.parse(dateString);
    }

    public static String dateToString(Date date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
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

    public static Date findWeekStart() {
        int numberOfDay = getDayOfWeek() - 1;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -numberOfDay);
        System.out.println("-------- hafta bası tarih ---- " + cal.getTime());
        return cal.getTime();
    }

}
