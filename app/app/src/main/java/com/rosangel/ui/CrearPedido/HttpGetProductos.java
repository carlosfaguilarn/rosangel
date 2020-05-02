package com.rosangel.ui.CrearPedido;

import android.os.AsyncTask;
import android.util.Log;
import com.rosangel.app.Modelos.Producto;
import com.rosangel.app.Utils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class HttpGetProductos extends AsyncTask<Void, String, Void> {
    protected String url = Utils.URL_SERVER + "/productos";
    private ArrayList<String> productosList;
    private ArrayList<Producto> productosListAll;

    HttpGetProductos(ArrayList<String> productosList,
                     ArrayList<Producto> productosListAll){
        this.productosList = productosList;
        this.productosListAll = productosListAll;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        productosListAll.clear();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        JSONObject content = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(new HttpGet(url));
            String respuesta = EntityUtils.toString(response.getEntity());
            content = new JSONObject(respuesta);
            if(content.getInt("status") == Utils.STATUS_CORRECT){
                JSONArray data = content.getJSONArray("data");
                JSONObject item;
                Producto producto;
                for(int i=0; i<data.length(); i++){
                    item = data.getJSONObject(i);
                    producto = new Producto();
                    producto.setId(item.getInt("id"));
                    producto.setDescripcion(item.getString("descripcion"));
                    producto.setMarca(item.getString("marca"));
                    producto.setExistencia(item.getInt("existencia"));
                    productosList.add(producto.getDescripcion());
                    productosListAll.add(producto);
                }
            }
        } catch (Exception e) {
            Log.d("[GET REQUEST]", "Network exception", e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}