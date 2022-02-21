package ru.stqa.pft.sandbox;

public class Equality {


    public static void main(String[] args) {
        System.out.println("///s1, 2///");

        String s1 = "fire";
        String s2 = s1;
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        System.out.println("///s3, 4///");

        String s3 = "fire";
        String s4 = new String(s3);
        System.out.println(s3 == s4);
        System.out.println(s3.equals(s4));

        System.out.println("///s5, 6///");

        String s5 = "fire";
        String s6 = "fire";
        System.out.println(s5 == s6);
        System.out.println(s5.equals(s6));

        System.out.println("///s7, 8///");

        String s7 = "fi" + "re";
        String s8 = "fire";
        System.out.println(s7 == s8);
        System.out.println(s7.equals(s8));

        System.out.println("///s9, 10///");

        String s9 = "fire 2.0";
        String s10 = "fire " + Math.sqrt(4.0);
        System.out.println(s9 == s10);
        System.out.println(s9.equals(s10));
    }
}

