package com.BooksFragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
        Log.d("onCreate", "savedInstanceState: " + savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_frags);
        getActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.toList:
                DescriptionFragment.resetWebView();
                return true;
            case android.R.id.home:
                startActivityAfterCleanup(MainActivity.class);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startActivityAfterCleanup(Class<?> cls) {
        Intent intent = new Intent(getApplicationContext(), cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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