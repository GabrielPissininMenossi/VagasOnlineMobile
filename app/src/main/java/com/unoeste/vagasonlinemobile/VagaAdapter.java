package com.unoeste.vagasonlinemobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.unoeste.vagasonlinemobile.entities.Vaga;

import java.util.List;

public class VagaAdapter extends ArrayAdapter<Vaga> {
    private int resource;
    private Button btInteresse;
    private MainActivity mainActivity;
    public VagaAdapter(@NonNull MainActivity mainActivity, int resource, @NonNull List<Vaga> vagas) {
        super(mainActivity, resource, vagas);
        this.resource = resource;
        this.mainActivity = mainActivity;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)
                    getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resource,parent,false);
        }
        TextView tvCargo =convertView.findViewById(R.id.tvCargo);
        TextView tvEmpresaCidade=convertView.findViewById(R.id.tvEmpresaCidade);
        TextView tvRemuneracao=convertView.findViewById(R.id.tvRemuneracao);
        TextView tvJornada=convertView.findViewById(R.id.tvJornada);
        TextView tvRegime=convertView.findViewById(R.id.tvRegime);
        btInteresse = convertView.findViewById(R.id.btInteresse);
        tvCargo.setText(getItem(position).getCargo());
        tvEmpresaCidade.setText(getItem(position).getEmpresa().getNome_fantasia()+" • "+getItem(position).getCidade()+" - "+getItem(position).getEstado());
        tvRemuneracao.setText(getItem(position).getRemuneracao());
        tvJornada.setText(getItem(position).getJornada_trabalho());
        tvRegime.setText(getItem(position).getRegime());

        btInteresse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.cadastrar(getItem((position)));
                //Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_LONG).show();
            }
        });
        return convertView;
    }
}
