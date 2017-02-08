package br.com.hbsis.testeandroidnatan.impl.presenter;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.hbsis.testeandroidnatan.base.BasePresenter;
import br.com.hbsis.testeandroidnatan.impl.dao.base.BaseDaoFactory;
import br.com.hbsis.testeandroidnatan.impl.dao.base.Pessoa;

/**
 * Created by natan on 07/02/17.
 */

public class MainPresenter extends BasePresenter{

    private List<Pessoa> todasPessoas = new ArrayList<>();

    public MainPresenter(Context activityContext)  {
        super(activityContext);
    }

    @Override
    protected void carrearDados() throws Exception {
        todasPessoas = getDaoFactory().getInstance(BaseDaoFactory.DaoFactoryType.LOCAL).getAll();
    }

    public List<Pessoa> getTodasPessoas() {
        return todasPessoas;
    }
}
