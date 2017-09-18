package com.hobbs.subtlesounds;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListViewFrag extends ListFragment {

    OnClickerListener hearingEar; // The onClickListener of this class. Will notify the main activity if something from the list was selected.
    public interface OnClickerListener { // We implement this in our main activity to connect it to this class.
        public void onMagicSelect(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This basically says if we have an old version of android, use one list layout, if we have one above honeycomb then use a different one.
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
        // This is a helper to make this list fragment get the data from out holder class to display.
        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Holder.moof));
    }

    @Override
    public void onStart() { //Again, onCreate() for fragments
        super.onStart();
        if (getFragmentManager().findFragmentById(R.id.container) != null) { /* This was originally
            really long, for option of two pane mode or single pane, but now the only option is single pane. */
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    // I originally commentted out this method, cuz it was deprecated, which was a mistake....
    public void onAttach(Activity activity) { // This is called when the fragment is attached to the main acitivity
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            hearingEar = (OnClickerListener) activity; // Means were attached to a activity so we can send method calls to it.
            // mCallBack.methodName() means that the person is interacting with the headlines and to do whats needs to be done.
        } catch (ClassCastException e) { // checks to see if attachment was made. Calls when attachment is made.
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) { // Gets called when a list item is chosen.
        // Notifies the main activity that a list item was chosen.
        hearingEar.onMagicSelect(position); // Notify activity that a click was made at that position.

        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(position, true); // highlights the list item that was clicked.
    }
}
