package com.tubandev.catalogmovie.ui.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.tubandev.catalogmovie.adapter.SearchAdapter;
import com.tubandev.catalogmovie.model.MainResult;
import com.tubandev.catalogmovie.model.Result;
import com.tubandev.catalogmovie.services.Config;
import com.tubandev.catalogmovie.services.SearchEndpoint;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sulistiyanto on 07/02/18.
 */

public class MainPresenter implements MainContract.UserActionsListener {

    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void searchMovie(final Context context, String search) {
        if (isConnecting(context)) {
            if (search.equals("")) {
                view.setErrorFieldSearch("Kolom pencarian harus diisi");
            } else {
                SearchEndpoint api = Config.getService();
                Call<MainResult> call = api.searchMovie(search);
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
            }

        } else {
            view.showError("Periksa koneksi internet Anda");
        }
    }

    private boolean isConnecting(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null){
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
