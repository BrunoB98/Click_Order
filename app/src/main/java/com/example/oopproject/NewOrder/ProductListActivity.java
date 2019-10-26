package com.example.oopproject.NewOrder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import android.text.method.CharacterPickerDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oopproject.AppCompatProjectOrder;
import com.example.oopproject.Dummy.*;
import com.example.oopproject.MainActivity;
import com.example.oopproject.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductListActivity extends AppCompatProjectOrder {
    private boolean mTwoPane;
    protected static List<Product> l = new ArrayList<Product>();
    Spinner spin;
    ImageButton goSearch;
    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);


        spin = findViewById(R.id.spin_categ);
        goSearch = findViewById(R.id.go_search);
       // setSupportActionBar(spin);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dbManager.viewCategory());
        spin.setAdapter(adapter);
        /* funzione che cambia la visualizzazione in base alla grandezza del tablet */
        if (findViewById(R.id.product_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        recyclerView = findViewById(R.id.product_list);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);
    }

    public void onBackPressed() {
        AlertDialog.Builder alertDialog;
        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.attention);
        alertDialog.setMessage(R.string.conf_delete_ord);
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), R.string.order_canceled, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                order.clear();
            }
        });
        alertDialog.setNegativeButton(R.string.CANCEL, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });
        alertDialog.show();
    }

    public void goSearch(View view) {
        setupRecyclerView(recyclerView);
    }

    public void ShowOrder(View view) {
        Intent i = new Intent(this, activity_order.class);
        startActivity(i);
    }

    /* Funzione per settare un nuovo Adapter utile per visualizzare gli elementi */
    public void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        l = dbManager.viewProducts(spin.getSelectedItem().toString());
        adapter = new SimpleItemRecyclerViewAdapter(this, l, mTwoPane);
        recyclerView.setAdapter(adapter);
    }




    /* Nested static class to manage the RecyclerView */
    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ProductListActivity mParentActivity;
        private final List<Product> mValues;
        private final boolean mTwoPane;

        private final View.OnClickListener addToOrder = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product(l.get((int)view.getTag()));
                order.add(product);
                Toast.makeText(view.getContext(), "Added to order", Toast.LENGTH_SHORT).show();
            }
        };

        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product prod = (Product) view.getTag();
                /* si verifica se lo schermo è abbastanza grande oppure no.
                Se nTwoPane è vero (schermo grande) allora

                Se nTwoPane è falso (schermo normale) allora passa all'activity ProductDetailActivity mandandogli,  tramite un Intent ,
                 una stringa prelevata dal Fragment ProductDetailFragment e un seriale.
                All'activity ProductDetailActivity vengono mandati ARG_ITEM_ID (definito nel fragment) e l'id del prodotto
                  In sostanza quando avviene un click su un prodotto
                 si passa all'activity che mostra i dettagli di quel prodotto.
                 */
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putInt(ProductDetailFragment.ARG_ITEM_ID, prod.id);
                    ProductDetailFragment fragment = new ProductDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.product_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra(ProductDetailFragment.ARG_ITEM_ID, prod.id);
                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ProductListActivity parent,
                                      List<Product> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.product_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final Product p = mValues.get(position);
            holder.mContentView.setText(p.getName());
            holder.mPriceView.setText(p.getPrice().toString() + "$");
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
            holder.bt_add.setTag(position);
            holder.bt_add.setOnClickListener(addToOrder);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mContentView;
            final TextView mPriceView;
            Button bt_add;

            ViewHolder(View view) {
                super(view);
                mContentView = (TextView) view.findViewById(R.id.prod_name);
                mPriceView = (TextView) view.findViewById(R.id.price);
                bt_add = (Button) view.findViewById(R.id.Add_button);
            }
        }
    }
}
