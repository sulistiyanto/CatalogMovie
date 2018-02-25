package com.tubandev.catalogmovie.ui.search;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tubandev.catalogmovie.R;
import com.tubandev.catalogmovie.adapter.SearchAdapter;
import com.tubandev.catalogmovie.model.Result;
import com.tubandev.catalogmovie.ui.detail.DetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements SearchContract.View {

    private SearchContract.UserActionsListener listener;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.editTextSearch)
    EditText editTextSearch;

    public SearchFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, rootView);

        listener = new SearchPresenter(this);
        progressBar.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void showMovie(SearchAdapter adapter) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setErrorFieldSearch(String message) {
        editTextSearch.setError(message);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void gotoDetailMovie(Result result) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("result", result);
        startActivity(intent);
    }

    @OnClick(R.id.button)
    void buttonSearch() {
        String search = editTextSearch.getText().toString();
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        listener.searchMovie(getContext(), search);
    }
}
