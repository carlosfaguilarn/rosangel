package com.rosangel.app.Adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rosangel.R;

public class PedidoAdapter extends ArrayAdapter {
    private Activity context;
    private ArrayList<Pedido2> datos;

    public PedidoAdapter(Activity context, ArrayList<Pedido> datos) {
        super(context, R.layout.activity_consultar_pedido, datos);
        this.context = context;
        this.datos = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.item_pedido, null);

        TextView text_status = item.findViewById(R.id.txtEstatus);
        ImageView img_status = item.findViewById(R.id.imgPedido);
        LinearLayout layout_status = item.findViewById(R.id.layout_estatus);

        int iconStatus = R.drawable.ic_programada_status;
        int colorStatus = context.getResources().getColor(R.color.colorProgramado);;


        img_status.setImageResource(iconStatus);
        text_status.setTextColor(colorStatus);

        text_status.setText(datos.get(position).getNombreEstatus());

        TextView text_fecha = item.findViewById(R.id.txtFechaProgramada);
        text_fecha.setText(ComercialUtils.dateToString(datos.get(position).getFecha(), "dd/MM/yyyy"));

        TextView text_producto = item.findViewById(R.id.txtProducto);
        text_producto.setText(datos.get(position).getProducto());

        TextView text_volumen = item.findViewById(R.id.editVolumen);
        text_volumen.setText(String.valueOf(datos.get(position).getVolumen()));

        TextView text_serie = item.findViewById(R.id.txtSerie);
        text_serie.setText(datos.get(position).getNombreEstacion());

        return item;
    }
}
