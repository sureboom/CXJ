package com.ls.checkin;

import com.ls.checkin.util.PasswordUtil;

public class SecurityTest {
    public static void main(String[] args) throws Exception {
        String salt=PasswordUtil.generateSalt();
        System.out.println(salt);
        String password="123456";
        String hashedPassword=PasswordUtil.hashPassword(password, salt);
        System.out.println("password:"+hashedPassword);
        //boolean isMatch=PasswordUtil.checkPassword(password, hashedPassword, salt);
    }
}
