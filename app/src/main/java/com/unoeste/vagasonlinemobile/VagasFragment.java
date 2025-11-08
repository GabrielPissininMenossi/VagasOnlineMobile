package com.unoeste.vagasonlinemobile;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.unoeste.vagasonlinemobile.configs.RetrofitVagasConfig;
import com.unoeste.vagasonlinemobile.entities.Vaga;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VagasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VagasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ListView listView;
    private MainActivity mainActivity;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VagasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VagasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VagasFragment newInstance(String param1, String param2) {
        VagasFragment fragment = new VagasFragment();
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_vagas, container, false);
        listView = view.findViewById(R.id.listView);
        getAllVagas(view);
        return view;
    }
    private void getAllVagas(View view)
    {
        Call<List<Vaga>> call = new RetrofitVagasConfig().getVagasService().getAll();
        call.enqueue(new Callback<List<Vaga>>() {
            @Override
            public void onResponse(Call<List<Vaga>> call, Response<List<Vaga>> response)
            {
                ArrayAdapter<Vaga> adapter;
                if (response.isSuccessful())
                {
                    if (response.body() != null)
                    {
                        List<Vaga> vagaList = response.body();
                        adapter = new VagaAdapter(mainActivity, R.layout.item_layout, vagaList);
                        listView.setAdapter(adapter);
                    }
                    else
                    {
                        Toast.makeText(view.getContext(), "Nenhuma Vaga Encontrada", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(view.getContext(), "Erro Ao Consultar", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Vaga>> call, Throwable t) {
                Toast.makeText(view.getContext(), "Falha: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}