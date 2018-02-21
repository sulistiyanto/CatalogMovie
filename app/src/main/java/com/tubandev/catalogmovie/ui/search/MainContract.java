package com.tubandev.catalogmovie.ui.main;

import android.content.Context;

import com.tubandev.catalogmovie.adapter.SearchAdapter;
import com.tubandev.catalogmovie.model.Result;

/**
 * Created by sulistiyanto on 07/02/18.
 */

public interface MainContract {

    interface View {
        void showMovie(SearchAdapter adapter);
        void showError(String message);
        void setErrorFieldSearch(String message);
        void gotoDetailMovie(Result result);
    }

    interface UserActionsListener {
        void searchMovie(Context context, String search);
    }
}