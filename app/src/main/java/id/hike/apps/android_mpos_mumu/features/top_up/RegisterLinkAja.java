package id.hike.apps.android_mpos_mumu.features.top_up;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.NextRegistrasiLinkAja;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;

public class RegisterLinkAja extends BaseActivity {

    EditText kodeNegara, nomorHandphne;
    Button btnLanjut, btnRegisLinkAjaPlayStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_link_aja);

        kodeNegara = findViewById(R.id.kodeNegara);
        kodeNegara.setEnabled(false);

        nomorHandphne = findViewById(R.id.nomorHanphone);
        btnLanjut = findViewById(R.id.btnLanjutRegisLinkAja);
        btnRegisLinkAjaPlayStore = findViewById(R.id.btnRegisToPlystoreLinkAja);

        btnRegisLinkAjaPlayStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.telkom.mwallet&hl=in" + appPackageName)));
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "https://play.google.com/store/apps/details?id=com.telkom.mwallet&hl=in" + appPackageName));
                startActivity(intent);
            }
        });

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor secPref = getSecPref().edit();

                secPref.putString(Cfg.NOMOR_HANDPHONE, String.valueOf(kodeNegara.getText().toString() + nomorHandphne.getText().toString()));
                secPref.apply();
                Toast.makeText(RegisterLinkAja.this, getSecPref().getString(Cfg.NOMOR_HANDPHONE, Cfg.NOMOR_HANDPHONE), Toast.LENGTH_SHORT).show();

                final Dialog dialog = new Dialog(RegisterLinkAja.this);
                dialog.setContentView(R.layout.popup_lanjut_regis_linkaja);

                /**
                 * Mengeset komponen dari custom dialog
                 */
                TextView text = (TextView) dialog.findViewById(R.id.noticeRegisLinkAja);

                Button dialogButton = (Button) dialog.findViewById(R.id.setujuRegis);
                Button dialogButtonDua = (Button) dialog.findViewById(R.id.menolakRegis);
                /**
                 * Jika tombol diklik, tutup dialog
                 */
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RegisterLinkAja.this, NextRegistrasiLinkAja.class);
                        startActivity(intent);
                    }
                });

                dialogButtonDua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
}
