package com.tubandev.catalogmovie.base;

import com.tubandev.catalogmovie.adapter.SearchAdapter;
import com.tubandev.catalogmovie.model.Result;

/**
 * Created by sulistiyanto on 06/03/18.
 */

public interface BaseView {
    void showMovie(SearchAdapter adapter);
    void showError(String message);
    void gotoDetailMovie(Result result);
}
