package com.superdroid.mybaseapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.superdroid.mybaseapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * ����fragmentҳ�湲������״̬�������С����س�������Ϊ�ա����سɹ�
 */
public class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }


}
