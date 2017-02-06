package br.com.hbsis.testeandroidnatan.impl.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

import br.com.hbsis.testeandroidnatan.R;

/**
 * Created by natan on 06/02/17.
 */

public class MainActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.main_activity);
    }
}
