package com.rosangel.ui.CrearPedido;

import android.os.AsyncTask;
import android.util.Log;
import com.rosangel.app.Modelos.Cliente;
import com.rosangel.app.Utils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HttpGetClientes extends AsyncTask<Void, String, Void> {
    protected String url = Utils.URL_SERVER + "/clientes";
    private ArrayList<String> clientesList;
    private ArrayList<Cliente> clientesListAll;

    HttpGetClientes(ArrayList<String> clientesList, ArrayList<Cliente> clientesListAll) {
        this.clientesList = clientesList;
        this.clientesListAll = clientesListAll;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        clientesList.clear();
        clientesListAll.clear();
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
                Cliente cliente;
                for(int i=0; i<data.length(); i++){
                    item = data.getJSONObject(i);
                    cliente = new Cliente();
                    cliente.setId(item.getInt("id"));
                    cliente.setNombre(item.getString("nombre"));
                    clientesList.add(cliente.getNombre());
                    clientesListAll.add(cliente);
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
