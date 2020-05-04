package com.rosangel.ui.CrearPedido;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.rosangel.R;
import com.rosangel.app.Modelos.Cliente;
import com.rosangel.app.Modelos.Pedido;
import com.rosangel.app.Modelos.Producto;
import com.rosangel.app.Utils;

import java.util.ArrayList;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class CrearPedidoFragment extends Fragment {
    private SpinnerDialog SpProductos;
    private SpinnerDialog SpClientes;

    public static ArrayList<String> productosList = new ArrayList<>();
    public static ArrayList<Producto> productosListAll = new ArrayList<>();

    private ArrayList<String> clientesList = new ArrayList<>();
    private ArrayList<Cliente> clientesListAll = new ArrayList<>();

    private Button buttonProducto;
    private Button buttonCliente;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.activity_crear_pedido, container, false);
        buttonProducto = root.findViewById(R.id.editProducto);
        buttonCliente = root.findViewById(R.id.editCliente);

        SpProductos = new SpinnerDialog(getActivity(), productosList, getString(R.string.seleccione_producto));
        SpClientes = new SpinnerDialog(getActivity(), clientesList, getString(R.string.seleccione_cliente));
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

        /** Listener para botón agregar**/
        root.findViewById(R.id.btnAgregar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editEmpleado = root.findViewById(R.id.editEmpleado);
                EditText editDireccion = root.findViewById(R.id.editDireccion);
                EditText editTelefono = root.findViewById(R.id.editTelefono);
                EditText editObservaciones = root.findViewById(R.id.editObservaciones);

                Pedido pedido = new Pedido();
                pedido.setCliente(buttonCliente.getText().toString());
                pedido.setProducto(buttonProducto.getText().toString());
                pedido.setEmpleado(editEmpleado.getText().toString());
                pedido.setDireccion(editDireccion.getText().toString());
                pedido.setTelefono(editTelefono.getText().toString());
                pedido.setObservaciones(editObservaciones.getText().toString());
                new HttpPostCrearPedido(getContext(), root, pedido).execute();
            }
        });

        EditText editText = root.findViewById(R.id.editEmpleado);
        editText.setText(Utils.getPreferences(getActivity(), "username"));
        new HttpGetClientes(clientesList, clientesListAll).execute();
        new HttpGetProductos(productosList, productosListAll).execute();
        return root;
    }

    /*
    public class HttpCrearPedido extends AsyncTask<Void, String, Void> {
        private String url = Utils.URL_SERVER + "/pedidos/crear";
        private Pedido pedido;
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
    }*/
}
