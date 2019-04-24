package id.hike.apps.android_mpos_mumu.features.pin_verifikasi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.SocketTimeoutException;

import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseActivity;
import id.hike.apps.android_mpos_mumu.features.donasi.DonasiService;
import id.hike.apps.android_mpos_mumu.features.donasi.model.Donation;
import id.hike.apps.android_mpos_mumu.features.donasi.model.MsgResponse;
import id.hike.apps.android_mpos_mumu.features.donasi.model.Transaction;
import id.hike.apps.android_mpos_mumu.features.landing_page.LandingPage;
import id.hike.apps.android_mpos_mumu.util.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PinActivity extends BaseActivity implements View.OnClickListener {
    private final String LOG = "BAKKA";
    private final Context context = this;
    private EditText txtPin;
    private Button btnSubmitPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        btnSubmitPin = findViewById(R.id.btnSubmitPin);
        txtPin = findViewById(R.id.txtPin);
        btnSubmitPin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dfLoading.show(getSupportFragmentManager(), "");
        dfLoading.setCancelable(false);
        DonasiService service = ApiClient.serviceGenerator(Cfg.BASEURL_DONASI)
                .create(DonasiService.class);

        try {
            Log.d(LOG, getDonation().toString());
            Log.d(LOG, "Bearer " + getSecPref().getString(Cfg.oauthAccessToken, null));
            Call<MsgResponse<Transaction>> call = service.denomfromwallet("Bearer " + getSecPref().getString(Cfg.oauthAccessToken, ""), getDonation());
            call.enqueue(new Callback<MsgResponse<Transaction>>() {
                @Override
                public void onResponse(Call<MsgResponse<Transaction>> call, Response<MsgResponse<Transaction>> response) {
                    Log.d(LOG, "response body: " + response.body().toString());
                    if (response.body().getData() != null) {
                        goToLandingPage(getString(R.string.donasi_berhasil));
                    } else {
                        onDonationFailed(getString(R.string.ppob_wrong_pin));
                    }
                }

                @Override
                public void onFailure(Call<MsgResponse<Transaction>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        Log.w(LOG, "taking too long time waiting for server response");
                        goToLandingPage(getString(R.string.donasi_berhasil));
                    } else {
                        Log.d(LOG, "request failed: " + t.getMessage(), t);
                        onDonationFailed(getString(R.string.donasi_error));
                    }
                }
            });
        } catch (Exception ex) {
            Log.e(LOG, "unknown exception: " + ex.getMessage(), ex);
        }
    }

    private Donation getDonation() {
        Donation donation = new Donation();
        donation.setPin(txtPin.getText().toString());
        donation.setPhone(getSecPref().getString(Cfg.prefDonasiPhone, null));
        donation.setMuzaki(getSecPref().getString(Cfg.prefDonasiMuzakki, null));
        donation.setDonationProgram(getSecPref().getString(Cfg.prefDonasiProgram, null));
        donation.setAmount(getSecPref().getInt(Cfg.prefDonasiAmount, 0) + 2500);
        donation.setAccountDest(getString(R.string.rek_dompet_dhuafa));
        donation.setDonationType(getSecPref().getString(Cfg.prefDonasiType, null));
        return donation;
    }

    private void goToLandingPage(String message) {
        dfLoading.dismiss();
        Intent donePay = new Intent(PinActivity.this, LandingPage.class);
        if (message != null) {
            donePay.putExtra("message", message);
        }
        donePay.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(donePay);
        finish();
    }

    private void onDonationFailed(String message) {
        dfLoading.dismiss();
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
