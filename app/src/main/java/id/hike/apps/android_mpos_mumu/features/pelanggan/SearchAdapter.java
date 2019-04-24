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
import id.hike.apps.android_mpos_mumu.R;

/**
 * Created by getwiz on 08/06/17.
 */

public class SearchAdapter extends ArrayAdapter<ModelCustomers> implements Filterable{

    Context context;
    ModelCustomers suggestions;
    List<ModelCustomers> originalModel = new ArrayList<>();
    int resourceLayout;

    public SearchAdapter(Context context, int resource, List<ModelCustomers> objects) {
        super(context, resource, objects);
        this.context = context;
        this.originalModel = objects;
        resourceLayout = resource;

        //init
        List<DataItem> dataItems=new ArrayList<>();
        dataItems.add(new DataItem());
        suggestions=new ModelCustomers(0,0,dataItems,0,null,0,0,null,0);
    }

    @Override
    public int getCount() {
//        if (suggestions != null && suggestions.getResponLogin3Data().size() > 0) {
            return suggestions.getData().size()-1;
//        }
    };

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        this.position=position;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resourceLayout, null);
        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        TextView tvNomorTelpon = (TextView) view.findViewById(R.id.tvNotel);

        if (suggestions != null && !suggestions.getData().isEmpty()) {
            tvName.setText(suggestions.getData().get(position).getName());
            tvNomorTelpon.setText(suggestions.getData().get(position).getPhone());
        }
        return view;
    }

    int position;

    @NonNull
    @Override
    public Filter getFilter() {
        Filter cFilter = new Filter() {

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return ((ModelCustomers)resultValue).getData().get(position).getName();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {


                if (originalModel != null && constraint != null) { // Check if the Original List and Constraint aren't null.
                    //if nextline error, berarti method ini langsung dilewati dan langsung ke publish result
                    if (suggestions.getData().size()>0) {
                        suggestions.getData().clear();
                    }
                    for (int i = 0; i < originalModel.get(0).getData().size(); i++) {
                        if (originalModel.get(0).getData().get(i).getName().contains(constraint)) { // Compare item in original list if it contains constraints.
                            suggestions.getData().add(originalModel.get(0).getData().get(i));
                            /*if (suggestions.getResponLogin3Data().size()==0){
                                suggestions.getResponLogin3Data().add(originalModel.get(0).getResponLogin3Data().get(i));
                                suggestions.get(0).getResponLogin3Data().add(originalModel.get(0).getResponLogin3Data().get(i));
                            } else {
                                suggestions.get(0).getResponLogin3Data().add(originalModel.get(0).getResponLogin3Data().get(i));
                            }*/
                        }
                    }
                }
                FilterResults results = new FilterResults(); // Create new Filter Results and return this to publishResults;
                results.values = suggestions;
                results.count = suggestions.getData().size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
//                notifyDataSetChanged();
            }
        };
        return cFilter;
    }
}