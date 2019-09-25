package com.example.ryan.roomrep.Classes;

import java.util.List;

public class LanguageTranslation {
    List<String> wordsInOtherLanguage;
    List<String> wordsInEnglish;
    String imgUrl;
    String category;
    String language;

    public LanguageTranslation(List<String> wordsInOtherLanguage, List<String> wordsInEnglish, String imgUrl, String category){
        this.wordsInEnglish = wordsInEnglish;
        this.wordsInOtherLanguage = wordsInOtherLanguage;
        this.imgUrl = imgUrl;
        this.category = category;
    }

    public List<String> getWordsInOtherLanguage() {
        return wordsInOtherLanguage;
    }

    public void setWordsInOtherLanguage(List<String> wordsInOtherLanguage) {
        this.wordsInOtherLanguage = wordsInOtherLanguage;
    }

    public List<String> getWordsInEnglish() {
        return wordsInEnglish;
    }

    public void setWordsInEnglish(List<String> wordsInEnglish) {
        this.wordsInEnglish = wordsInEnglish;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
