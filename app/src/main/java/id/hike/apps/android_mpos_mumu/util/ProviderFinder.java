package id.hike.apps.android_mpos_mumu.util;

import id.hike.apps.android_mpos_mumu.features.home.enums.PPOB_PREFIX;

public class ProviderFinder {


    public static ProviderFinder getInstance(){
        return new ProviderFinder();
    }

    public PPOB_PREFIX getProvider(String number){
        String comparedString = number;
        if(number.length()  > 4){
            comparedString = number.substring(0,4);
        }

        PPOB_PREFIX provider = null;


        switch (comparedString) {
            case "0821":// kartu simpati
            case "0822":
                provider = PPOB_PREFIX.TELKOMSEL;
                break;
            case "0823":/// kartu as
            case "0851":
            case "0852":
            case "0853":
                provider = PPOB_PREFIX.TELKOMSEL;
                break;
            case "0811":// kartu halo
            case "0812":
            case "0813":
                provider = PPOB_PREFIX.TELKOMSEL;
                break;
            case "0814":
            case "0815":// kartu mentari
            case "0816":
            case "0858":
            case "0855":
                provider = PPOB_PREFIX.INDOSAT;

                break;
            case "0856":// kartu im3
            case "0857":
                provider = PPOB_PREFIX.INDOSAT;

                break;
            case "0817":// kartu xl axiata
            case "0818":
            case "0819":
            case "0859":
            case "0877":
            case "0878":
                provider = PPOB_PREFIX.XL;

                break;
            case "0831": // kartu Axis
            case "0832":
            case "0833":
            case "0838":
                provider = PPOB_PREFIX.AXIS;

                break;
            case "0881": // kartu smartfren
            case "0882":
            case "0883":
            case "0884":
            case "0885":
            case "0886":
            case "0887":
            case "0888":
            case "0889":
                provider = PPOB_PREFIX.SMARTFREN;

                break;
            case "0895":// Kartu tri
            case "0896":
            case "0897":
            case "0898":
            case "0899":
                provider = PPOB_PREFIX.THREE;

                break;
        }

        return provider;
    }




}
