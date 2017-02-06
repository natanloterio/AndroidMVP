package br.com.hbsis.testeandroidnatan.base;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by natan on 06/02/17.
 */

public abstract class BasePresenter {

    // Flag indicando o estado do Presenter.
    // Depois de ir para background, o Android pode não restaurar o estado dos objetos
    // que não extendem de view. Então, devemos retaurar por nossa conta.
    private boolean inicializado = false;

    // Flag que indica se está carregando ou não dados
    private boolean carregandoDados = false;

    // Lista com as views que este presenter controla
    private ArrayList<IVew> views = new ArrayList<>();

    // Contexto da Activity
    private Context activityContext;

    public BasePresenter(Context activityContext) {
        this.activityContext = activityContext;
    }

    public void onResume() {

        if(!inicializado){
            inicializarPresenter();
        }

    }

    private void inicializarPresenter() {
        asyncCarregarDados = new CarregarDados();

    }

    /**
     * Evento destinado para buscar dados locais ou remotos. Este método roda em segundo plano.
     */
    protected abstract void carrearDados();
}
