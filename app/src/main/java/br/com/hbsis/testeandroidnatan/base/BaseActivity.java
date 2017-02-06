package br.com.hbsis.testeandroidnatan.base;

import android.support.v4.app.FragmentActivity;

/**
 * Created by natan on 06/02/17.
 */

public abstract class BaseActivity extends FragmentActivity {

    // Presenter da activity
    private BasePresenter presenter;

    private boolean deverReinicializarPresenter = true;

    @Override
    protected void onResume() {
        super.onResume();
        if(deveReinicializarPresenter()){
            presenter = getPresenter();
        }

        // notifica o presenter de que deve se atualizar
        presenter.onResume();

        deverReinicializarPresenter = false;
    }

    /**
     * Retorna boolean indicando se o presenter deve ser reinicializado
     * @return @boolean
     */
    private boolean deveReinicializarPresenter() {
        return deverReinicializarPresenter;
    }

    /**
     * Retorna uma instânca de @{@link BasePresenter}
     * @return @{@link BasePresenter}
     */
    protected abstract BasePresenter getPresenter();
}
