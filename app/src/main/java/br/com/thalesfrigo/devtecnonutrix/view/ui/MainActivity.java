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

        FeedFragment feedFragment = new FeedFragment(this);

//        Bundle bundle = new Bundle();
//        bundle.putParcelable("contact_parcel", contact);
//        messagesListFragment.setArguments(bundle);

        initFragment(feedFragment, "Feed");
    }

    @Override
    public void initFragment(Fragment fragment, String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
        setTitle(title);
    }

    @Override
    public void changeFragment(Fragment fragment, String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .addToBackStack(fragment.getTag())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
        setTitle(title);
    }

    @Override
    public void showMessage(View view, int messageResourceId) {
        Snackbar.make(view, messageResourceId, Snackbar.LENGTH_SHORT).show();
    }
}
