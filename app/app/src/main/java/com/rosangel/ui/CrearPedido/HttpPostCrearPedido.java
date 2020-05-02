package com.rosangel.ui.CrearPedido;

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

import com.android.volley.Response;
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

public class HttpPostCrearPedido extends AsyncTask<Void, String, Void> {
    private View view;
    private String url = Utils.URL_SERVER + "/pedidos/crear";
    private Pedido pedido;
    private Context context;
    private AlertDialog.Builder builder;
    private AlertDialog alert;
    private TextView txt_detalle;
    private ImageView img_status;
    private TextView txt_estatus;
    private ProgressBar pr_status;
    private String detalleError = "";
    private JSONObject Respuesta;

    public HttpPostCrearPedido(Context context, View view, Pedido pedido){
        this.context = context;
        this.view = view;
        this.pedido = pedido;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        builder = new AlertDialog.Builder(context);
        builder.setView(R.layout.progress_dialog_guardando);
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
        img_status = alert.findViewById(R.id.img_status);
        img_status.setVisibility(View.INVISIBLE);
        txt_detalle = alert.findViewById(R.id.txt_detalle);
        txt_detalle.setText("");
        txt_detalle.setVisibility(View.GONE);
        pr_status = alert.findViewById(R.id.progress_status);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            //AÑADIR PARAMETROS
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("producto", pedido.getProducto()));
            params.add(new BasicNameValuePair("cliente", pedido.getCliente()));
            params.add(new BasicNameValuePair("empleado", pedido.getEmpleado()));
            params.add(new BasicNameValuePair("direccion", pedido.getDireccion()));
            params.add(new BasicNameValuePair("telefono", pedido.getTelefono()));
            params.add(new BasicNameValuePair("observaciones", pedido.getObservaciones()));
            httppost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();
            Respuesta = new JSONObject(EntityUtils.toString(ent));
            Thread.sleep(1000);
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
        txt_estatus = alert.findViewById(R.id.txt_estatus);
        pr_status.setVisibility(View.INVISIBLE);
        ImageView img_status = alert.findViewById(R.id.img_status);
        try {
            if(Respuesta.getInt("status") == Utils.STATUS_CORRECT){
                img_status.setImageResource(R.drawable.correct);
            }else{
                img_status.setImageResource(R.drawable.error);
            }
            txt_estatus.setText(Respuesta.getString("message"));
            txt_detalle.setVisibility(View.VISIBLE);
            img_status.setVisibility(View.VISIBLE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
