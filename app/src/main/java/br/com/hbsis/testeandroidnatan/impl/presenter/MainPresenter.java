package br.com.hbsis.testeandroidnatan.impl.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.hbsis.testeandroidnatan.base.BasePresenter;
import br.com.hbsis.testeandroidnatan.impl.dao.base.BaseDaoFactory;
import br.com.hbsis.testeandroidnatan.impl.dao.base.Pessoa;
import br.com.hbsis.testeandroidnatan.util.ApkUpdater;
import br.com.hbsis.testeandroidnatan.util.SQLiteBackupManager;

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

    public void onClickAtualizarApp() {
        (new AsyncTask<Void,Void,File>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                getMainView().mostrarProgressDialog("Baixando nova Versão");
            }

            @Override
            protected File doInBackground(Void... params) {
                try {
                    return baixarNovoApk();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(File novoApk) {
                getMainView().esconderProgressDialog();

                if(novoApk!=null){
                    instalarNovoApk(novoApk);
                }else{
                    getMainView().mostrarAlerta("Ocorreu um erro durante o download da nova versão do app");
                }
                super.onPostExecute(novoApk);
            }
        }).execute();

    }

    private void instalarNovoApk(File novoApk) {
        Log.w(this.getClass().getSimpleName(),"Instalando nova versão de:"+novoApk.getAbsolutePath());
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(novoApk), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getActivityContext().startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private File baixarNovoApk() throws IOException {
        return (new ApkUpdater().downloadNewVersion());
    }

    public void onClickFazerBackup() {
        try {
            String local = fazerBackupBancoDeDados();
            showFileOnDownloadFolder(local);
            getMainView().mostrarAlerta("Backup criado em:"+local);
        } catch (IOException e) {
            e.printStackTrace();
            getMainView().mostrarAlerta("Ocorreu um erro ao realizar o backup do banco de dados");
        }

    }

    private void showFileOnDownloadFolder(String local) {
        getActivityContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(local))));
    }

    public String fazerBackupBancoDeDados() throws IOException {
        return (new SQLiteBackupManager()).fazerBackup(getActivityContext());
    }
}
