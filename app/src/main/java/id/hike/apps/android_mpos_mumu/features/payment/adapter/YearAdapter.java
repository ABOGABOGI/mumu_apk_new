package id.hike.apps.android_mpos_mumu.features.payment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import id.hike.apps.android_mpos_mumu.R;

/**
 * Created by getwiz on 08/06/17.
 */

public class YearAdapter extends ArrayAdapter<String> {

    List<String> years=new ArrayList<>();
    Context context;
    public YearAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context=context;
        years=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.i_spinner_payment, null);
        TextView tvName = (TextView) view.findViewById(R.id.tvTahun);

        tvName.setText(years.get(position));

        return view;
    }

    @Override
    public int getCount() {
        return years.size()-1; //untuk hiding hint dari headspinner
    }
}
