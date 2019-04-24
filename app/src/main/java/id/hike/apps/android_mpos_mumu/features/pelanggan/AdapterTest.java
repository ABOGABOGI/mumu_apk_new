package id.hike.apps.android_mpos_mumu.features.pelanggan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import id.hike.apps.android_mpos_mumu.R;

/**
 * Created by getwiz on 14/06/17.
 */

public class AdapterTest extends ArrayAdapter<ModelTest> implements Filterable{

    List<ModelTest> originalModel;
    List<ModelTest> suggestions;
    Context context;
    int resourceInt;
    public AdapterTest(Context context, int resource, List<ModelTest> objects) {
        super(context, resource, objects);
        originalModel =objects;
        this.context=context;
        suggestions=new ArrayList<>();
        resourceInt=resource;
    }

    @Override
    public int getCount() {
        return suggestions.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.i_search_layout, null);

        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        TextView tvNomorTelpon = (TextView) view.findViewById(R.id.tvNotel);

        if (suggestions!=null && !suggestions.isEmpty()){
            tvName.setText(suggestions.get(position).getName());
            tvNomorTelpon.setText(suggestions.get(position).getNotel());
        }

        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return ((ModelTest)resultValue).getName();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (originalModel != null && constraint != null) { // Check if the Original List and Constraint aren't null.
                    suggestions.clear(); //ini harus didalam if, kalo diluar if ini, suggestion ke-clear sendiri
                    for (int i = 0; i < originalModel.size(); i++) {
                        if (originalModel.get(i).getName().contains(constraint)) { // Compare item in original list if it contains constraints.
                            suggestions.add(originalModel.get(i)); // If TRUE add item in Suggestions.
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = suggestions;
                results.count = suggestions.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                notifyDataSetChanged();
            }
        };
    }

    @Nullable
    @Override
    public ModelTest getItem(int position) {
        return super.getItem(position);
    }
}
