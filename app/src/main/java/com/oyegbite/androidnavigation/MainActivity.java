package com.oyegbite.androidnavigation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private AppBarConfiguration mAppBarConfiguration;
    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        // Set up Action Bar
        mNavController = navHostFragment.getNavController();

        mAppBarConfiguration = new AppBarConfiguration.Builder(mNavController.getGraph()).build();

        // TODO STEP 9.5 - Create an AppBarConfiguration with the correct top-level destinations
        // You should also remove the old appBarConfiguration setup above
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        mAppBarConfiguration =
                new AppBarConfiguration.Builder(
                    R.id.home_dest, R.id.deeplink_dest
                ).setDrawerLayout(drawerLayout).build();
        // TODO END STEP 9.5

        setupActionBar();

        setupNavigationMenu();

        setupBottomNavMenu();

        mNavController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                String dest;
                try {
                    dest = getResources().getResourceName(destination.getId());
                } catch (Resources.NotFoundException e) {
                    dest = Integer.toString(destination.getId());
                }

                Toast.makeText(
                        MainActivity.this,
                        "Navigated to " + dest,
                        Toast.LENGTH_SHORT
                ).show();
                Log.e("NavigationActivity", "Navigated to " + dest);
            }
        });
    }

    private void setupActionBar() {
//        // TODO STEP 9.6 - Have NavigationUI handle what your ActionBar displays
//        // This allows NavigationUI to decide what label to show in the action bar
//        // By using appBarConfig, it will also determine whether to
//        // show the up arrow or drawer menu icon
        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfiguration);
//        // TODO END STEP 9.6
    }

    private void setupNavigationMenu() {
        // TODO STEP 9.4 - Use NavigationUI to set up a Navigation View
        // In split screen mode, you can drag this view out from the left
        // This does NOT modify the actionbar
        NavigationView sideNavView = findViewById(R.id.nav_view);
        Log.e(TAG, "nav_view id = " + R.id.nav_view);
        Log.e(TAG, "Side nav view = " + sideNavView);
        if (sideNavView != null) {
            NavigationUI.setupWithNavController(sideNavView, mNavController);
        }
        // TODO END STEP 9.4
    }

    private void setupBottomNavMenu() {
        // TODO STEP 9.3 - Use NavigationUI to set up Bottom Nav
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_view);
        Log.e(TAG, "bottom_nav_view id = " + R.id.bottom_nav_view);
        Log.e(TAG, "Bottom nav view = " + bottomNav);
        if (bottomNav != null) {
            NavigationUI.setupWithNavController(bottomNav, mNavController);
        }
        // TODO END STEP 9.3
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        NavigationView navigationView = findViewById(R.id.nav_view);

        // The NavigationView already has these same navigation items, so we only add
        // navigation items to the menu here if there isn't a NavigationView
        if (navigationView == null) {
            getMenuInflater().inflate(R.menu.overflow_menu, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // NavigationUI has static methods that associate menu items with navigation destinations.
        // NOTE: If NavigationUI finds a menu item with the same ID as a destination on the current
        // graph, it configures the menu item to navigate to that destination.
        // TODO STEP 9.2 - Have Navigation UI Handle the item selection - make sure to delete
        //  the old return statement above
        // Have the NavigationUI look for an action or destination matching the menu
        // item id and navigate there if found.
        // Otherwise, bubble up to the parent (If the menu item is not meant to navigate,
        // handle with super.onOptionsItemSelected)
        return NavigationUI.onNavDestinationSelected(item, mNavController) || super.onOptionsItemSelected(item);
        // TODO END STEP 9.2
    }

    // TODO STEP 9.7 - Have NavigationUI handle up behavior in the ActionBar
    @Override
    public boolean onSupportNavigateUp() {
        // Allows NavigationUI to support proper up navigation or the drawer layout
        // drawer menu, depending on the situation
        return NavigationUI.navigateUp(mNavController, mAppBarConfiguration);
    }
    // TODO END STEP 9.7
}