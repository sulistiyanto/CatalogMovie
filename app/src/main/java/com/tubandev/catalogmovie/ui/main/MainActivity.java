package com.tubandev.catalogmovie.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tubandev.catalogmovie.R;
import com.tubandev.catalogmovie.adapter.SearchAdapter;
import com.tubandev.catalogmovie.model.Result;
import com.tubandev.catalogmovie.ui.detail.DetailActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.UserActionsListener listener;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listener = new MainPresenter(this);
        editTextSearch = findViewById(R.id.editText);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = editTextSearch.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                listener.searchMovie(getBaseContext(), search);
            }
        });
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setErrorFieldSearch(String message) {
        editTextSearch.setError(message);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void gotoDetailMoview(Result result) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("result", result);
        startActivity(intent);
    }
}
