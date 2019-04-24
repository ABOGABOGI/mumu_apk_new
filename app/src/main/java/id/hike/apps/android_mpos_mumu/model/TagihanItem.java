package id.hike.apps.android_mpos_mumu.model;

public class TagihanItem {

    private String titleLine;
    private String descriptionLine;

    public TagihanItem(String title, String descriptionLine){
        this.titleLine = title;
        this.descriptionLine = descriptionLine;
    }

    public String getDescriptionLine() {
        return descriptionLine;
    }

    public void setDescriptionLine(String descriptionLine) {
        this.descriptionLine = descriptionLine;
    }

    public String getTitleLine() {
        return titleLine;
    }

    public void setTitleLine(String titleLine) {
        this.titleLine = titleLine;
    }
}
