package br.com.thalesfrigo.devtecnonutrix.view.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by thalesfrigo on 12/21/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    abstract protected void renderView();
    abstract protected void init();
}
