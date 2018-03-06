package com.tubandev.catalogmovie.base;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.tubandev.catalogmovie.R;

import butterknife.BindView;

/**
 * Created by sulistiyanto on 06/03/18.
 */

public class BaseFragment extends Fragment {

    protected @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    protected @BindView(R.id.progressBar)
    ProgressBar progressBar;
    protected @BindView(R.id.editTextSearch)
    EditText editTextSearch;
    protected @BindView(R.id.button)
    Button button;

    public BaseFragment() {
    }

}
