package com.BooksFragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by matthewmcguire on 9/17/14.
 */
public class MainActivity extends Activity implements TitlesFragment.OnTitlesSelectedListener {

    /**
     * Create the main view for the app.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_frags);
        if (findViewById(R.id.titles_frag_id) != null) {
            if (savedInstanceState != null) {
                return;
            }
            TitlesFragment titlesFrag = new TitlesFragment();
            getFragmentManager().beginTransaction().add(R.id.titles_frag_id , titlesFrag).commit();
        } else {
            TitlesFragment tFrag = new TitlesFragment();
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            trans.add(R.id.titles_frag_id, tFrag);
            trans.commit();
        }
    }

    /**
     * Handles action when a user selects a certain title from the list of titles.
     *
     * @param position
     */
    @Override
    public void onBookSelected(int position) {
        DescriptionFragment dFrag = (DescriptionFragment) getFragmentManager().findFragmentById(R.id.desc_frag_id);
        if (dFrag != null) {
            dFrag.updateBookView(position);
        } else {
            DescriptionFragment newDFrag = new DescriptionFragment();
            Bundle args = new Bundle();
            args.putInt(DescriptionFragment.ARG_POSITION, position);
            newDFrag.setArguments(args);

            FragmentTransaction trans = getFragmentManager().beginTransaction();
            trans.replace(R.id.titles_frag_id, newDFrag);
            trans.addToBackStack(null);
            trans.commit();
        }
    }
}