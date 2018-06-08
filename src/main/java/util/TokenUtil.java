package util;

public class TokenUtil {
    public static String getToken(Object o) {
        return AESUtil.encode(o.toString());
    }
}