package com.yuyoubang.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiaoyuan on 16/11/7.
 */
public class CommonUtis {
    public  static  boolean checkPhoneNumber(String number) {
        number = number.replace(" ", "");
        Pattern p = Pattern.compile("1\\d{10}");
        Matcher m = p.matcher(number);
        return m.matches() && (number.length() == 11);
    }

}
