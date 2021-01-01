package com.ramadan.indexer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.yokeyword.indexablerv.EntityWrapper;
import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableLayout;
import me.yokeyword.indexablerv.SimpleHeaderAdapter;

public class MainActivity2 extends AppCompatActivity {
    private List<CityEntity> mDatas;
    private  SearchFragment mSearchFragment;
    private androidx.appcompat.widget.SearchView mSearchView;
    private FrameLayout mProgressBar;
    private SimpleHeaderAdapter mHotCityAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super . onCreate (savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar() . setTitle( " Select City " );

        mSearchFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.search_fragment);
        IndexableLayout indexableLayout = (IndexableLayout) findViewById(R.id.indexableLayout);
        mSearchView = (androidx.appcompat.widget.SearchView) findViewById(R.id.searchview);
        mProgressBar = (FrameLayout) findViewById(R.id.progress);


//        indexableLayout.setLayoutManager(new LinearLayoutManager(this));
        indexableLayout . setLayoutManager ( new GridLayoutManager( this , 2 ));

        // Polyphone word processing
       // Pinyin.init(Pinyin.newConfig().with(CnCityDict.getInstance(this)));

        // Add a custom polyphonic dictionary
//         Pinyin.init (Pinyin.newConfig ()
//                .with(new PinyinMapDict() {
//                    @Override
//                    public Map<String, String[]> mapping() {
//                        HashMap<String, String[]> map = new HashMap<String, String[]>();
//                        map.put("重庆",  new String[]{"CHONG", "QING"});
//                        return map;
//                    }
//                }));


        // Quick sort. The sorting rule is set to: only sort by the first letter (default full pinyin sort), the efficiency is very high, about 10 times the default. Open on demand~
        indexableLayout.setCompareMode(IndexableLayout.MODE_FAST);
        // Custom collation
//        indexableLayout.setComparator(new Comparator<EntityWrapper<CityEntity>>() {
//            @Override
//            public int compare(EntityWrapper<CityEntity> lhs, EntityWrapper<CityEntity> rhs) {
//                return lhs.getPinyin().compareTo(rhs.getPinyin());
//            }
//        });

        // setAdapter
        CityAdapter adapter = new CityAdapter(this);
        indexableLayout.setAdapter(adapter);
        // set Dates
        mDatas = initDatas ();

        adapter.setDatas(mDatas, new IndexableAdapter.IndexCallback<CityEntity>() {
            @Override
            public void onFinished(List<EntityWrapper<CityEntity>> datas) {
                // Callback after data processing is completed
                mSearchFragment . bindDatas (mDatas);
                mProgressBar.setVisibility(View.GONE);
            }
        });

        // set Center OverlayView
        indexableLayout.setOverlayStyle_Center();

        // set Listener
        adapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<CityEntity>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, CityEntity entity) {
                if (originalPosition >= 0) {
                    ToastUtil . showShort( getApplicationContext() , " Selected: "  + entity . getName() +  "   Current position: "  + currentPosition +  "   Original array position: "  + originalPosition);
                } else {
                    ToastUtil . showShort(getApplicationContext()  , " Select Header: "  + entity . getName() +  "   Current position: "  + currentPosition);
                }
            }
        });

        adapter.setOnItemTitleClickListener(new IndexableAdapter.OnItemTitleClickListener() {
            @Override
            public void onItemClick(View v, int currentPosition, String indexTitle) {
                ToastUtil . showShort(getApplicationContext() , " selected: "  + indexTitle +  "   current position: "  + currentPosition);
            }
        });

        // Add HeaderView DefaultHeaderAdapter to receive an IndexableAdapter to make the layout and click events consistent with IndexableAdapter
        // If you want to customize the layout, click event, you can pass in new IndexableHeaderAdapter

        mHotCityAdapter =  new  SimpleHeaderAdapter<> (adapter, " Hot " , " Hot City " , iniyHotCityDatas());
        // Hot city
        indexableLayout.addHeaderAdapter(mHotCityAdapter);
        // Locate
        final List<CityEntity> gpsCity = iniyGPSCityDatas();
        final  SimpleHeaderAdapter gpsHeaderAdapter =  new  SimpleHeaderAdapter<> (adapter, "定" , " current city " , gpsCity);
        indexableLayout.addHeaderAdapter(gpsHeaderAdapter);

        // Display the real index
//        indexableLayout.showAllLetter(false);

        // Analog positioning
        indexableLayout . postDelayed ( new  Runnable () {
            @Override
            public  void  run () {
                gpsCity . get( 0 ) . setName( " Hangzhou City " );
                gpsHeaderAdapter.notifyDataSetChanged();
            }
        }, 3000);

        // Search Demo
        initSearch();
    }

    // Update data click event
    public  void  update ( View view ) {
        List<CityEntity> list = new ArrayList<>();
        list . add( new  CityEntity ( " Hangzhou City " ));
        list . add( new  CityEntity ( " Beijing " ));
        list . add( new  CityEntity ( " Shanghai " ));
        list . add( new  CityEntity ( " Guangzhou City " ));
        mHotCityAdapter.addDatas(list);
        Toast. makeText( this , " Update data " , Toast . LENGTH_SHORT ).show();
    }

    private List<CityEntity> initDatas() {
        List<CityEntity> list = new ArrayList<>();
        List<String> cityStrings = Arrays.asList(getResources().getStringArray(R.array.city_array));
        for (String item : cityStrings) {
            CityEntity cityEntity = new CityEntity();
            cityEntity.setName(item);
            list.add(cityEntity);
        }
        return list;
    }

    private List<CityEntity> iniyHotCityDatas() {
        List<CityEntity> list = new ArrayList<>();
        list . add( new  CityEntity ( " Hangzhou City " ));
        list . add( new  CityEntity ( " Beijing " ));
        list . add( new  CityEntity ( " Shanghai " ));
        list . add( new  CityEntity ( " Guangzhou City " ));
        return list;
    }

    private List<CityEntity> iniyGPSCityDatas() {
        List<CityEntity> list = new ArrayList<>();
        list . add( new  CityEntity ( " Locating... " ));
        return list;
    }

    private void initSearch() {
        getSupportFragmentManager().beginTransaction().hide(mSearchFragment).commit();

        mSearchView . setOnQueryTextListener ( new  SearchView. OnQueryTextListener () {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.trim().length() > 0) {
                    if (mSearchFragment.isHidden()) {
                        getSupportFragmentManager().beginTransaction().show(mSearchFragment).commit();
                    }
                } else {
                    if (!mSearchFragment.isHidden()) {
                        getSupportFragmentManager().beginTransaction().hide(mSearchFragment).commit();
                    }
                }

                mSearchFragment . bindQueryText (newText);
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!mSearchFragment.isHidden()) {
            // Hide search
            mSearchView . setQuery ( null , false );
            getSupportFragmentManager().beginTransaction().hide(mSearchFragment).commit();
            return;
        }
        super.onBackPressed();
    }
}