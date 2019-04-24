package id.hike.apps.android_mpos_mumu.features.home.enums;

/**
 * Created by M Alvin Syahrin on 4/3/2018.
 */

public enum PPOB_TYPE {

    PLS("PULSA"),
    PLN("PLN"),
    TVK("TV"),
    MP3("MP3");

    private String text;

    PPOB_TYPE(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

    public static PPOB_TYPE fromString(String text){
        for(PPOB_TYPE p : PPOB_TYPE.values()){
            if(p.text.equals(text)){
                return p;
            }
        }
        return null;
    }
}
