package br.com.hbsis.testeandroidnatan.impl.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ListView;

import br.com.hbsis.testeandroidnatan.R;
import br.com.hbsis.testeandroidnatan.base.BaseActivity;
import br.com.hbsis.testeandroidnatan.impl.presenter.PresenterImpl;
import br.com.hbsis.testeandroidnatan.impl.view.adapter.PessoaAdapter;

/**
 * Created by natan on 06/02/17.
 */

public class MainActivity extends BaseActivity<PresenterImpl> {

    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    protected PresenterImpl getPresenterNewsInstance() {
        return new PresenterImpl(MainActivity.this);
    }

    @Override
    public void registerViews() {
        getPresenter().registerView(this);
    }

    @Override
    public void initializeViews() {
        listView = (ListView)findViewById(R.id.main_listview);
    }

    @Override
    public void inicializarAdapter() {
        PessoaAdapter adapter = new PessoaAdapter(MainActivity.this,getPresenter().getTodasPessoas());
        listView.setAdapter(adapter);
    }

    @Override
    public void atualizarView() {

    }




}
