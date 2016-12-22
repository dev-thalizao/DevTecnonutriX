package br.com.thalesfrigo.devtecnonutrix.view.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.thalesfrigo.devtecnonutrix.view.contract.BaseActivityView;

/**
 * Created by thalesfrigo on 12/21/16.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
