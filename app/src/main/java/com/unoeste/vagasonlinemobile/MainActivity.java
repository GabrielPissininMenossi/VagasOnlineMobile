package com.unoeste.vagasonlinemobile;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.unoeste.vagasonlinemobile.configs.RetrofitVagasConfig;
import com.unoeste.vagasonlinemobile.entities.Vaga;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView = findViewById(R.id.listView);
        getAllVagas();
    }
    public void getAllVagas()
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
                        adapter = new ArrayAdapter<Vaga>(getApplicationContext(), android.R.layout.simple_list_item_1, vagaList);
                        listView.setAdapter(adapter);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Nenhuma Vaga Encontrada", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Erro Ao Consultar", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Vaga>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Falha: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}