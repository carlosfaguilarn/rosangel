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
import com.rosangel.app.Modelos.Producto;

import java.util.ArrayList;

public class ProductoAdapter extends ArrayAdapter {
    private Activity context;
    private ArrayList<Producto> datos;

    public ProductoAdapter(Activity context, ArrayList<Producto> datos) {
        super(context, R.layout.activity_productos, datos);
        this.context = context;
        this.datos = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.item_producto, null);

        TextView text_producto = item.findViewById(R.id.textDescripcion);
        text_producto.setText(datos.get(position).getDescripcion());

        TextView text_id = item.findViewById(R.id.textID);
        text_id.setText(String.valueOf(datos.get(position).getId()));

        TextView text_marca = item.findViewById(R.id.textMarca);
        text_marca.setText(datos.get(position).getMarca());

        TextView text_exist = item.findViewById(R.id.textExist);
        text_exist.setText(String.valueOf(datos.get(position).getExistencia()));

        return item;
    }
}
