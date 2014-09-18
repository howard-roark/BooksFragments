package com.BooksFragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by matthewmcguire on 9/17/14.
 */
public class TitlesFragment extends ListFragment {
    /**Listener to allow user to select specific book to get description on*/
    OnTitlesSelectedListener titleListener;

    /**
     * MainActivity must implement this listener to interact with the fragment containing the list of book titles
     */
    public interface OnTitlesSelectedListener {
        public void onBookSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        int layout = android.R.layout.simple_list_item_1;
        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Info.Titles));
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getFragmentManager().findFragmentById(R.id.desc_frag_id) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    /**
     *
     * @param activity
     */
    @Override
    public void onAttach (Activity activity) {
        super.onAttach(activity);
        try {
            titleListener = (OnTitlesSelectedListener) activity;
        } catch (ClassCastException ce) {
            throw new ClassCastException(activity.toString()
                    + "MainActivity has not implemented the OnTitleSelectedListener interface in TitleFragment class");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        titleListener.onBookSelected(position);
        getListView().setItemChecked(position, true);
    }
}
