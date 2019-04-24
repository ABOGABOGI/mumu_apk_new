package id.hike.apps.android_mpos_mumu.features.home.enums;

/**
 * Created by M Alvin Syahrin on 4/5/2018.
 */

public enum PPOB_STATE {
    ON_PENDING("ON_PENDING"),
    ON_PROCESS("ON_PROCESS"),
    ON_TRANSACTION("ON_TRANSACTION"),
    ON_WAITING("ON_WAITING"),
    IS_SUCCESS("IS_SUCCESS"),
    IS_FAILED("IS_FAILED"),
    IS_CANCELED("IS_CANCELED");

    private String text;

    PPOB_STATE(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

    public static PPOB_STATE fromString(String text){
        for(PPOB_STATE p : PPOB_STATE.values()){
            if(p.text.equals(text)){
                return p;
            }
        }
        return null;
    }
}
