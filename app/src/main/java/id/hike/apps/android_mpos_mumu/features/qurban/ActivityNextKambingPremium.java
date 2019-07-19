package id.hike.apps.android_mpos_mumu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ActivityNextKambingPremium extends AppCompatActivity {

    EditText jenisKurban, jumlahKurban, hargaSatuan, jumlahBayar, namaPekurban;
    Button button;
    ImageView kurangJumlahKurban, tambahJumlahKurban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_kambing_premium);

        String pilihanJenisKurban = getIntent().getStringExtra(Cfg.JENIS_KURBAN);
        String harga = String.valueOf(getIntent().getStringExtra(Cfg.HARGA_KAMBING_STANDAR));

        jenisKurban = findViewById(R.id.editText2);
        jenisKurban.setEnabled(false);
        jenisKurban.setText(pilihanJenisKurban);

        jumlahKurban = findViewById(R.id.editText);
        kurangJumlahKurban = findViewById(R.id.imageView18);
        tambahJumlahKurban = findViewById(R.id.imageView19);

        tambahJumlahKurban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String b = String.valueOf((jumlahKurban.getText().toString()));

                    if (b == null || b.isEmpty()) {
                        int a = 0;
                        a = a + 1;
                        jumlahKurban.setText(String.valueOf(a));
                    } else {
                        int a = Integer.parseInt((jumlahKurban.getText().toString()));
                        a = a + 1;
                        //int c = a < 10 ? a : 10;
                        //yang pertama menentukan kondisi, dan yang kedua jika nilai sebelum titik menentukan true & setelah titik nilai false

                        jumlahKurban.setText(String.valueOf(a));
                    }
                } catch (NumberFormatException ex) {
                    // handle your exception
                    ex.fillInStackTrace();
                }
            }
        });

        kurangJumlahKurban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String b = String.valueOf((jumlahKurban.getText().toString()));

                    if (b == null || b.isEmpty()) {
                        int a = 0;
                        a = a - 0;
                        jumlahKurban.setText(String.valueOf(a));
                    } else {
                        int a = Integer.valueOf(jumlahKurban.getText().toString());
                        a = a - 1;
                        int u = a > 0 ? a : 0;
                        //yang pertama menentukan kondisi, dan yang kedua jika nilai sebelum titik menentukan true & setelah titik nilai false

                        jumlahKurban.setText(String.valueOf(u));
                    }
                } catch (NumberFormatException ex) {
                    // handle your exception
                    ex.fillInStackTrace();
                }
            }
        });

        hargaSatuan = findViewById(R.id.editText3);
        hargaSatuan.setEnabled(false);
        hargaSatuan.setText(String.valueOf(harga));

        jumlahBayar = findViewById(R.id.editText4);
        jumlahBayar.setEnabled(false);
        
        namaPekurban = findViewById(R.id.editText5);
    }
}
