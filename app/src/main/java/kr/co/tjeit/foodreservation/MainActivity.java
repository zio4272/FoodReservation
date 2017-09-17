package kr.co.tjeit.foodreservation;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kr.co.tjeit.foodreservation.adapter.SearchAdapter;
import kr.co.tjeit.foodreservation.data.RestaurantData;
import kr.co.tjeit.foodreservation.fragment.HomeFragment;
import kr.co.tjeit.foodreservation.fragment.MyLocationFragment;
import kr.co.tjeit.foodreservation.fragment.SearchFragment;
import kr.co.tjeit.foodreservation.util.GpsInfo;

public class MainActivity extends BaseActivity {







    private android.widget.TextView currentLocationTxt;
    private android.widget.ImageView gpsSettingImg;
    private android.widget.LinearLayout searchLinearLayout;
    private LinearLayout myLocationOn;
    private LinearLayout myLocationOff;
    private android.widget.FrameLayout myLocationFrameLayout;
    private ViewPager myViewPager;
    private FrameLayout mainFrameLayout;
    private LinearLayout homeLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chkGpsService();
        bindViews();
        setupEvents();
        setValues();


    }


    @Override
    public void setupEvents() {

        gpsSettingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        });

//        searchLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, SearchActivity.class);
//                startActivity(intent);
//            }
//        });

//        myLocationFrameLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myLocationOn.setVisibility(View.VISIBLE);
//                myLocationOff.setVisibility(View.GONE);
//
//                Intent intent = new Intent(mContext, MylocationActivity.class);
//                startActivity(intent);
//            }
//        });

        View.OnClickListener pageChange = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pageNum = Integer.parseInt(v.getTag().toString());
                myViewPager.setCurrentItem(pageNum);
            }
        };

        homeLinearLayout.setOnClickListener(pageChange);
        searchLinearLayout.setOnClickListener(pageChange);
        myLocationFrameLayout.setOnClickListener(pageChange);


        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position == 0) {
                    myLocationOn.setVisibility(View.GONE);
                    myLocationOff.setVisibility(View.VISIBLE);
                    mainFrameLayout.setVisibility(View.VISIBLE);
                }
                else if (position == 1) {
                    myLocationOn.setVisibility(View.GONE);
                    myLocationOff.setVisibility(View.VISIBLE);
                    mainFrameLayout.setVisibility(View.GONE);

                } else if (position == 2) {
                    myLocationOn.setVisibility(View.VISIBLE);
                    myLocationOff.setVisibility(View.GONE);
                    mainFrameLayout.setVisibility(View.GONE);

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void setValues() {


        myViewPager.setAdapter(new myPagerAdapter(getSupportFragmentManager()));


        setFinishOnTouchOutside(false);

        GpsInfo gps = new GpsInfo(mContext);
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();

        String address = getAddress(latitude, longitude);


        if (address == null) {
            currentLocationTxt.setText("현재 위치를 알 수 없습니다.");
        } else {
            currentLocationTxt.setText(address);
        }


    }


    public String getAddress(double lat, double lng) {
        String address = null;

        //위치정보를 활용하기 위한 구글 API 객체
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        //주소 목록을 담기 위한 List
        List<Address> list = null;

        try {
            //주소 목록을 가져온다. --> 위도, 경도, 조회 갯수
            list = geocoder.getFromLocation(lat, lng, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (list == null) {
            Log.d("getAddress", "주소 데이터 얻기 실패");
            return null;
        }

        if (list.size() > 0) {
            Address addr = list.get(0);
            address =//addr.getCountryName() + " "      // 나라
//                     addr.getAdminArea() + " "        // 시
//                     addr.getLocality() + " "         // 구
                    addr.getThoroughfare() + " ";   // 동
//                   + addr.getFeatureName();          // 지번
        }
        return address;
    }

    //GPS 설정 체크
    private boolean chkGpsService() {

        GpsInfo gps = new GpsInfo(mContext);
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();

        String address = getAddress(latitude, longitude);

        // GPS OFF 일때 Dialog 표시
        AlertDialog.Builder gsDialog = new AlertDialog.Builder(this);


        // 기존에 GPS가 미리 켜져 있다면 다이얼로그 창에서 확인 없이 바로 진행
        if (address != null) {
            return false;
        }
        gsDialog.setCancelable(false);
        gsDialog.setTitle("위치 확인");

        if (address == null) {
            gsDialog.setMessage("현재위치를 확인할 수 없습니다.\nGPS 설정을 ON 해주세요");

        } else {

            gsDialog.setMessage("사용자의 위치가 " + address + " 이(가) 맞습니까?\n 시작하시려면 시작하기 버튼을 눌러주세요.");
        }


        gsDialog.setNegativeButton("설정", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // GPS설정 화면으로 이동
                Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
                finish();
            }
        });
        gsDialog.setPositiveButton("시작하기", null).show();
//        gsDialog.setPositiveButton("시작", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                Intent intent = new Intent(mContext, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//
//        }).show();


        return false;


    }

    class myPagerAdapter extends FragmentStatePagerAdapter {

        public myPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {

            if (position == 0) {
                return new HomeFragment();

            } else if (position == 1) {
                return new SearchFragment();

            } else if (position == 2) {
                return new MyLocationFragment();
            }
            return new MyLocationFragment();

        }

        @Override
        public int getCount() {
            return 5;
        }
    }

    @Override
    public void bindViews() {
        this.myLocationFrameLayout = (FrameLayout) findViewById(R.id.myLocationFrameLayout);
        this.myLocationOff = (LinearLayout) findViewById(R.id.myLocationOff);
        this.myLocationOn = (LinearLayout) findViewById(R.id.myLocationOn);
        this.searchLinearLayout = (LinearLayout) findViewById(R.id.searchLinearLayout);
        this.homeLinearLayout = (LinearLayout) findViewById(R.id.homeLinearLayout);
        this.myViewPager = (ViewPager) findViewById(R.id.myViewPager);
        this.mainFrameLayout = (FrameLayout) findViewById(R.id.mainFrameLayout);
        this.gpsSettingImg = (ImageView) findViewById(R.id.gpsSettingImg);
        this.currentLocationTxt = (TextView) findViewById(R.id.currentLocationTxt);
    }





}
