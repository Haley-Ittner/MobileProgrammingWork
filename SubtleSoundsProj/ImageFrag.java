package com.hobbs.subtlesounds;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



public class ImageFrag extends Fragment {
    int currentImage = -1; // This is going to count where we are in the array as far as images and sounds.
    static final String TAG = "This one"; // This is just the key to find that value in a bundle being passed from the main class.
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) { // Checking to see is we were paused and to resume on a certain image.
            currentImage = savedInstanceState.getInt(TAG); // Getting that position from the bundle with out key.
        }
        return inflater.inflate(R.layout.fragment_main, container, false); //  This puts that design(image_view) on the screen.
    }

    @Override
    public void onStart() { // This is like onCreate for fragments. When the view is put for a fragment this gets called.
        super.onStart(); // Always gotta call this...
        Bundle args = getArguments(); // This gets the arguments from a prevois bundle(?) and puts it into a new bundle(?)
        if (args != null) { // checks to see if there were previous arguments in the bundle
            getImage(args.getInt(TAG)); // If so, just call out method with the position from the bundle
        } else if (currentImage != -1) { // making sure that a item in the list was selected.
            getImage(currentImage); // then we just call our method to put the right image on the screen.
        }
    }


    public void getImage(int position) { // This is a method I created to put the image on the screen
        ImageView image = (ImageView) getActivity().findViewById(R.id.imageHolder);// Finds the image view we need
        Drawable background = getResources().getDrawable(Holder.picture[position]); /* This was tricky...
        // So I looked this up and found that since I can't set a background to the id number, I needed to find
           a way to convert the number to the actual object, which is what getResources.getDrawable() does. */
        image.setBackground(background); // Then, we just set the image we just found as the background of the image view
        MediaPlayer media = MediaPlayer.create(getContext(), Holder.sound[position]); /* This was also tricky...
        I found the MediaPlayer idea on stackoverflow and tried exactly what they typed but it wouldn't work
        (it was MediaPlayer.create(this, Holder.sound[position]) and the "this" part was not working).
         So, I found out that the "this" part was for context, so I looked up how to get the context for an
         activity, which was just getContext().... who knew? */
        media.start(); // This starts media player, to play the sound... obviously.
        currentImage = position; // Then we set the position to the one that was selected.
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);// Save everything you want to save. Pass in bundle that will be given back on onStart

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(TAG, currentImage);
    }
}
