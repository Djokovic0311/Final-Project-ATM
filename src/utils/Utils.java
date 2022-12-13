package utils;

import model.Customer;

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
        return Objects.hash(userName);
    }
    public static long getTimestamp() {
        return Calendar.getInstance().getTimeInMillis();
    }
}
