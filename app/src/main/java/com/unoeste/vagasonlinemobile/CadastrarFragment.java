package com.unoeste.vagasonlinemobile;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.unoeste.vagasonlinemobile.configs.RetrofitInteressesConfig;
import com.unoeste.vagasonlinemobile.configs.RetrofitVagasConfig;
import com.unoeste.vagasonlinemobile.entities.Candidato;
import com.unoeste.vagasonlinemobile.entities.Interesses;
import com.unoeste.vagasonlinemobile.entities.Vaga;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CadastrarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadastrarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText etNome, etCpf, etEmail, etTelefone, etFormacao;
    private Button btConfirmar, btCancelar;
    private MainActivity mainActivity;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    public CadastrarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cadastrar.
     */
    // TODO: Rename and change types and number of parameters
    public static CadastrarFragment newInstance(String param1, String param2) {
        CadastrarFragment fragment = new CadastrarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastrar, container, false);
        btCancelar = view.findViewById(R.id.btCancelar);
        btConfirmar = view.findViewById(R.id.btConfirmar);
        etNome = view.findViewById(R.id.etNome);
        etCpf = view.findViewById(R.id.etCpf);
        etEmail = view.findViewById(R.id.etEmail);
        etTelefone = view.findViewById(R.id.etTelefone);
        etFormacao = view.findViewById(R.id.etFormacao);
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpar();
                mainActivity.voltar();
            }
        });
        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etNome.getText().toString().isEmpty() && !etCpf.getText().toString().isEmpty() && !etEmail.getText().toString().isEmpty() && !etTelefone.getText().toString().isEmpty() && !etFormacao.getText().toString().isEmpty())
                    addInteresse(v);
                else
                    Toast.makeText(view.getContext(), "Campo(s) Não Preenchido(s)", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
    private void addInteresse(View view)
    {
        String registro = getArguments().getString("registro");
        Call<Vaga> callVaga = new RetrofitVagasConfig().getVagasService().getOne(registro);
        callVaga.enqueue(new Callback<Vaga>() {
            @Override
            public void onResponse(Call<Vaga> call, Response<Vaga> response)
            {
                if (response.isSuccessful())
                {
                    if (response.body() != null)
                    {
                        Vaga vaga = response.body();
                        Candidato candidato = new Candidato(etNome.getText().toString(), etCpf.getText().toString(), etEmail.getText().toString(), etTelefone.getText().toString(), etFormacao.getText().toString());
                        Interesses interesses = new Interesses(vaga, candidato);
                        Call<Interesses> callInteresse = new RetrofitInteressesConfig().getInteressesService().addInteresse(interesses);
                        callInteresse.enqueue(new Callback<Interesses>() {
                            @Override
                            public void onResponse(Call<Interesses> call, Response<Interesses> response) {
                                if (response.isSuccessful())
                                    Toast.makeText(view.getContext(), "Interesse Enviado Com Sucesso", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(view.getContext(), "Erro ao Enviar Interesse", Toast.LENGTH_LONG).show();
                                limpar();
                            }

                            @Override
                            public void onFailure(Call<Interesses> call, Throwable t) {
                                Toast.makeText(view.getContext(), "Falha: " + t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(view.getContext(), "Vaga Não Encontrada", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(view.getContext(), "Erro Ao Consultar", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Vaga> call, Throwable t) {
                Toast.makeText(view.getContext(), "Falha: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
    private void limpar()
    {
        etTelefone.setText("");
        etNome.setText("");
        etEmail.setText("");
        etCpf.setText("");
        etFormacao.setText("");
    }
}