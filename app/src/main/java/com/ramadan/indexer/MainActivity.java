package com.ramadan.indexer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements IndexAdapter.SelectListner {

    private HashMap<String, Integer> mapIndex;
    private List<Data> dataList;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataList = new ArrayList<>();
        mapIndex = new LinkedHashMap<String, Integer>();

        getData();

        RecyclerView rvList = (RecyclerView) findViewById(R.id.rvList);
        RecyclerView rvIndex = (RecyclerView) findViewById(R.id.rvIndex);

        layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvList.getContext(), layoutManager.getOrientation());

        DataAdapter dataAdapter = new DataAdapter(dataList);
        IndexAdapter indexAdapter = new IndexAdapter(new ArrayList<String>(mapIndex.keySet()), this);
        rvList.setLayoutManager(layoutManager);
        rvList.addItemDecoration(dividerItemDecoration);
        rvList.setAdapter(dataAdapter);

        rvIndex.setLayoutManager(new LinearLayoutManager(this));
        rvIndex.setAdapter(indexAdapter);

    }

    private void getData() {
// Add your data here
       dataList.add(new Data("AAShaktisinh Jadeja", "27"));
       dataList.add(new Data("ABShaktisinh Jadeja", "27"));
       dataList.add(new Data("ACShaktisinh Jadeja", "27"));
       dataList.add(new Data("BAShaktisinh Jadeja", "27"));
       dataList.add(new Data("BBShaktisinh Jadeja", "27"));
       dataList.add(new Data("CShaktisinh Jadeja", "27"));
       dataList.add(new Data("CShaktisinh Jadeja", "27"));
       dataList.add(new Data("DShaktisinh Jadeja", "27"));
       dataList.add(new Data("DShaktisinh Jadeja", "27"));
       dataList.add(new Data("EShaktisinh Jadeja", "27"));
       dataList.add(new Data("EShaktisinh Jadeja", "27"));
       dataList.add(new Data("FShaktisinh Jadeja", "27"));
       dataList.add(new Data("FShaktisinh Jadeja", "27"));
       dataList.add(new Data("GShaktisinh Jadeja", "27"));
       dataList.add(new Data("GShaktisinh Jadeja", "27"));
       dataList.add(new Data("HIIShaktisinh Jadeja", "27"));
       dataList.add(new Data("IShaktisinh Jadeja", "27"));
       dataList.add(new Data("IShaktisinh Jadeja", "27"));
       dataList.add(new Data("JShaktisinh Jadeja", "27"));
       dataList.add(new Data("KShaktisinh Jadeja", "27"));
       dataList.add(new Data("LShaktisinh Jadeja", "27"));
       dataList.add(new Data("MShaktisinh Jadeja", "27"));
       dataList.add(new Data("NShaktisinh Jadeja", "27"));
       dataList.add(new Data("OShaktisinh Jadeja", "27"));
       dataList.add(new Data("PShaktisinh Jadeja", "27"));
       dataList.add(new Data("PShaktisinh Jadeja", "27"));
       dataList.add(new Data("PShaktisinh Jadeja", "27"));
       dataList.add(new Data("QShaktisinh Jadeja", "27"));
       dataList.add(new Data("RShaktisinh Jadeja", "27"));
       dataList.add(new Data("RShaktisinh Jadeja", "27"));
       dataList.add(new Data("RShaktisinh Jadeja", "27"));
       dataList.add(new Data("SShaktisinh Jadeja", "27"));
       dataList.add(new Data("TShaktisinh Jadeja", "27"));
       dataList.add(new Data("TShaktisinh Jadeja", "27"));
       dataList.add(new Data("TShaktisinh Jadeja", "27"));
       dataList.add(new Data("TShaktisinh Jadeja", "27"));
       dataList.add(new Data("VShaktisinh Jadeja", "27"));
       dataList.add(new Data("WShaktisinh Jadeja", "27"));
       dataList.add(new Data("WShaktisinh Jadeja", "27"));
       dataList.add(new Data("XShaktisinh Jadeja", "27"));
       dataList.add(new Data("YShaktisinh Jadeja", "27"));
       dataList.add(new Data("ZShaktisinh Jadeja", "27"));

        Set<String> temp = new HashSet<>();
        for (int i = 0; i < dataList.size(); i++) {
            String obj1 = Character.toString(dataList.get(i).getName().charAt(0)).toUpperCase();
            String obj2;
            try {
                obj2 = Character.toString(dataList.get(i + 1).getName().charAt(0)).toUpperCase();
            } catch (IndexOutOfBoundsException e) {
                obj2 = "#";
            }

            if (!obj1.equalsIgnoreCase(obj2)) {
                temp.add(obj1);
            }
        }
        for (String title : temp) {
            Data member = new Data();
            member.setIndex(title);
            member.setName(title);
            dataList.add(member);
        }

        Collections.sort(dataList, new Comparator<Data>() {
            public int compare(Data obj1, Data obj2) {
                return obj1.getName().compareToIgnoreCase(obj2.getName());
            }
        });

        getIndexList(dataList);
    }

    private void getIndexList(List<Data> memberList) {
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getIndex() != null) {
                String index = memberList.get(i).getIndex();
                if (mapIndex.get(index) == null)
                    mapIndex.put(index, i);
            }
        }
    }

    @Override
    public void selectedIndex(View view) {
        TextView selectedIndex = (TextView) view;
        layoutManager.scrollToPositionWithOffset(mapIndex.get(selectedIndex.getText()), 0);
    }
}
