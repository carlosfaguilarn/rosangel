package com.rosangel.app.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rosangel.R;
import com.rosangel.app.Adaptadores.PedidoAdapter;
import com.rosangel.app.Modelos.Pedido;
import com.rosangel.app.Utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class ConsultarPedidos extends AppCompatActivity {
    private ListView listViewPedidos;
    private ArrayAdapter adapterPedidos;
    private ArrayList<Pedido> pedidosList = new ArrayList<>();
    private ArrayList<Pedido> pedidosListAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_pedidos);

        /**CONFIGURAR TOOLBAR SOBRE ACTIONBAR*/
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Utils.configurarToolbarSteps(this, Objects.requireNonNull(getSupportActionBar()), toolbar, getString(R.string.consultar_pedidos));

        listViewPedidos = findViewById(R.id.listaPedidos);
        adapterPedidos = new PedidoAdapter(this, pedidosList);
        listViewPedidos.setAdapter(adapterPedidos);

        new HttpGetLlenarPedidos().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public class HttpGetLlenarPedidos extends AsyncTask<Void, String, Void> {
        int ITEMS_COUNTS = 0;
        protected String url = Utils.URL_SERVER + "/pedidos";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pedidosList.clear();
            adapterPedidos.notifyDataSetChanged();
            findViewById(R.id.progress_pedidos).setVisibility(View.VISIBLE);
            findViewById(R.id.cont_no_pedidos).setVisibility(View.INVISIBLE);
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
                    Pedido pedido;
                    for(int i=0; i<data.length(); i++){
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
                    ITEMS_COUNTS = data.length();
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

            findViewById(R.id.progress_pedidos).setVisibility(View.INVISIBLE);

            if(ITEMS_COUNTS == 0){
                findViewById(R.id.cont_no_pedidos).setVisibility(View.VISIBLE);
            }else{
                findViewById(R.id.cont_no_pedidos).setVisibility(View.INVISIBLE);
            }
        }
    }

}
