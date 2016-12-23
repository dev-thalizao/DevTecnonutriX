package br.com.thalesfrigo.devtecnonutrix.view.ui;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.thalesfrigo.devtecnonutrix.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment(new FeedFragment(this), "DevTecnonutriX", FeedFragment.TAG);
    }

    @Override
    public void initFragment(Fragment fragment, String tag, String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment, tag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
        setTitle(title);
    }

    @Override
    public void changeFragment(Fragment fragment, String tag, String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment, tag)
                .addToBackStack(fragment.getTag())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
        setTitle(title);
    }

    @Override
    public void onBackPressed(){
        if (getSupportFragmentManager().getBackStackEntryCount() == 0){
            finish();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public void showMessage(View view, int messageResourceId) {
        Snackbar.make(view, messageResourceId, Snackbar.LENGTH_SHORT).show();
    }
}
