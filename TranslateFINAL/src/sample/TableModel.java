package sample;

public class TableModel {
    public String english, ojibwe, mohawk;

    TableModel(String english, String ojibwe, String mohawk) {
        this.english = english;
        this.ojibwe = ojibwe;
        this.mohawk = mohawk;
    }

//must be public to show english word in table
   public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getOjibwe() {
        return ojibwe;
    }

    public void setOjibwe(String ojibwe) {
        this.ojibwe = ojibwe;
    }

    public String getMohawk() {
        return mohawk;
    }

    public void setMohawk(String mohawk) {
        this.mohawk = mohawk;
    }
}


