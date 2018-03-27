package com.tubandev.catalogmovie.ui.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tubandev.catalogmovie.R;
import com.tubandev.catalogmovie.model.Result;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView imageView = findViewById(R.id.imageView);
        TextView txtTilte = findViewById(R.id.txtTitle);
        TextView txtDate = findViewById(R.id.txtDate);
        TextView txtPopularity = findViewById(R.id.txtPopularity);
        TextView txtOverView = findViewById(R.id.txtOverView);

        Result result = getIntent().getParcelableExtra("result");
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + result.getPosterPath())
                .into(imageView);

        txtTilte.setText(result.getTitle());
        txtDate.setText(result.getReleaseDate());
        txtPopularity.setText(result.getPopularity() + "");
        txtOverView.setText(result.getOverview());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
