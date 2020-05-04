package com.rosangel.app.Adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rosangel.R;
import com.rosangel.app.Modelos.Pedido;
import com.rosangel.app.Utils;

import java.util.ArrayList;

public class PedidoAdapter extends ArrayAdapter {
    private Activity context;
    private ArrayList<Pedido> datos;

    public PedidoAdapter(Activity context, ArrayList<Pedido> datos) {
        super(context, R.layout.activity_consultar_pedidos, datos);
        this.context = context;
        this.datos = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.item_pedido, null);

        TextView text_producto = item.findViewById(R.id.txtProducto);
        text_producto.setText(datos.get(position).getProducto());

        TextView text_cliente = item.findViewById(R.id.txtCliente);
        text_cliente.setText(String.valueOf(datos.get(position).getCliente()));

        TextView text_fecha = item.findViewById(R.id.txtFecha);
        text_fecha.setText(Utils.dateToString(datos.get(position).getFecha(), "dd/MM/yyyy"));

        TextView text_status = item.findViewById(R.id.txtEstatus);
        text_status.setText(datos.get(position).getEstatus());

        return item;
    }
}
