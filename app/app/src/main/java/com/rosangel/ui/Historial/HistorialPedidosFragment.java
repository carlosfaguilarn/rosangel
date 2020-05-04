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
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(getContext());
                // set item background
                openItem.setBackground(R.color.colorBackConsultar);
                // set item width
                openItem.setWidth(150);
                // set item title
                //openItem.setTitle("Open");
                openItem.setIcon(R.drawable.ic_delete);
                // set item title fontsize
                openItem.setTitleSize(38);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                /* create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_close);
                // add to menu
                menu.addMenuItem(deleteItem);*/
            }
        };

        // set creator
        listViewPedidos.setMenuCreator(creator);

        listViewPedidos.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Utils.showMessageShort(getContext(), "opcion uno de: " + index);
                        break;
                    case 1:
                        Utils.showMessageShort(getContext(), "opcion dos de: " + index);
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });


        new HttpGetPedidos(root, pedidosList, pedidosListAll, adapterPedidos).execute();
        return root;
    }
}
