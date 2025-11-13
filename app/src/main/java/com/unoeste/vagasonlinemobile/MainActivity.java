package com.unoeste.vagasonlinemobile;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.unoeste.vagasonlinemobile.configs.RetrofitVagasConfig;
import com.unoeste.vagasonlinemobile.entities.Vaga;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private VagasFragment vagasFragment = new VagasFragment();

    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.frameLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        frameLayout = findViewById(R.id.frameLayout);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), vagasFragment);
        fragmentTransaction.commit();
    }
    public void cadastrar(Vaga vaga)
    {
        CadastrarFragment cadastrarFragment = new CadastrarFragment();
        Bundle bundle = new Bundle();
        bundle.putString("registro", vaga.getRegistro());
        bundle.putString("nome_fantasia", vaga.getEmpresa().getNome_fantasia());
        bundle.putString("cargo", vaga.getCargo());
        cadastrarFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), cadastrarFragment);
        fragmentTransaction.commit();
    }
    public void voltar()
    {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), vagasFragment);
        fragmentTransaction.commit();
    }
}