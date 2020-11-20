package com.fire.es.test;

import org.apache.commons.lang3.StringUtils;

public class MainTest {

    static final String SP_1 = "*";

    public static void main(String[] args) {

        String name = "张三";
        getStr(name);
        name = "王老六";
        getStr(name);
        name = "欧阳王五";
        getStr(name);

        String id1 = "412727281638469";
        System.out.println(leftHide(id1,4));

        String id2 = "412727281638461952";
        System.out.println(leftHide(id2,4));

    }

    /**
     * 抽取传递不同姓名的方法
     * @param name
     */
   static void getStr(String name){
        //名字长度为2时，只脱敏右边
        if(name.length() <= 2){
            System.out.println(rightHide(name,1));
        }else{
            //名字长度大于2时，需要中间脱敏，保留两边
            System.out.println(hide(name,1,1));
        }

    }



    /**
     *
     * 脱敏字符串右边
     * @param str 要处理的目标字符串
     * @param before 左边保留位数
     * @return
     */
    public static String rightHide(String str,Integer before){
        return StringUtils.rightPad(StringUtils.left(str, before),  StringUtils.length(str),SP_1);
    }

    /**
     * 脱敏字符串左边
     * @param str 要处理的目标字符串
     * @param before 右边保留位数
     * @return
     */
    public static String leftHide(String str,Integer before){
        return StringUtils.leftPad(StringUtils.right(str, before),  StringUtils.length(str),SP_1);
    }

    /**
     *
     * @param str 要处理的目标字符串
     * @param before 前面保留位数
     * @param after  后面保留的位数
     * @return
     */
    public static String hide(String str,Integer before,Integer after){
        String beforeNum = str.substring(0, before);
        String afterNum = str.substring(str.length() - after, str.length());
        return beforeNum+""+StringUtils.leftPad(afterNum,str.length()-before,SP_1);
    }


}
