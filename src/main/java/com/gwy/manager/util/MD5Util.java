package com.gwy.manager.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Tracy
 * @date 2020/11/4 14:33
 */
public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    /**
     * 第一次md5
     * @param inputPass
     * @return
     */
    private static String inputToForm(String inputPass) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    /**
     * 第二次md5
     * @param formPass
     * @return
     */
    private static String formToDB(String formPass) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    /**
     * 两次md5
     * @param inputPass
     * @return
     */
    public static String inputToDb(String inputPass) {
        String formPass = inputToForm(inputPass);
        return formToDB(formPass).toUpperCase();
    }
}
