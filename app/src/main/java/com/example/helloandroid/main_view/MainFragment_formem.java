package com.example.helloandroid.main_view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.helloandroid.R;


public class MainFragment_formem extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public MainFragment_formem() {
        // Required empty public constructor
    }


    public static MainFragment_formem newInstance(String param1, String param2) {
        MainFragment_formem fragment = new MainFragment_formem();
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
    }
    //main_fragment_search_button
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_main, container, false);;
        Button searchButton = inflateView.findViewById(R.id.main_fragment_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(inflateView).navigate(R.id.action_mainFragment_to_searchFragment);
            }
        });

        Button Login_Botton = inflateView.findViewById(R.id.login_Botton);
        Login_Botton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Navigation.findNavController(inflateView).navigate(R.id.action_mainFragment_to_loginFragment);
            }
        });

        Button Register_Botton = inflateView.findViewById(R.id.register_Botton);
        Register_Botton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Navigation.findNavController(inflateView).navigate(R.id.action_mainFragment_to_registerFragment);
            }
        });

        return inflateView;
    }
}