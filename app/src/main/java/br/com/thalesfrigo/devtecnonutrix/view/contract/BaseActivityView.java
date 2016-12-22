package br.com.thalesfrigo.devtecnonutrix.view.contract;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by thalesfrigo on 12/22/16.
 */
public interface BaseActivityView {

    void initFragment(final Fragment fragment, final String title);

    void changeFragment(final Fragment fragment, final String title);

    void showMessage(View view, int messageResourceId);
}
