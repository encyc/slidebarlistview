package com.erning.slidebarlistview;

import java.util.Comparator;

/**
 * Created by 二宁 on 17-11-26.
 */

public class StringComparator implements Comparator<String> {
    @Override
    public int compare(String s, String t1) {
        //如果是空的
        if (s==null || s.isEmpty())
            return -1;
        if (t1==null || t1.isEmpty())
            return 1;

        s = PinyinDemo.ToFirstChar(s).toUpperCase();
        t1 = PinyinDemo.ToFirstChar(t1).toUpperCase();

        //转换为数组
        char[] char1 = s.toCharArray();
        char[] char2 = t1.toCharArray();

        //两个数字
        if (isNumber(char1) && isNumber(char2)){
            double num1 = Double.valueOf(s);
            double num2 = Double.valueOf(t1);
            if (num1 > num2)
                return 1;
            if (num1 < num2)
                return -1;
            if (num1 == num2)
                return 0;
        }
        //第二个是数字
        if (!isNumber(char1) && isNumber(char2)){
            return 1;
        }
        //第一个是数字
        if (isNumber(char1) && !isNumber(char2)){
            return -1;
        }
        //都不是数字
        if (!isNumber(char1) && !isNumber(char2)){
            int length = char1.length;
            if (length < char2.length)
                length = char2.length;

            for (int i=0;i<length;i++){
                char c1 = 1;
                char c2 = 1;
                try {
                    c1 = char1[i];
                    c2 = char2[i];
                    if (c1 > c2)
                        return 1;
                    if (c1 < c2)
                        return -1;
                }catch (IndexOutOfBoundsException e){
                    if (c1 == 1)
                        return -1;
                    if (c2 == 1)
                        return 1;
                }
            }
        }

        return 0;
    }

    public static boolean isNumber(char c){
        return c>=48 && c<=57;
    }
    public static boolean isNumber(char[] cs){
        for (char c:cs) {
            if (!isNumber(c))
                return false;
        }
        return true;
    }
}
