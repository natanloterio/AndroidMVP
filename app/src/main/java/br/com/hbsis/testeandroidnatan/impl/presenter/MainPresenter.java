package br.com.hbsis.testeandroidnatan.impl.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.hbsis.testeandroidnatan.base.BasePresenter;
import br.com.hbsis.testeandroidnatan.impl.dao.base.BaseDaoFactory;
import br.com.hbsis.testeandroidnatan.impl.dao.base.Pessoa;
import br.com.hbsis.testeandroidnatan.network.ApkUpdater;
import br.com.hbsis.testeandroidnatan.network.MediaScannerWrapper;
import br.com.hbsis.testeandroidnatan.network.SQLiteBackupManager;

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
        try {
            File novoApk = baixarNovoApk();
            instalarNovoApk(novoApk);
        } catch (IOException e) {
            e.printStackTrace();
            getMainView().mostrarAlerta("Ocorreu um erro durante o download da nova versão do app");
        }

    }

    private void instalarNovoApk(File novoApk) {
        Uri uri = Uri.parse(novoApk.getAbsolutePath());
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivityContext().startActivity(install);
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
