package com.rosangel.ui.Historial;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.rosangel.R;
import com.rosangel.app.Adaptadores.PedidoAdapter;
import com.rosangel.app.Modelos.Pedido;
import com.rosangel.app.UI.ConsultarPedidos;
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

public class HistorialPedidosFragment extends Fragment {
    private ListView listViewPedidos;
    private ArrayAdapter adapterPedidos;
    private ArrayList<Pedido> pedidosList = new ArrayList<>();
    private ArrayList<Pedido> pedidosListAll;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_consultar_pedidos, container, false);

        listViewPedidos = root.findViewById(R.id.listaPedidos);
        adapterPedidos = new PedidoAdapter(getActivity(), pedidosList);
        listViewPedidos.setAdapter(adapterPedidos);

        new ConsultarPedidos.HttpGetLlenarPedidos().execute();
        return root;
    }

    /*
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
        }*/
    }
