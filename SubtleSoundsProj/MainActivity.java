package com.hobbs.subtlesounds;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity implements
        ListViewFrag.OnClickerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ListViewFrag list = new ListViewFrag();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.container) != null) { // Checks if our fragment container is empty.
            if (savedInstanceState != null) { // Checks if the bundle has anything
                return; // If not, were not gunna do anything.
            }
            list.setArguments(getIntent().getExtras()); // Sets the arguments of that bundle.
            // Now, this changes the view from out original layout, just a blank screen, to out list fragment.
            getSupportFragmentManager().beginTransaction().add(R.id.container, list).commit();
            getSupportFragmentManager().beginTransaction().remove(list).commit();
        }
    }

    public void goBack(View v) {
        Fragment fragment = new ListViewFrag();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        Toolbar tool = (Toolbar) findViewById(R.id.toolbar);
        tool.setTitle("Subtle Sounds of the Earth");
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }

    public void onMagicSelect(int position) { // This is the method that was in the interface that we have to implement here.
        ImageFrag imager = new ImageFrag(); // Make a new image fragment.
        Bundle arg = new Bundle(); // New bundle
        arg.putInt(ImageFrag.TAG, position); // Were putting the list item we choses position into the bundle to pass.
        imager.setArguments(arg); // Setting the arguments of that fragment to the bundles info,
        // so basically passing the position number to that class.
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction(); // this is changing the view from list
        // to the image frag view.
        trans.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_top);
        trans.replace(R.id.container, imager); // this replaces list frag in the container to the image frag.
        trans.addToBackStack(null); // Adds the list frag to the back stack, so if we push back it will pull that frag back up.
        trans.commit(); // Commits those changes.
        Toolbar tool = (Toolbar) findViewById(R.id.toolbar);
        tool.setTitle(Holder.moof[position]);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Toolbar tool = (Toolbar) findViewById(R.id.toolbar);
        tool.setTitle("Subtle Sounds of the Earth");
    }
}
