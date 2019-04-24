package id.hike.apps.android_mpos_mumu.features.home.enums;

/**
 * Created by M Alvin Syahrin on 4/4/2018.
 */

public enum PPOB_PREFIX {
    TELKOMSEL("TELKOMSEL"),
    INDOSAT("INDOSAT"),
    XL("XL"),
    AXIS("AXIS"),
    THREE("Three"),
    BOLT("BOLT"),
    SMARTFREN("SMARTFREN"),
    GOPAY("GOPAY"),
    PLN("PLN");


    private String text;

    PPOB_PREFIX(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

    public static PPOB_PREFIX fromString(String text){
        for(PPOB_PREFIX p : PPOB_PREFIX.values()){
            if(p.text.equals(text)){
                return p;
            }
        }
        return null;
    }
}
