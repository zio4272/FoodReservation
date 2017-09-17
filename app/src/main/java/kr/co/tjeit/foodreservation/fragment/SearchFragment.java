package kr.co.tjeit.foodreservation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import kr.co.tjeit.foodreservation.R;


/**
 * Created by ziO on 2017-09-17.
 */

public class SearchFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_item, container, false);
        // 뷰 매핑 반드시 하도록.


        return v;
    }
}
