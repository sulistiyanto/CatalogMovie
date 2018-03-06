package com.tubandev.catalogmovie.ui.up_coming;

import android.content.Context;

import com.tubandev.catalogmovie.adapter.SearchAdapter;
import com.tubandev.catalogmovie.base.BaseActionListener;
import com.tubandev.catalogmovie.base.BaseView;
import com.tubandev.catalogmovie.model.MainResult;
import com.tubandev.catalogmovie.model.Result;
import com.tubandev.catalogmovie.services.ApiEndpoint;
import com.tubandev.catalogmovie.services.Config;
import com.tubandev.catalogmovie.utilities.Connection;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sulistiyanto on 06/03/18.
 */

public class UpComingPresenter implements BaseActionListener {

    private BaseView view;

    public UpComingPresenter(BaseView view) {
        this.view = view;
    }

    @Override
    public void loadData(final Context context, String search) {
        boolean connected = new Connection().isConnecting(context);
        if (connected) {
            ApiEndpoint api = Config.getService();
            Call<MainResult> call = api.upcoming();
            call.enqueue(new Callback<MainResult>() {
                @Override
                public void onResponse(Call<MainResult> call, Response<MainResult> response) {
                    List<Result> resultList = response.body().getResults();
                    if (resultList.size() > 0) {
                        SearchAdapter adapter = new SearchAdapter(context, resultList, new SearchAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClicked(Result result) {
                                view.gotoDetailMovie(result);
                            }
                        });
                        view.showMovie(adapter);
                    } else {
                        view.showError("Film yang Anda cari tidak ada");
                    }
                }

                @Override
                public void onFailure(Call<MainResult> call, Throwable t) {
                    view.showError("Pencarian gagal, silahkan coba lagi");
                }
            });
        } else {
            view.showError("Periksa koneksi internet Anda");
        }
    }
}
