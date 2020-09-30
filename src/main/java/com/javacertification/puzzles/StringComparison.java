package com.javacertification.puzzles;

public class StringComparison {
    public static void main(String[] args) {
        String s1 = "Hola";
        String s2 = "Ho";
        String s3 = "la";
        System.out.println(s1 == s2 + s3);
        System.out.println(s1 == s2.concat(s3).intern());
    }
}
