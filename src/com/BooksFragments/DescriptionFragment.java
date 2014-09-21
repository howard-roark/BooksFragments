package com.BooksFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by matthewmcguire on 9/17/14.
 */
public class DescriptionFragment extends Fragment {
    final static String ARG_POSITION = "position";
    static int currentPos = -1;
    protected static WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        super.onCreateView(inflater, container, savedInstance);
        if (savedInstance != null) {
            currentPos = savedInstance.getInt(ARG_POSITION);
        }
        return inflater.inflate(R.layout.descrip_frag, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            updateBookView(args.getInt(ARG_POSITION));
        } else if (currentPos != -1) {
            updateBookView(currentPos);
        }
    }

    protected void updateBookView(int position) {
        TextView bookDesc = (TextView) getActivity().findViewById(R.id.desc_frag_id);
        ImageView cover = (ImageView) getActivity().findViewById(R.id.image);
        webView = (WebView) getActivity().findViewById(R.id.webView);

        //Ensure links clicked in webViewer are handled in webViewer not default browser activity
        webView.setWebViewClient(new WebViewClient());

        bookDesc.setText(Info.Descriptions[position]);
        cover.setImageResource(Info.Images[position]);
        webView.loadUrl(Info.Links[position]);
        currentPos = position;
    }

    /**
     * Reset webview from the action bar item
     */
    protected static void resetWebView() {
        webView.loadUrl(Info.Links[currentPos]);
    }

    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, currentPos);
    }
}
