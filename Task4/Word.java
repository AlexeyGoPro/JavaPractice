package com.company;

public class Word {

    private char[] word;

    public void setWord(char[] word) {
        this.word = word;
    }

    public char[] getWord() {
        return word;
    }

    public Word(String strWord) {
        if (strWord == null || "".equals(strWord.trim())) {
            throw new IllegalArgumentException("Слово не может быть пустым");
        }
        this.word = strWord.toCharArray();
    }

    public int getSymbolCount() {
        return word.length;
    }

    public String getOriginalWord() {
        String str = "";
        for (int i = 0; i < word.length; i++) {
            str += word[i];
        }
        return str;
    }
}