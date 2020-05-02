package com.rosangel.ui.Historial;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.rosangel.R;
import com.rosangel.app.Modelos.Pedido;
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

public class HttpGetPedidos extends AsyncTask<Void, String, Void> {
    private String url = Utils.URL_SERVER + "/pedidos";
    private ArrayList<Pedido> pedidosList;
    private ArrayList<Pedido> pedidosListAll;
    private ArrayAdapter adapterPedidos;
    private int ITEMS_COUNTS = 0;
    private View view;

    public HttpGetPedidos(View view,
                          ArrayList<Pedido> pedidosList,
                          ArrayList<Pedido> pedidosListAll,
                          ArrayAdapter adapterPedidos) {
        this.pedidosList = pedidosList;
        this.pedidosListAll = pedidosListAll;
        this.adapterPedidos = adapterPedidos;
        this.view = view;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pedidosList.clear();
        adapterPedidos.notifyDataSetChanged();
        view.findViewById(R.id.progress_pedidos).setVisibility(View.VISIBLE);
        view.findViewById(R.id.cont_no_pedidos).setVisibility(View.INVISIBLE);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        JSONObject content = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(new HttpGet(url));
            String respuesta = EntityUtils.toString(response.getEntity());
            content = new JSONObject(respuesta);
            if (content.getInt("status") == Utils.STATUS_CORRECT) {
                JSONArray data = content.getJSONArray("data");
                JSONObject item;
                Pedido pedido;
                ITEMS_COUNTS = data.length();
                for (int i = 0; i < data.length(); i++) {
                    item = data.getJSONObject(i);
                    pedido = new Pedido();
                    pedido.setId(item.getInt("id"));
                    pedido.setProducto(item.getString("producto"));
                    pedido.setCliente(item.getString("cliente"));
                    pedido.setTelefono(item.getString("telefono"));
                    pedido.setDireccion(item.getString("direccion"));
                    pedido.setObservaciones(item.getString("observaciones"));
                    pedido.setFecha(Utils.stringToDate(item.getString("created_at"), "yy-MM-dd"));
                    pedido.setEstatus(item.getString("estatus"));
                    pedidosList.add(pedido);
                }
                // Copia de todas las estaciones, para filtros
                pedidosListAll = new ArrayList<>(pedidosList);
            }
        } catch (Exception e) {
            Log.d("[GET REQUEST]", "Network exception", e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        adapterPedidos.notifyDataSetChanged();

        view.findViewById(R.id.progress_pedidos).setVisibility(View.INVISIBLE);
        if (ITEMS_COUNTS == 0) {
            view.findViewById(R.id.cont_no_pedidos).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.cont_no_pedidos).setVisibility(View.INVISIBLE);
        }
    }
}