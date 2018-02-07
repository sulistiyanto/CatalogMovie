package com.tubandev.catalogmovie.ui;

import android.content.Context;

import com.tubandev.catalogmovie.adapter.SearchAdapter;

/**
 * Created by sulistiyanto on 07/02/18.
 */

public interface MainContract {

    interface View {
        void showMovie(SearchAdapter adapter);
        void showError(String message);
        void setErrorFieldSearch(String message);
    }

    interface UserActionsListener {
        void searchMovie(Context context, String search);
    }
}