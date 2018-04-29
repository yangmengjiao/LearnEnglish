package edu.mum.learnenglish.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Word {

    private String id;

    private String english;

    private String chinese;
    private String imageName;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public Word(String english, String chinese) {
        this.english = english;
        this.chinese = chinese;
    }
}
