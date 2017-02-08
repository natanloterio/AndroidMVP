package br.com.hbsis.testeandroidnatan.impl.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.hbsis.testeandroidnatan.base.BasePresenter;
import br.com.hbsis.testeandroidnatan.impl.dao.base.BaseDaoFactory;
import br.com.hbsis.testeandroidnatan.impl.dao.base.Pessoa;

/**
 * Created by natan on 07/02/17.
 */

public class PresenterImpl  extends BasePresenter{
    private String jsonURL="";

    public PresenterImpl(Context activityContext)  {
        super(activityContext);
    }

    @Override
    protected void carrearDados() throws Exception {

        if(deveBaixarNovaCargaDeDados()){
            getDaoFactory().updateData();
        }

    }

    private boolean deveBaixarNovaCargaDeDados() throws Exception {
        int count = getDaoFactory().getInstance(BaseDaoFactory.DaoFactoryType.LOCAL).getAll().size();
        return count<=0;
    }

    @Override
    protected void antesDeCarregarDados() {

    }

    @Override
    protected void depoisDeCarregarDados() {

    }


    public List<Pessoa> getTodasPessoas() {
        try {
            return getDaoFactory().getInstance(BaseDaoFactory.DaoFactoryType.LOCAL).getAll();
        } catch (Exception e) {
            e.printStackTrace();
            getMainView().mostrarAlertaFatal("Aconteceu um erro grave. Não será possível prosseguir");
        }
        return new ArrayList<>();
    }
}
