package org.agileboard.common;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 1/29/2017.
 */
public class CommonManager {

        public static String convertToUtf8(String input){
            String result = null;
            try {
                result = new String(input.getBytes("ISO-8859-1"), "UTF-8");
            }
            catch (java.io.UnsupportedEncodingException e) {
                return null;
            }
            return result;
        }

        public static String getCurrentDate(String dateFormat){
            Date now=new Date();
            SimpleDateFormat formater=new SimpleDateFormat(dateFormat);
            return formater.format(now);
        }
        public static String getDateInPastOrFuture(String dayMonthYear,int increment){
            Date now=new Date();
            Calendar date=Calendar.getInstance();
            date.setTime(now);
            switch(dayMonthYear){
                case "day": date.add(Calendar.DATE, increment);break;
                case "month":date.add(Calendar.MONTH,increment);break;
                case "year":date.add(Calendar.YEAR,increment);break;
                default:date.add(Calendar.DATE,increment);
            }
            return new SimpleDateFormat("dd-MM-yyyy").format(date.getTime());
        }

        public static void waitSeconds(int seconds){
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getRandomTaskName(){
            return "Task_"+ RandomStringUtils.randomAlphanumeric(7);
        }

    }


