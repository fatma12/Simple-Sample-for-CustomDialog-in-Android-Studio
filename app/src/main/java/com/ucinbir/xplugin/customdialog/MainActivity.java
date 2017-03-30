package com.ucinbir.xplugin.customdialog;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by xplugin on 27.03.2017.
 */
public class MainActivity extends ListActivity implements NameUpdateListener {
    private String[] names;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        names = getResources().getStringArray(R.array.names);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);
        ListView list = getListView();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomDialogFragment dialogFragment = CustomDialogFragment.newInstance(position, names[position]);
                dialogFragment.show(getFragmentManager(), "CustomDialogFragment");
            }
        });
    }
    @Override
    public void onUpdate(int position,String name){
        names[position]=name;
        adapter.notifyDataSetChanged();
    }
}

