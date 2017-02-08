package br.com.hbsis.testeandroidnatan.impl.view.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.hbsis.testeandroidnatan.R;
import br.com.hbsis.testeandroidnatan.impl.dao.base.Pessoa;

/**
 * Created by natan on 07/02/17.
 */

public class PessoaAdapter extends BaseAdapter{

    private final List<Pessoa> pessoas;
    private final Context context;

    public PessoaAdapter(Context ctx, List<Pessoa> pessoas) {
        this.context = ctx;
        this.pessoas = pessoas;
    }

    @Override
    public int getCount() {
        return pessoas.size();
    }

    @Override
    public Object getItem(int position) {
        return pessoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);
        }
        java.text.DateFormat df = DateFormat.getDateFormat(context);
        Pessoa p = pessoas.get(position);
        ((TextView)convertView.findViewById(R.id.list_item__nome)).setText(p.getNome());
        ((TextView)convertView.findViewById(R.id.list_item__sobrenome)).setText(p.getSobrenome());
        ((TextView)convertView.findViewById(R.id.list_item__data_nascimento)).setText(df.format(p.getDataNascimento()));
        return convertView;
    }
}
