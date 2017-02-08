package br.com.hbsis.testeandroidnatan.impl.view.activity;

import android.os.Bundle;
import android.widget.ListView;

import br.com.hbsis.testeandroidnatan.R;
import br.com.hbsis.testeandroidnatan.base.BaseActivity;
import br.com.hbsis.testeandroidnatan.impl.presenter.MainPresenter;
import br.com.hbsis.testeandroidnatan.impl.view.adapter.PessoaAdapter;

/**
 * Created by natan on 06/02/17.
 */

public class MainActivity extends BaseActivity<MainPresenter> {

    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    protected MainPresenter getPresenterNewsInstance() {
        return new MainPresenter(MainActivity.this);
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
