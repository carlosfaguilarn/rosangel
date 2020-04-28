package com.rosangel.app.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rosangel.MainMenu;
import com.rosangel.R;
import com.rosangel.app.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    public Activity activity;
    EditText editUsuario, editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activity = LoginActivity.this;
        editUsuario = findViewById(R.id.editUsuario);
        editPassword = findViewById(R.id.editPassword);

        editUsuario.setText("carlos");
        editPassword.setText("123");

        findViewById(R.id.btnIngresar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IniciarSesion().execute();
            }
        });

    }

    public class IniciarSesion extends AsyncTask<Void, String, Boolean> {
        public int resultTask = 0;
        ImageView img_status;
        ProgressBar progress_status;
        TextView text_status;
        AlertDialog.Builder builder;
        AlertDialog alert;
        String url, HttpStatus, HttpMessage, UserIntent, PasswordIntent;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            url = Utils.URL_SERVER + "/login";
            /** Abre dialog iniciando...*/
            builder = new AlertDialog.Builder(activity);
            builder.setView(R.layout.progress_iniciando);
            builder.setCancelable(false);
            alert = builder.create();
            alert.show();
            /** Inicializar dialog*/
            img_status = alert.findViewById(R.id.img_status);
            progress_status = alert.findViewById(R.id.progress_status);
            text_status = alert.findViewById(R.id.txt_status);
            img_status.setVisibility(View.INVISIBLE);

            UserIntent = editUsuario.getText().toString();
            PasswordIntent = editPassword.getText().toString();

            HttpStatus = "";
            HttpMessage = "";
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try{
                Thread.sleep(1000);

                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(url);
                //AÑADIR PARAMETROS
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("email", UserIntent));
                params.add(new BasicNameValuePair("password", PasswordIntent));
                httppost.setEntity(new UrlEncodedFormEntity(params));
                HttpResponse resp = httpclient.execute(httppost);
                HttpEntity ent = resp.getEntity();
                JSONObject jsonResponse = new JSONObject(EntityUtils.toString(ent));
                HttpStatus = jsonResponse.getString("status");

                if(HttpStatus.equals("ok")){
                    alert.cancel();
                    JSONObject jsonUser = jsonResponse.getJSONObject("user");
                    String username = jsonUser.getString("name");
                    String photo = jsonUser.getString("photo");
                    Utils.setPreferences(LoginActivity.this, "username", username);
                    Utils.setPreferences(LoginActivity.this, "photo", photo);
                    startActivity(new Intent(activity, MainMenu.class));
                    finish();
                }else{
                    HttpMessage = jsonResponse.getString("message");
                }
            } catch (Exception e){
                Log.d("rps", "Error:" + e.getMessage());
                e.printStackTrace();
                resultTask = 2;
            } finally {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            text_status.setText(HttpMessage);
            text_status.setVisibility(View.VISIBLE);
            img_status.setVisibility(View.VISIBLE);
            progress_status.setVisibility(View.INVISIBLE);
            alert.setCancelable(true);
        }
    }
}
