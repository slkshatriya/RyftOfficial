package com.technocrats.ryftofficial;

public class Model {
    String techUsed1,techUsed2,title,projectImageUrl,Description;

    public Model() {
    }

    public Model(String techUsed1, String techUsed2, String title,String projectImageUrl,
                 String Description) {
        this.techUsed1 = techUsed1;
        this.techUsed2 = techUsed2;
        this.title = title;
        this.projectImageUrl=projectImageUrl;
        this.Description=Description;
    }

    public String getDescription(){
        if(Description.length() > 10)
            return Description.substring(0,9) + "...";
        else
            return Description;
        }

    public void setDescription(String Description) {
        this.Description = Description;
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

    public String getProjectImageUrl() {
        return projectImageUrl;
    }

    public void setProjectImageUrl(String projectImageUrl) {
        this.projectImageUrl = projectImageUrl;
    }
}
