package com.example.heroesapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Api.Heroes;
import URL.url;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etDesc;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDesc);
        btnSave=findViewById(R.id.btnSave);

      btnSave.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Save();
          }


      });



    }


    private void Save() {


            String name = etName.getText().toString();
            String desc = etDesc.getText().toString();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Heroes api = retrofit.create(Heroes.class);

            Call<Void> heroesCall = api.addHero(name,desc);

            heroesCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                    if(!response.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this, "Code", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(MainActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



