package com.BooksFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by matthewmcguire on 9/17/14.
 */
public class DescriptionFragment extends Fragment {
    final static String ARG_POSITION = "position";
    int currentPos = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        super.onCreateView(inflater, container, savedInstance);
        if (savedInstance != null) {
            currentPos = savedInstance.getInt(ARG_POSITION);
        }
        return inflater.inflate(R.layout.books_frags, container, false);
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
        bookDesc.setText(Info.Descriptions[position]);
        currentPos = position;
    }

    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, currentPos);
    }
}
