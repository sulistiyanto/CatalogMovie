package com.tubandev.catalogmovie.ui.home;

import android.support.v4.widget.DrawerLayout;

/**
 * Created by sulistiyanto on 25/02/18.
 */

public interface HomeContract {

    interface View {
        void closeDrawer();
        void actionBackPressed();
        void showNowPlaying();
        void showUpcoming();
        void showSearch();
    }

    interface UserActionListener {
        void backPressed(DrawerLayout drawer);
        void actionNavigationSelected(int id);
    }
}
