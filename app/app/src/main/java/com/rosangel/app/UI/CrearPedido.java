package com.rosangel.app.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rosangel.R;
import com.rosangel.app.Modelos.Cliente;
import com.rosangel.app.Modelos.Pedido;
import com.rosangel.app.Modelos.Producto;
import com.rosangel.app.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class CrearPedido extends AppCompatActivity {
    SpinnerDialog SpProductos;
    SpinnerDialog SpClientes;
    public ArrayList<String> productosList = new ArrayList<>();
    public ArrayList<Producto> productosListAll = new ArrayList<>();
    public ArrayList<String> clientesList = new ArrayList<>();
    public ArrayList<Cliente> clientesListAll = new ArrayList<>();
    public Button buttonProducto;
    public Button buttonCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        buttonProducto = findViewById(R.id.editProducto);
        buttonCliente = findViewById(R.id.editCliente);

        /**CONFIGURAR TOOLBAR SOBRE ACTIONBAR*/
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Utils.configurarToolbarSteps(this, Objects.requireNonNull(getSupportActionBar()), toolbar, getString(R.string.crear_pedido));

        SpProductos = new SpinnerDialog(CrearPedido.this, productosList, getString(R.string.seleccione_producto));
        SpClientes = new SpinnerDialog(CrearPedido.this, clientesList, getString(R.string.seleccione_cliente));
        SpProductos.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                buttonProducto.setText(productosListAll.get(position).getDescripcion());
            }
        });
        SpClientes.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                buttonCliente.setText(clientesListAll.get(position).getNombre());
            }
        });

        /** Listener para seleccionar producto **/
        buttonProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpProductos.showSpinerDialog();
            }
        });
        /** Listener para seleccionar cliente **/
        buttonCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpClientes.showSpinerDialog();
            }
        });

        /** Listener para botón agregar **/
        findViewById(R.id.btnAgregar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpCrearPedido(CrearPedido.this).execute();
            }
        });

        new HttpGetLlenarProductos().execute();
        new HttpGetLlenarClientes().execute();
    }

    public class HttpCrearPedido extends AsyncTask<Void, String, Void> {
        protected String url = Utils.URL_SERVER + "/pedidos/crear";
        protected Pedido pedido;
        private Context context;
        private AlertDialog.Builder builder;
        private AlertDialog alert;

        private TextView txt_detalle;
        private ImageView img_status;
        private TextView txt_estatus;
        private boolean resultTask = false;
        private String detalleError = "";

        public HttpCrearPedido(Context context){
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            EditText editEmpleado = findViewById(R.id.editEmpleado);
            EditText editDireccion = findViewById(R.id.editDireccion);
            EditText editTelefono = findViewById(R.id.editTelefono);
            EditText editObservaciones = findViewById(R.id.editObservaciones);

            pedido = new Pedido();
            pedido.setCliente(buttonCliente.getText().toString());
            pedido.setProducto(buttonProducto.getText().toString());
            pedido.setEmpleado(editEmpleado.getText().toString());
            pedido.setDireccion(editDireccion.getText().toString());
            pedido.setTelefono(editTelefono.getText().toString());
            pedido.setObservaciones(editObservaciones.getText().toString());

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
                JSONObject jsonResponse = new JSONObject(EntityUtils.toString(ent));
                int data = jsonResponse.getInt("status");
                if(data == Utils.STATUS_CORRECT){
                    resultTask = true;
                }else {
                    resultTask = false;
                }
                detalleError = jsonResponse.getString("message");
                Thread.sleep(1000);
                int x = 0;
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
            ProgressBar progressBar = alert.findViewById(R.id.progress_status);
            progressBar.setVisibility(View.INVISIBLE);
            ImageView img_status = alert.findViewById(R.id.img_status);

            if(resultTask){
                img_status.setImageResource(R.drawable.correcto);
                txt_estatus.setText(detalleError);
            }else{
                img_status.setImageResource(R.drawable.error);
                txt_estatus.setText(context.getString(R.string.pedido_incorrecto));
                txt_detalle.setText(detalleError);
                txt_detalle.setVisibility(View.VISIBLE);
            }
            img_status.setVisibility(View.VISIBLE);
            Log.d("BackThread", "Terminando");
        }
    }

    public class HttpGetLlenarProductos extends AsyncTask<Void, String, Void> {
        int ITEMS_COUNTS = 0;
        protected String url = Utils.URL_SERVER + "/productos";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            productosList.clear();
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
        }
    }
    public class HttpGetLlenarClientes extends AsyncTask<Void, String, Void> {
        int ITEMS_COUNTS = 0;
        protected String url = Utils.URL_SERVER + "/clientes";
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
        }
    }
}
