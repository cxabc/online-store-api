package com.maidao.edu;

import org.junit.Test;

/**
 * @author: jc.cp
 * @date: 2024/3/4 11:07
 * @description: TODO
 **/
public class Try {

    @Test
    public void test() {
        int i = -1;
        System.out.println("初始数据：" + i);
        System.out.println("初始数据对应的二进制字符串：" + Integer.toBinaryString(i));
        i <<= 10;
        System.out.println("左移 10 位后的数据 " + i);
        System.out.println("左移 10 位后的数据对应的二进制字符 " + Integer.toBinaryString(i));

    }
}
