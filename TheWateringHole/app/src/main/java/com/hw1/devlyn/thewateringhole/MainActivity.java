package com.hw1.devlyn.thewateringhole;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import info.info.wateringhole.slidingmenu.adapter.NavDrawerListAdapter;
import info.info.wateringhole.slidingmenu.model.NavDrawerItem;

import static com.hw1.devlyn.thewateringhole.R.*;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    /*Fields for the buttons to be used in this class.*/
    Button Events;
    Button Friends;
    Button Profile;
    Button Settings;

    /*These fields are used for the navigation slide out menu.*/
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    /*Navigation drawer title*/
    private CharSequence mDrawerTitle;

    /*Stores app title for menu*/
    private CharSequence mTitle;

    /*Slide menu items*/
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        Events = (Button) this.findViewById(id.events_btn);
        Friends = (Button) this.findViewById(id.friends_btn);
        Profile = (Button) this.findViewById(id.profile_btn);
        Settings = (Button) this.findViewById(id.settings_btn);

        Events.setOnClickListener(this);
        Friends.setOnClickListener(this);
        Profile.setOnClickListener(this);
        Settings.setOnClickListener(this);


        mTitle = mDrawerTitle = getTitle();

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slider_menu);

        Log.d("Main Page","mDrawerList created"+ mDrawerList);
        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Photos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // Pages
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // What's hot, We  will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));


        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);

        mDrawerList.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        /* This enables the action bar app icon and allows it to behave as a toggle button on the
            screen.*/

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, //nav menu toggle icon
                string.app_name, // nav drawer open - description for accessibility
                string.app_name) // nav drawer close - description for accessibility

        {
            /*These methods are used for when the menu slides out or in and what to display on the
                screen.*/
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);


        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }
    }

    /*This method is for the on screen clicks by the user depending on which button is pushed
        in this case the Events, Friends, Profile or Settings buttons. Once one button has been
        clicked depedning on their relationship the screen will switch to the appropriate screen.*/
    public void onClick(View v) {
        if (v == Events) {
            Intent events = new Intent(this, EventsActivity.class);

            Button b = (Button) v;
            this.startActivity(events);
        }
        else if (v == Friends) {
            Intent friends = new Intent(this, Friends.class);

            Button b = (Button) v;
            this.startActivity(friends);
        }
        else if (v == Profile) {
            Intent profile = new Intent(this, UserProfile.class);

            Button b = (Button) v;
            this.startActivity(profile);
        }
        else if (v == Settings) {
            Intent settings = new Intent(this, Settings.class);

            Button b = (Button) v;
            this.startActivity(settings);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_slide_out_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /***
     * Called when invalidateOptionsMenu() is triggered
     */


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);

            switch (position) {
                case 0:
                    Intent home = new Intent(MainActivity.this, MainActivity.class);

                    startActivity(home);
                    break;

                case 1:
                    Intent FindPeople = new Intent(MainActivity.this, Locate_Friends.class);

                    startActivity(FindPeople);
                    break;

                case 2:
                    Intent FindEvents = new Intent(MainActivity.this, LocateEvents.class);

                    startActivity(FindEvents);
                    break;

                case 3:
                    Intent FindHangouts = new Intent(MainActivity.this, LocateHangoutActivity.class);

                    startActivity(FindHangouts);
                    break;

                case 4:
                    Intent EditProfile = new Intent(MainActivity.this, EditProfileActivity.class);

                    startActivity(EditProfile);
                    break;

                case 5:
                    Intent Settings = new Intent(MainActivity.this, Settings.class);

                    startActivity(Settings);
                    break;
                default:

            }
        }
    }
    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        // update the main content by replacing fragments
       /* Fragment fragment = null;*/
       /* switch (position) {
            case 0:
                Intent events = new Intent(this, MainActivity.class);

               /* Button b = (Button) v;
                this.startActivity(events);
                break;

            default:

        }

        /*if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(id.frame_container, fragment).commit();
            */
        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerList.setSelection(position);
        setTitle(navMenuTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    /*} else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }*/
    }

}
