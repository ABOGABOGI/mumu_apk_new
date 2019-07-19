package id.hike.apps.android_mpos_mumu.features.qurban.fragment_qurban;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import id.hike.apps.android_mpos_mumu.base.BaseFragment;
import id.hike.apps.android_mpos_mumu.features.qurban.ActivityNextKambingPremium;
import id.hike.apps.android_mpos_mumu.Cfg;
import id.hike.apps.android_mpos_mumu.R;
import id.hike.apps.android_mpos_mumu.features.qurban.ActivityNextQurban;

public class FragmentQurbanKambingPremium extends BaseFragment {

    public FragmentQurbanKambingPremium() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qurban_kambing_premium, container, false);

        int hargaKambingPremium = 2975000;

        TextView title = view.findViewById(R.id.titleKambingPremium);

        Button button = view.findViewById(R.id.nextKurban);
        button.setOnClickListener(v -> {

            boolean a = true;

            if(isNetworkConnected() == false){
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("No Internet Connection");
                builder.setMessage("Silahkan aktifkan koneksi Internet Anda. \nklik OK untuk keluar");
                builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss()).show();
            }
            else{

                SharedPreferences.Editor secPref = getFragSecPrefs().edit();
                secPref.putString(Cfg.JENIS_KURBAN, title.getText().toString());
                secPref.putString(Cfg.HARGA_KAMBING, String.valueOf(hargaKambingPremium));
                secPref.apply();

                Intent intent = new Intent(getContext(), ActivityNextKambingPremium.class);
                getContext().startActivity(intent);
            }

            Log.d("wakacaw","is connected "+isNetworkConnected());



        });
        return view;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
//        Log.d("wakacaw", cm.getActiveNetworkInfo().toString());
        return cm.getActiveNetworkInfo() != null;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
