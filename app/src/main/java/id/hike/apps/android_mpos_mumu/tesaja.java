package id.hike.apps.android_mpos_mumu;

import android.content.Context;
import android.widget.Toast;

public class tesaja {

    Context context;
    int nilaiTotal = 0;
    int kehadiran;
    double tugas, uts, uas;

    public void nilai (int kehadiran, double tugas, double uts, double uas){

        this.kehadiran = kehadiran;
        this.tugas = tugas;
        this.uts = uts;
        this.uas = uas;

    }

    public void nilaiMahasiswa(){

        int i = 70 < 80 ? 70 : 80;
        int u = 60 < 70 ? 60 : 70;
        int o = 50 < 60 ? 50 : 60;

        if (nilaiTotal > 80){
            System.out.println("A");
        }else if(nilaiTotal == i){
            System.out.println("B");
        }else if(nilaiTotal == u){
            System.out.println("C");
        }else if(nilaiTotal == o){
            System.out.println("D");
        }else if(nilaiTotal < 50){
            System.out.println("E");
        }
    }

    public double getNilaiTotal(){
        return getNilaiTotal();
    }

    public double getNilaiHuruf(){
        return getNilaiHuruf();
    }
}
