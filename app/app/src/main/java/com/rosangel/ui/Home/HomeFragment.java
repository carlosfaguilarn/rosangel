package com.rosangel.ui.Home;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.rosangel.R;
import com.rosangel.app.Modelos.Dashboard;
import com.rosangel.app.Utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HomeFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        new HttpGetDashboard(root).execute();
        return root;
    }

    public class HttpGetDashboard extends AsyncTask<Void, String, Void> {
        private String url = Utils.URL_SERVER + "/dashboard";
        private Dashboard dashboard = new Dashboard();
        private View view;

        public HttpGetDashboard(View view) {
            this.view = view;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            JSONObject content = null;
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(new HttpGet(url));
                String respuesta = EntityUtils.toString(response.getEntity());
                content = new JSONObject(respuesta);
                if(content.getString("status").equals("ok")){
                    JSONObject data = content.getJSONObject("data");
                    dashboard.setPedidosTotales(data.getInt("pedidos_totales"));
                    dashboard.setPedidosPendientes(data.getInt("pedidos_pendientes"));
                    dashboard.setVentas(data.getInt("ventas"));
                    dashboard.setGanancias(data.getInt("ganancias"));
                    dashboard.setClientes(data.getInt("clientes"));
                    dashboard.setProductos(data.getInt("productos"));
                }
            } catch (Exception e) {
                Log.d("[GET REQUEST]", "Network exception", e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView textTotales = view.findViewById(R.id.textTotales);
            TextView textPendientes = view.findViewById(R.id.textPendientes);
            TextView textVentas = view.findViewById(R.id.textVentas);
            TextView textGanancias = view.findViewById(R.id.textGanancias);
            TextView textClientes = view.findViewById(R.id.textClientes);
            TextView textEmpleados = view.findViewById(R.id.textEmpleados);

            textTotales.setText(String.valueOf(dashboard.getPedidosTotales()));
            textPendientes.setText(String.valueOf(dashboard.getPedidosPendientes()));
            textVentas.setText("$"+(dashboard.getVentas()));
            textGanancias.setText("$"+(dashboard.getGanancias()));
            textClientes.setText(String.valueOf(dashboard.getClientes()));
            textEmpleados.setText(String.valueOf(dashboard.getProductos()));
        }
    }
}