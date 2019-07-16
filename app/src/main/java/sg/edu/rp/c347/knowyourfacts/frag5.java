package sg.edu.rp.c347.knowyourfacts;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import com.crazyhitty.chdev.ks.rssmanager.RSS;
import com.crazyhitty.chdev.ks.rssmanager.RssReader;

import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class frag5 extends Fragment implements RssReader.RssCallback {
    private RssReader rssReader = new RssReader(this );

    Button btnChangeColor;
    LinearLayout layout;
    WebView wv;

    public frag5() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_5, container, false);

        btnChangeColor = view.findViewById(R.id.btnChangeColour);
        layout = view.findViewById(R.id.layout);
        wv = view.findViewById(R.id.wv);

        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setAllowFileAccess(true);
        wv.getSettings().setBuiltInZoomControls(true);

        String[] url = {"https://www.gov.sg/rss/factuallyrss"};
        loadFeeds(url);

        btnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                layout.setBackgroundColor(color);
            }
        });

        return view;

    }

    private void loadFeeds(String[] urls) {
        rssReader.loadFeeds(urls);
    }

    @Override
    public void rssFeedsLoaded(List<RSS> rssList) {
        // Feeds loaded, do whatever you want to do with them.
        wv.loadData(rssList.toString(),null,null);
    }

    @Override
    public void unableToReadRssFeeds(String errorMessage) {
        // Oops, library was unable to parse your feed url.
    }

}
