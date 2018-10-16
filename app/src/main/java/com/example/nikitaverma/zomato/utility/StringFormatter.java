package com.example.nikitaverma.zomato.utility;

public class StringFormatter {

    public  static String normalCaseStringConverter(String txt){
        String txt2 =((txt.charAt(0)) + "").toUpperCase();
        return txt2.concat(txt.substring(1));
    }
}
