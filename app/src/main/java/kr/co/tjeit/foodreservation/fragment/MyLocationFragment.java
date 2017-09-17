package kr.co.tjeit.foodreservation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.foodreservation.R;
import kr.co.tjeit.foodreservation.adapter.MyLocationAdapter;
import kr.co.tjeit.foodreservation.data.RestaurantData;

/**
 * Created by ziO on 2017-09-17.
 */

public class MyLocationFragment extends Fragment {

    MyLocationAdapter mAdapter;
    List<RestaurantData> RestaurantDataList = new ArrayList<>();
    private ListView RestaurantListView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mylocation_item, container, false);
        this.RestaurantListView = (ListView) v.findViewById(R.id.RestaurantListView);
        // 뷰 매핑 반드시 하도록.

        mAdapter = new MyLocationAdapter(getContext(), RestaurantDataList);
        RestaurantListView.setAdapter(mAdapter);

        return v;


    }


}
