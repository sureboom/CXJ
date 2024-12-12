package com.ls.checkin.util;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {
    // 生成随机盐
    public static String generateSalt() {
        byte[] salt = new byte[8];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // 加密密码
    public static String hashPassword(String password, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt.getBytes()); // 加入盐
        byte[] hashedPassword = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    // 验证密码
    public static boolean verifyPassword(String password, String hashedPassword, String salt) throws Exception {
        String hashed = hashPassword(password, salt);
        return hashed.equals(hashedPassword);
    }
}