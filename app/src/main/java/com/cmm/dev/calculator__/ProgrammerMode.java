package com.cmm.dev.calculator__;

import java.util.StringTokenizer;

/**
 * Created by Maxi Dufter.
 */
public class ProgrammerMode extends SimpleMode {

    public static String toDec(int inputMode, String eingabe) {
        String result = " ";
        // from Dec
        if (inputMode == 1){
            result = eingabe;
        }
        // from Bin
        else if (inputMode == 2) {
            result = Long.toString(Long.parseLong(eingabe, 2));
        }
        // from Hex
        else if (inputMode == 3) {
            result = Long.toString(Long.parseLong(eingabe, 16));
        }
        return (result);
    }

    public static String toBin(int inputMode, String eingabe) {
        String result = " ";
        // from Dec
        if (inputMode == 1){
            result = Long.toBinaryString(Long.parseLong(eingabe));
        }
        // from Bin
        else if (inputMode == 2) {
            result = eingabe;
        }
        // from Hex
        else if (inputMode == 3) {
            result = Long.toBinaryString(Long.parseLong(eingabe, 16));
        }

        return (result);
    }

    public static String toHex(int inputMode, String eingabe) {
        String result = " ";
        // from Dec
        if (inputMode == 1){
            result = Long.toHexString(Long.parseLong(eingabe)).toUpperCase();
        }
        // from Bin
        else if (inputMode == 2) {
            result = Long.toHexString(Long.parseLong(eingabe, 2)).toUpperCase();
        }
        // from Hex
        else if (inputMode == 3) {
            result = eingabe;
        }

        return (result);
    }

    // Addition/Subtraktion/Multiplikation/Division zweier Hexadezimaler Zahlen
    public static String solveHex (String eingabe) {

        int result;
        int value1;
        int value2;
        for (int i = 0; i < eingabe.length(); i++) {
            if (eingabe.charAt(i) == '+') {
                result = hextodec(eingabe.substring(0, i))
                        + hextodec((eingabe.substring(i + 1, eingabe.length())));

                return (Integer.toString(result, 16).toUpperCase());

            } else if (eingabe.charAt(i) == '-') {
                result = hextodec(eingabe.substring(0, i))
                        - hextodec((eingabe.substring(i + 1, eingabe.length())));

                return (Integer.toString(result, 16).toUpperCase());

            } else if (eingabe.charAt(i) == '*') {
                result = hextodec(eingabe.substring(0, i))
                        * hextodec((eingabe.substring(i + 1, eingabe.length())));

                return (Integer.toString(result, 16).toUpperCase());

            } else if (eingabe.charAt(i) == '/') {
                result = hextodec(eingabe.substring(0, i))
                        / hextodec((eingabe.substring(i + 1, eingabe.length())));

                return (Integer.toString(result, 16).toUpperCase());

            }
        }

        return " ";
    }

    private static int hextodec(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }

    // binäre Addition/Subtraktion/Multiplikation/Divisions
    public static String solveBin (String eingabe) {

            int result = 0;
            int value1;
            int value2;
            for (int i = 0; i < eingabe.length(); i++) {
                if (eingabe.charAt(i) == '+') {
                    result = binaryToDecimal(Integer.parseInt(eingabe.substring(0, i)))
                            + binaryToDecimal(Integer.parseInt(eingabe.substring(i + 1, eingabe.length())));
                    return (Integer.toString(result, 2));

                } else if (eingabe.charAt(i) == '-') {
                    result = binaryToDecimal(Integer.parseInt(eingabe.substring(0, i)))
                            - binaryToDecimal(Integer.parseInt(eingabe.substring(i + 1, eingabe.length())));
                    return (Integer.toString(result, 2));

                } else if (eingabe.charAt(i) == '*') {
                    result = binaryToDecimal(Integer.parseInt(eingabe.substring(0, i)))
                            * binaryToDecimal(Integer.parseInt(eingabe.substring(i + 1, eingabe.length())));
                    return (Integer.toString(result, 2));

                } else if (eingabe.charAt(i) == '/') {
                    result = binaryToDecimal(Integer.parseInt(eingabe.substring(0, i)))
                            / binaryToDecimal(Integer.parseInt(eingabe.substring(i + 1, eingabe.length())));
                    return (Integer.toString(result, 2));
                }
            }

            return " ";
    }

    // Umwandlung von binär nach dezimal
    private static int binaryToDecimal(int binaryNumber){

        int decimal = 0;
        int p = 0;
        while(true){
            if(binaryNumber == 0){
                break;
            } else {
                int temp = binaryNumber%10;
                decimal += temp*Math.pow(2, p);
                binaryNumber = binaryNumber/10;
                p++;
            }
        }
        return decimal;
    }
}
