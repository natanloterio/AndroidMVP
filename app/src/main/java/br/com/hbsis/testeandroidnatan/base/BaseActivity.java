package br.com.hbsis.testeandroidnatan.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

import br.com.hbsis.testeandroidnatan.base.util.DialogUtils;

/**
 * Created by natan on 06/02/17.
 */

public abstract class BaseActivity<PresenterClass> extends FragmentActivity implements IMainView, IViewNotifier{

    // Presenter da activity
    private PresenterClass presenter;

    private boolean deverReinicializarPresenter = true;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        presenter = getPresenterNewsInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (deveReinicializarPresenter()) {
                presenter = getPresenterNewsInstance();
            }

            ((BasePresenter)presenter).onResume();

            deverReinicializarPresenter = false;
        }catch (Exception e){
            e.printStackTrace();
            DialogUtils.mostrarAlerta(BaseActivity.this,"Alerta","Aconteceu um problema durante ao abrir inicializar a tela. Contate o suporte",new  DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            } );
        }
    }

    @Override
    public void mostrarAlerta(String s) {
        DialogUtils.mostrarAlerta(BaseActivity.this,"Alerta",s,new  DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        } );
    }

    @Override
    public void mostrarAlertaFatal(String s) {
        DialogUtils.mostrarAlerta(BaseActivity.this,"Erro grave",s,new  DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        } );
    }

    /**
     * Retorna uma instancia do @{@link BasePresenter<PresenterClass>}
     */
    protected PresenterClass getPresenter(){
        return this.presenter;
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
    protected abstract PresenterClass getPresenterNewsInstance();


}
