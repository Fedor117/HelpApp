package by.moa.crydev.helpapp.activities.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.moa.crydev.helpapp.R;
import by.moa.crydev.helpapp.activities.DetailActivity;

/**
 * Created by laptop on 13.06.2016.
 */
public class PlaceholderFragment extends Fragment {

    ArrayAdapter<String> mServiceAdapter;

    public PlaceholderFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String[] data = {
                "Государственная регистрация создания, изменения, прекращения существования, "
                    + "а также возникновения, перехода, прекращения прав на недвижимое "
                    + "имущество и сделок с ним",
                "Техническая инвентаризация недвижимости",
                "Техническая инвентаризация сооружений",
                "Землеустроительные и геодезические работы",
                "Независимая оценка имущества"
        };
        List<String> services = new ArrayList<>(Arrays.asList(data));

        mServiceAdapter =
                new ArrayAdapter<>(
                        getActivity(),
                        R.layout.list_item_service,
                        R.id.list_item_service_textview,
                        services);

        View rootview = inflater.inflate(R.layout.fragment_placeholder, container, false);

        ListView listView = (ListView) rootview.findViewById(R.id.listview_services);
        listView.setAdapter(mServiceAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String service = mServiceAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, service);
                startActivity(intent);
            }
        });

        return rootview;
    }

}
