package com.task.vasskob.customtab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String ARG_CURRENT_POSITION = "ARG_CURRENT_POSITION";
    private static final String TAG = MainActivity.class.getSimpleName();

    int HOME_PAGE_POSITION             = 1;
    int USER_ACTIVITY_PAGE_POSITION    = 2;
    int CAMERA_PAGE_POSITION           = 3;
    int SEARCH_PAGE_POSITION           = 4;
    int PROFILE_PAGE_POSITION          = 5;

    @Bind({ R.id.home, R.id.notification, R.id.camera, R.id.search, R.id.profile })
    List<ImageButton> mTabs;
    static final ButterKnife.Setter<View, Boolean> SELECT = new ButterKnife.Setter<View, Boolean>() {
        @Override public void set(View view, Boolean value, int index) {
            view.setSelected(value);
        }
    };

    private int mCurrentPosition = CAMERA_PAGE_POSITION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        lockScreenOrientation();
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate: currentPos: " + mCurrentPosition);
        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate: restore state " + savedInstanceState.getInt(ARG_CURRENT_POSITION) + " bundle: " + savedInstanceState);
            mCurrentPosition = savedInstanceState.getInt(ARG_CURRENT_POSITION, mCurrentPosition);
        } else {
            Log.d(TAG, "onCreate: savedInstanceState == null");
        }
        selectTab(mCurrentPosition);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: pos: " + mCurrentPosition);
        Log.d(TAG, "onSaveInstanceState: outState before : " + outState);
        outState.putInt(ARG_CURRENT_POSITION, mCurrentPosition);
        Log.d(TAG, "onSaveInstanceState: outState save: " + outState);
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: outState super: " + outState);
    }


    private void selectTab(int position) {
        ButterKnife.apply(mTabs, SELECT, false);
        mTabs.get(position - 1).setSelected(true);
    }

    @OnClick({ R.id.home, R.id.notification, R.id.camera, R.id.search, R.id.profile })
    public void onTabClick(View view) {
        Log.d(TAG, "onTabClick: id: " + view.getId());
        selectTab(getTabPosition(view.getId()));
    }

    private int getTabPosition(int tabID) {
        switch (tabID) {
            case R.id.home:
                return HOME_PAGE_POSITION;
            case R.id.notification:
                return USER_ACTIVITY_PAGE_POSITION;
            case R.id.camera:
                return CAMERA_PAGE_POSITION;
            case R.id.search:
                return SEARCH_PAGE_POSITION;
            case R.id.profile:
                return PROFILE_PAGE_POSITION;
            default:
                return HOME_PAGE_POSITION;
        }
    }

    public void lockScreenOrientation() {
        switch (((WindowManager) getSystemService(WINDOW_SERVICE))
                .getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_90:
                setContentView(R.layout.activity_main);
                break;
            case Surface.ROTATION_180:
                setContentView(R.layout.activity_main);
                break;
            case Surface.ROTATION_270:
                setContentView(R.layout.activity_main_reverse);
                break;
            default :
                setContentView(R.layout.activity_main);
        }
    }
}
