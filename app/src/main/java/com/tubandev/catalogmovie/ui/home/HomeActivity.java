package com.tubandev.catalogmovie.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.tubandev.catalogmovie.R;
import com.tubandev.catalogmovie.ui.now_playing.NowPlayingFragment;
import com.tubandev.catalogmovie.ui.search.SearchFragment;
import com.tubandev.catalogmovie.ui.up_coming.UpComingFragment;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeContract.View {

    private FragmentTransaction fragmentTransaction;
    private HomePresenter presenter;
    private static final String LOCALE_KEY = "localekey";
    private static final String INDONESIA_LOCALE = "id";
    private static final String ENGLISH_LOCALE = "en_US";
    private static final String LOCALE_PREF_KEY = "localePref";
    private Locale locale;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.now_playing);
        presenter = new HomePresenter(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        showNowPlaying();
    }

    @Override
    public void onBackPressed() {
        presenter.backPressed(drawer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        presenter.actionNavigationSelected(id);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void actionBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showNowPlaying() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, new NowPlayingFragment());
        fragmentTransaction.commitAllowingStateLoss();
        getSupportActionBar().setTitle(R.string.now_playing);
    }

    @Override
    public void showUpcoming() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, new UpComingFragment());
        fragmentTransaction.commitAllowingStateLoss();
        getSupportActionBar().setTitle(R.string.upcoming);
    }

    @Override
    public void showSearch() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, new SearchFragment());
        fragmentTransaction.commitAllowingStateLoss();
        getSupportActionBar().setTitle(R.string.search);
    }
}
