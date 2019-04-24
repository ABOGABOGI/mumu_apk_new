package id.hike.apps.android_mpos_mumu.features.receipt_invoice;

/**
 * Created by getwiz on 22/05/17.
 */

public class ModelListBelanja {
    String namaProduk;
    int jumlah;
    String harga;

    public ModelListBelanja(String namaProduk, int jumlah, String harga) {
        this.namaProduk = namaProduk;
        this.jumlah = jumlah;
        this.harga = harga;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
