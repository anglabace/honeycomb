package com.cmaple.honeycomb;

public class test {

    public static void main(String[] args) {
        int i = Runtime.getRuntime().availableProcessors(); //获取cpu数量
        System.out.println("本机CPU数量：【" + i + "】");

        long l = Runtime.getRuntime().totalMemory();
        int il = ((int) l) / 1024;
        System.out.println(l +"本机内存：【" + il + "】");
    }
}