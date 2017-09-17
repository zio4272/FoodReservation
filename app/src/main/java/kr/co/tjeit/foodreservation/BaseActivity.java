package kr.co.tjeit.foodreservation;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ziO on 2017-09-17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    Context mContext = this;

    public abstract void setupEvents();

    public abstract void setValues();

    public abstract void bindViews();


}
