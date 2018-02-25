package com.tubandev.catalogmovie.ui.home;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

/**
 * Created by sulistiyanto on 25/02/18.
 */

public class HomePresenter implements HomeContract.UserActionListener {

    private HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void backPressed(DrawerLayout drawer) {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            view.closeDrawer();
        } else {
            view.actionBackPressed();
        }
    }
}
