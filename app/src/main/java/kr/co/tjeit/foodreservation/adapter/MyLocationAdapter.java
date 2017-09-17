package kr.co.tjeit.foodreservation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import kr.co.tjeit.foodreservation.R;
import kr.co.tjeit.foodreservation.data.RestaurantData;

/**
 * Created by ziO on 2017-09-17.
 */

public class MyLocationAdapter extends ArrayAdapter<RestaurantData> {

    Context mContext;
    List<RestaurantData> mList;
    LayoutInflater inf;


    public MyLocationAdapter(Context context, List<RestaurantData> list) {
        super(context, R.layout.mylocation_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.mylocation_list_item, null);
        }
        return row;
    }

    @Override
    public int getCount() {
        return 10;
    }
}
