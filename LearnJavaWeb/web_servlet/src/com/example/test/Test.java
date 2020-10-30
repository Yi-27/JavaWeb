package com.example.test;

/**
 * @author Yi-27
 * @create 2020-10-30 11:13
 */
public class Test {

    String s = new String("good");
    char[] ch = {'a', 'b', 'c'};

    public static void main(String[] args) {
        Test test = new Test();
        test.change(test.s, test.ch);
        System.out.print(test.s + " and ");
        System.out.println(test.ch);
    }

    public void change(String str, char ch[]){
        str = "test ok";
        ch[0] = 'g';
    }

}
