package id.hike.apps.android_mpos_mumu.features.wakaf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import butterknife.BindView;
import id.hike.apps.android_mpos_mumu.R;

public class FragmentSinopsis extends Fragment {

    @BindView(R.id.description)
    TextView description;

    public FragmentSinopsis() {
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
        return inflater.inflate(R.layout.activity_fragment_sinopsis, container, false);
    }

}
