package com.company;

import java.util.ArrayList;

public class Sentence {

    private ArrayList<Word> words = new ArrayList<Word>();

    public Sentence() {

    }

    public Sentence(String words) {
        String[] arrWords = words.split(" ");
        for (int i = 0; i < arrWords.length; i++) {
            this.words.add(new Word(arrWords[i]));
        }
    }

    public Sentence(String[] words) {
        for (String word : words) {
            this.words.add(new Word(word));
        }
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public void setWordsStr(String words) {
        String[] arrWords = words.split(" ");
        ArrayList<Word> temp = new ArrayList<Word>();
        for (int i = 0; i < arrWords.length; i++) {
            temp.add(new Word(arrWords[i]));
        }
        this.words = temp;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public int getWordsCount() {
        return words.size();
    }

    public String getOriginalSentence() {
        String str = "";
        for (Word word : words) {
            str += word.getOriginalWord() + " ";
        }
        return str;
    }

    public Word getWordByPosition(int pos) {
        return words.get(pos - 1);
    }

    public void deleteDataTime() {
        words.remove(words.size() - 1);
    }

}