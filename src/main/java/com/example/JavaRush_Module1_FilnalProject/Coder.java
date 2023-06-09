package com.example.JavaRush_Module1_FilnalProject;

import java.util.Arrays;

public class Coder implements DecryptAble {
    private static final char[] ALPHABET = Alphabet.getAlphabet();
    private static int key;

    public static int getKey() {
        return key;
    }

    public static void setKey(int key) {
        Coder.key = key;
        System.out.println("Установлен ключ: " + key);
    }

    public static StringBuilder encrypt(StringBuilder text, int key) {
        if (key >= 0) {
            Coder.key = key;
        } else {
            Coder.key = -key;
        }
        System.out.println("Установлен ключ: " + key);
        char[] result = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                if (Character.isUpperCase(ch)) {    // Заглавные буквы
                    result[i] = Character.toUpperCase(encryptChar(Character.toLowerCase(ch)));
                } else {    // Строчные буквы
                    result[i] = encryptChar(ch);
                }
            } else {
                result[i] = encryptChar(ch);
            }
        }
        StringBuilder resultSB = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            resultSB.append(result[i]); // Записываем результат в стрингбилдер
        }
        return resultSB;
    }

    private static char encryptChar(char ch) {
//        int index = Arrays.binarySearch(ALPHABET, ch);  // Определение индекса символа в алфавите
        int index = 0;
        for (int i = 0; i < ALPHABET.length; i++) {
            if (ALPHABET[i]==ch) {
                index = (i + key) % ALPHABET.length;  // Получаем новый индекс с учетом сдвига
                return ALPHABET[index];
            }
        }
        return ch;
    }

    public static StringBuilder decrypt(StringBuilder text, int key) {
        if (key >= 0) {
            Coder.key = key;
        } else {
            Coder.key = -key;
        }
        System.out.println("Установлен ключ: " + key);
        char[] result = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                if (Character.isUpperCase(ch)) {
                    result[i] = Character.toUpperCase(decryptChar(Character.toLowerCase(ch)));
                } else {
                    result[i] = decryptChar(ch);
                }
            } else {
                result[i] = decryptChar(ch);
            }
        }
        StringBuilder resultSB = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            resultSB.append(result[i]); // Записываем результат в стрингбилдер
        }
        return resultSB;
    }

    private static char decryptChar(char ch) {
//        int index = Arrays.binarySearch(ALPHABET, ch);
        int index = 0;
        for (int i = 0; i < ALPHABET.length; i++) {
            if (ALPHABET[i]==ch) {
                index = (i - key + ALPHABET.length) % ALPHABET.length;  // Получаем новый индекс с учетом сдвига
                return ALPHABET[index];
            }
        }
        return ch;
    }
}
