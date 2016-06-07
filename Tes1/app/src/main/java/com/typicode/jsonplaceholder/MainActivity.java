package com.typicode.jsonplaceholder;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.typicode.jsonplaceholder.models.Model;
import com.typicode.jsonplaceholder.rest.RestApi;
import com.typicode.jsonplaceholder.tes1.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    public static final String ROOT_URL = "http://jsonplaceholder.typicode.com";

    private TextView txt_userId;
    private TextView txt_id;
    private TextView txt_title;
    private TextView txt_body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_userId = (TextView) findViewById(R.id.txt_userId);
        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_body =(TextView) findViewById(R.id.txt_body);


        //Memanggil method untuk mengambil data
        getData();
    }

    private void getData() {
        //ketika aplikasi sedang mengambil data kita akan melihat progress dialog muncul
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait..", false, false);

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create())//GsonConverter untuk parsing json
            .build();
        RestApi service = retrofit.create(RestApi.class);
        Call<Model> call = service.getUserId();
        call.enqueue(new Callback<Model>() { //Asyncronous Request
                 @Override
                 public void onResponse(Call<Model> call, Response<Model> response) {
                     try {
                         loading.dismiss(); //progress dialog dihentikan

                         String userId = response.body().getUserId().toString();
                         String id = response.body().getId().toString();
                         String title = response.body().getTitle();
                         String body = response.body().getBody();

                         txt_userId.setText("ID : " + userId);
                         txt_id.setText("Nama : " + id);
                         txt_title.setText("Email : " + title);
                         txt_body.setText("Alamat : " + body);
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                 }

                 @Override
                 public void onFailure(Call<Model> call, Throwable t) {

                 }

             }
        );

    }
}

