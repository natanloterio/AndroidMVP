package br.com.hbsis.testeandroidnatan.impl.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.File;

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
        listView.setAdapter(new PessoaAdapter(MainActivity.this,getPresenter().getTodasPessoas()));
    }

    @Override
    public void atualizarView() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_realizar_backup:
                getPresenter().onClickFazerBackup();
                break;
            case R.id.action_atualizar_app:
                getPresenter().onClickAtualizarApp();
                //atualizar();
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void atualizar() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File("")), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
