package utils;

import model.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

/*
    This is the global utils class
    @author: Jiahang Li
    @version: 1.0
*/
public class Utils {
    public static boolean isEmpty(String str) {
        return "".equals(str) || str == null;
    }
     public static int getFixedLengthRandom(int len) {
        int rs = (int) ((Math.random() * 9 + 1) * Math.pow(10, len - 1));
        return rs;
     }
    public static String getFixedLengthRandomString(int len) {

        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random r = new Random();
        StringBuffer buff = new StringBuffer();//StringBuffer
        for (int i = 0; i < len; i++) {
            int it = r.nextInt(62);
            buff.append(str.charAt(it));

        }
        return buff.toString();
    }
     // hash username to id
    public static int createHashCodeForPersonId(String userName){

        return Math.abs(Objects.hash(userName));
    }
    public static long getTimestamp() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public String getStringFormatDate(long longFormatDate) {
        return String.format("%tF %<tT", longFormatDate);
    }

    public static int dayPass(long oldDate, long newDate) {
        return (int) ((newDate-oldDate)/1000/60/60/24);
    }
    public static double redeem(double balance, int dayPass) {
        double new_balance = balance;
        ATMConstant a = new ATMConstant();
        for (int i = 0; i < dayPass; i ++) {
            new_balance *= a.getREDEEM_INTEREST();
        }
        return new_balance;
    }

    public static long dateToStamp(String day, String month) throws ParseException {
        String s = "2022-"+month +"-"+day+" 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        return date.getTime();
    }
}
