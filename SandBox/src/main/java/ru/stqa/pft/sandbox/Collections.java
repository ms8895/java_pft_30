package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main(String[] args) {
        String[] langs = {"Java", "Python", "C#", "PHP"};
        for (int i = 0; i < langs.length; i++) {
            System.out.println("Язык " + langs[i]);
        }
        for (String l : langs) {
            System.out.println("Выучи язык " + l);
        }

        List<String> languages = new ArrayList<String>();
        languages.add("C#");
        languages.add("Python");
        languages.add("Java");
        languages.add("PHP");
        for (String l : languages) {
            System.out.println("Я выучу язык " + l);
        }

        //One line array initialization
        List<String> firstLanguages = Arrays.asList("Java", "Python", "C#", "PHP");
        for (String l : firstLanguages) {
            System.out.println("Нужно выучить язык " + l);
        }

        //Iterating over the elements of a list with a counter variable
        List<String> twoLanguages = Arrays.asList("Java", "Python", "C#", "PHP");
        for (int i = 0; i < twoLanguages.size(); i++) {
            System.out.println("Язык " + twoLanguages.get(i));
        }
        //List of objects of arbitrary type
        List threeLanguages = Arrays.asList("Java", "Python", "C#", "PHP");
        for (Object l : threeLanguages) {
            System.out.println("Выучи " + l);
        }
    }
}
