package com.example.lwl.retrofit2.fragment;


import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lwl.retrofit2.R;

import java.util.ArrayList;

public class Test1Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mRv;
    private MyAdapter mMyAdapter;
    private ArrayList<DataResponse> list;

    public Test1Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Test1Fragment newInstance(String param1, String param2) {
        Test1Fragment fragment = new Test1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        list=new ArrayList<>();
        for (int i=0;i<20;i++) {
            DataResponse dataResponse = new DataResponse();
            dataResponse.setName(mParam1+i);
            dataResponse.setBir("201609"+i);
            list.add(dataResponse);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_test1, container, false);
        mRv= (RecyclerView) view.findViewById(R.id.rv_data);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMyAdapter=new MyAdapter(list);
        mRv.setAdapter(mMyAdapter);
        mMyAdapter.notifyDataSetChanged();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
