package com.technocrats.ryftofficial;

public class Model {
    String techUsed1,techUsed2,title;

    public Model() {
    }

    public Model(String techUsed1, String techUsed2, String title) {
        this.techUsed1 = techUsed1;
        this.techUsed2 = techUsed2;
        this.title = title;
    }


    public String getTechUsed1()
    {
        return techUsed1;
    }

    public void setTechUsed1(String techUsed1)
    {
        this.techUsed1 = techUsed1;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }


    public String getTechUsed2() {
        return techUsed2;
    }

    public void setTechUsed2(String techUsed2) {
        this.techUsed2 = techUsed2;
    }

}
