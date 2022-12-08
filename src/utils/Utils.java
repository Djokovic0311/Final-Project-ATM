package utils;

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
}
