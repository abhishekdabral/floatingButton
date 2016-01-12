package com.example.floatingbutton;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    public static final int HOME_TAB_1 = 0;
    public static final int HOME_TAB_2 = 1;
    public static final int HOME_TAB_3 = 2;
    private FloatingButton menuButton;
    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPager = (ViewPager) findViewById(R.id.pager);
        menuButton = (FloatingButton) findViewById(R.id.floating);
        HomeTabAdapter adapter = new HomeTabAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);
        mPager.addOnPageChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
       setFloatingImage(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    /**
     * Caaled to set image on floating button on view pager page change action
     * @param position : poition of current tab selected
     */
    public void setFloatingImage(int position) {

        switch (position) {
            case HOME_TAB_1:
                menuButton.setImage(R.drawable.menu);
                break;
            case HOME_TAB_2:
                menuButton.setImage(R.drawable.add);
                break;
            case HOME_TAB_3:
                menuButton.setImage(R.drawable.search);
                break;
        }
    }

}
