package com.zhao.elevator.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.zhao.elevator.R;
import com.zhao.elevator.utils.Data;

/**
 * Created by zhao on 2016/12/8.
 */

public class WebFragment extends Fragment {
    private Data data;
    private String iPaddress;
    private String port;
    public static String URL;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        data= (Data) getActivity().getApplication();
        iPaddress=data.getIpAddress();
        port=Data.PORT;
        URL= "http://" + iPaddress + ":" + port +
                "/elevatorService/introduction.html";

        View view=inflater.inflate(R.layout.fragment_web, container, false);
        WebView webView= (WebView) view.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.loadUrl("http://"+iPaddress+":8080/elevatorService/introduction.html");

        return view;
    }
}
