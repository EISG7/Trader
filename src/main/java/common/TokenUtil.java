package common;

public class TokenUtil {
    public static String getToken(int userId) {
        return AESUtil.encode(Integer.toString(userId));
    }
}