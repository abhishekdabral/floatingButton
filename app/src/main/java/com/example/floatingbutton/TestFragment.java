package com.example.floatingbutton;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ABHISHEK on 01/12/2016.
 */
public class TestFragment extends Fragment {

    public int position =0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment, null);
        TextView text = (TextView) view.findViewById(R.id.textView);
        text.setText("Fragment " + position);
        return view;
    }
}
