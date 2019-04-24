package id.hike.apps.android_mpos_mumu.features.login.df;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.base.BaseDialogFragment;
import id.hike.apps.android_mpos_mumu.util.MyUtils;

/**
 * Created by getwiz on 18/05/17.
 */

public class DfLupaPIN extends BaseDialogFragment {

    RecyclerView recyclerView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogStyle);
        View view=getActivity().getLayoutInflater().inflate(R.layout.df_lupa_pin,null);

        final EditText etEmailLupaPin= (EditText) view.findViewById(R.id.etEmailLupaPin);
        Button btnCloseDf= (Button) view.findViewById(R.id.btnCloseDF);
        btnCloseDf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmailLupaPin.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), getResources().getString(R.string.mohon_lengkapi_form), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (MyUtils.isValidEmail(etEmailLupaPin.getText().toString())){
                        lupaPin(etEmailLupaPin.getText().toString());
                    } else {
                        Toast.makeText(getActivity(), R.string.email_tidak_valid, Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                }
            }
        });

        String a="";
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void lupaPin(String email){
//        SvLogin svLogin= ApiClient.serviceGenerator(Cfg.BASEURL_IDENTITY).create(SvLogin.class);
//        Call<ResLupaPin> call=svLogin.lupaPin(new ReqLupaPin(email));
//        call.enqueue(new Callback<ResLupaPin>() {
//            @Override
//            public void onResponse(Call<ResLupaPin> call, Response<ResLupaPin> response) {
//                ResLupaPin resLupaPin =response.body();
//                if (resLupaPin.getData().contains("resetemailverifikasi")){
//                    //berhasil
//                    Toast.makeText(getActivity(), getResources().getString(R.string.pin_telah_direset), Toast.LENGTH_LONG).show();
//                    dismiss();
//                } else {
//                    Toast.makeText(getActivity(), resLupaPin.getData().toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResLupaPin> call, Throwable t) {
////                Toast.makeText(getActivity(), getMethodName()+Cfg.ERR, Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }

}
