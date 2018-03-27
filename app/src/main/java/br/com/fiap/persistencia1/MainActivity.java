package br.com.fiap.persistencia1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    EditText edtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getPreferences(MODE_PRIVATE);
        edtNome = findViewById(R.id.edtNome);
    }

    public void Recuperar(View view) {
        String nome = sp.getString("nome", "");
        Toast.makeText(this, getString(R.string.o_nome_salvo) + nome, Toast.LENGTH_SHORT).show();
    }

    public void salvar(View view) {
        SharedPreferences.Editor e = sp.edit();
        String nome = edtNome.getText().toString();
        e.putString("nome", nome);
        e.commit();
    }

    public void criarArquivo(View view) {
        try {
            FileOutputStream fos = openFileOutput("banco.txt", MODE_PRIVATE);
            String nome = edtNome.getText().toString();
            fos.write(nome.getBytes());
            fos.close();

            Toast.makeText(this, R.string.arquivo_criado_com_sucesso, Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lerArquivo(View view) {
        try {
            FileInputStream fis = openFileInput("banco.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String txt = br.readLine();
            fis.close();
            Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
