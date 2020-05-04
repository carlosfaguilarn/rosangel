package com.rosangel.ui.Historial;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.rosangel.R;
import com.rosangel.app.Adaptadores.PedidoAdapter;
import com.rosangel.app.Modelos.Pedido;
import com.rosangel.app.Utils;

import java.util.ArrayList;

public class HistorialPedidosFragment extends Fragment {
    private SwipeMenuListView listViewPedidos;
    private PedidoAdapter adapterPedidos;
    private ArrayList<Pedido> pedidosList = new ArrayList<>();
    private ArrayList<Pedido> pedidosListAll;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_consultar_pedidos, container, false);

        listViewPedidos = root.findViewById(R.id.listaPedidos);
        adapterPedidos = new PedidoAdapter(getActivity(), pedidosList);
        listViewPedidos.setAdapter(adapterPedidos);


        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext());
                deleteItem.setBackground(R.color.colorBackConsultar);
                deleteItem.setWidth(100);
                deleteItem.setIcon(R.drawable.ic_delete);
                deleteItem.setTitle(getString(R.string.eliminar));
                deleteItem.setTitleSize(13);
                deleteItem.setTitleColor(Color.parseColor("#dc3545"));
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        listViewPedidos.setMenuCreator(creator);

        listViewPedidos.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        break;
                }
                // false : close the menu; true : not close the menu
                return true;
            }
        });


        new HttpGetPedidos(root, pedidosList, pedidosListAll, adapterPedidos).execute();
        return root;
    }
}
