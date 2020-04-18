package com.rosangel.app.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rosangel.R;
import com.rosangel.app.Adaptadores.ProductoAdapter;
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
import java.util.Objects;

public class Productos extends AppCompatActivity {
    private ListView listViewProductos;
    private ArrayAdapter adapterProductos;
    private ArrayList<Producto> productosList = new ArrayList<>();
    private ArrayList<Producto> productosListAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        /**CONFIGURAR TOOLBAR SOBRE ACTIONBAR*/
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Utils.configurarToolbarSteps(this, Objects.requireNonNull(getSupportActionBar()), toolbar, getString(R.string.productos));

        listViewProductos = findViewById(R.id.listaProductos);
        adapterProductos = new ProductoAdapter(this, productosList);
        listViewProductos.setAdapter(adapterProductos);

        new HttpGetLlenarProductos().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_busqueda, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint(getString(R.string.filtro_busca));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                productosList.clear();
                for (Producto producto: productosListAll) {
                    //Buscar por estatus, estación o producto
                    if (producto.getDescripcion().toLowerCase().contains(newText.toLowerCase()) ||
                            producto.getMarca().toLowerCase().contains(newText.toLowerCase()) ||
                            String.valueOf(producto.getId()).contains(newText)
                    ){
                        productosList.add(producto);
                    }
                }
                adapterProductos.notifyDataSetChanged();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public class HttpGetLlenarProductos extends AsyncTask<Void, String, Void> {
        int ITEMS_COUNTS = 0;
        protected String url = Utils.URL_SERVER + "/productos";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            productosList.clear();
            adapterProductos.notifyDataSetChanged();
            findViewById(R.id.progress_productos).setVisibility(View.VISIBLE);
            findViewById(R.id.cont_no_productos).setVisibility(View.INVISIBLE);
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
                        productosList.add(producto);
                    }
                    // Copia de todas las estaciones, para filtros
                    productosListAll = new ArrayList<>(productosList);
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
            adapterProductos.notifyDataSetChanged();

            findViewById(R.id.progress_productos).setVisibility(View.INVISIBLE);

            if(ITEMS_COUNTS == 0){
                findViewById(R.id.cont_no_productos).setVisibility(View.VISIBLE);
            }else{
                findViewById(R.id.cont_no_productos).setVisibility(View.INVISIBLE);
            }
        }
    }
}
