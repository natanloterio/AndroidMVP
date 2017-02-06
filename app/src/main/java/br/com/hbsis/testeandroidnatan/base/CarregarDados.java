package br.com.hbsis.testeandroidnatan.base;

import android.os.AsyncTask;

/**
 * Created by natan on 06/02/17.
 */

public class CarregarDados extends AsyncTask<Void,Void,Void> {

    private final AntesCarregarDados listenerAntesCarregarDados;
    private final DepoisCarregarDados listenerDepoisCarregarDados;

    public CarregarDados(AntesCarregarDados listenerAntesCarregarDados, DepoisCarregarDados listenerDepoisCarregarDados) {
        this.listenerAntesCarregarDados = listenerAntesCarregarDados;
        this.listenerDepoisCarregarDados = listenerDepoisCarregarDados;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    public interface AntesCarregarDados{
        void antesCarregarDados();
    }

    public interface DepoisCarregarDados{
        void depoisCarregarDados();
    }
}
