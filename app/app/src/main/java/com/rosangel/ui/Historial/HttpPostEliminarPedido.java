package com.rosangel.ui.Historial;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.rosangel.R;
import com.rosangel.app.Modelos.Pedido;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HttpPostEliminarPedido extends AsyncTask<Void, String, Void> {
    private View view;
    private String url = Utils.URL_SERVER + "/pedidos/delete";
    private int pedidoID;
    private Context context;
    private AlertDialog.Builder builder;
    private AlertDialog alert;
    private JSONObject Respuesta;

    public HttpPostEliminarPedido(Context context, View view, int pedidoID){
        this.context = context;
        this.view = view;
        this.pedidoID = pedidoID;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        builder = new AlertDialog.Builder(context);
        builder.setView(R.layout.progress_simple_status);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Activity activity = (Activity) context;
                activity.finish();
            }
        });
        builder.setCancelable(false);
        alert = builder.create();
        alert.show();
        alert.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            //AÑADIR PARAMETROS
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("pedidoid", String.valueOf(pedidoID)));
            httppost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();
            Respuesta = new JSONObject(EntityUtils.toString(ent));
        } catch (Exception e){
            Log.d("rp", "Error:" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        alert.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
        TextView txt_estatus = alert.findViewById(R.id.txt_estatus);
        ImageView img_status = alert.findViewById(R.id.img_status);
        alert.findViewById(R.id.progress_status).setVisibility(View.INVISIBLE);
        try {
            if(Respuesta.getInt("status") == Utils.STATUS_CORRECT){
                img_status.setImageResource(R.drawable.correct);
            }else{
                img_status.setImageResource(R.drawable.error);
            }
            txt_estatus.setText(Respuesta.getString("message"));
            img_status.setVisibility(View.VISIBLE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
