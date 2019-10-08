package com.example.oopproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oopproject.Dummy.*;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Products. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ProductDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ProductListActivity extends AppCompatActivity {
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        /* funzione che cambia la visualizzazione in base alla grandezza del tablet */
        if (findViewById(R.id.product_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.product_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    public void AddToOrder(View view) {
        Toast.makeText(getApplicationContext(), "Added to order", Toast.LENGTH_SHORT).show();
    }

    public void ShowOrder(View view) {
        Intent i = new Intent(this, activity_order.class);
        startActivity(i);
    }

    /* Funzione per settare un nuovo Adapter utile per visualizzare gli elementi */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        Bundle bundle = getIntent().getExtras();
        List<Product> l = new ArrayList<Product>();
        String str;
        switch (bundle.getInt("categoria")) {
            case R.id.antipasti:
                str = "antipasti";
                break;
            case R.id.primi:
                str = "primi";
                break;
            case R.id.secondi:
                str = "secondi";
                break;
            case R.id.pizze:
                str = "pizze";
                break;
            case R.id.bevande:
                str = "bevande";
                break;
            case R.id.dolci:
                str = "dolci";
                break;
                default:
                    str = "null";
        }

        for (Product pr : LogActivity.ITEMS) {
            if (pr.category.equals(str)) {
                l.add(pr);
            }
        }
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, l, mTwoPane));
    }

    /* Nested static class to manage the RecyclerView */
    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ProductListActivity mParentActivity;
        private final List<Product> mValues;
        private final boolean mTwoPane;
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
            Product p = mValues.get(position);
            holder.mContentView.setText(p.getName());
            holder.mPriceView.setText(p.getPrice().toString() + "$");
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);

        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mContentView;
            final TextView mPriceView;



            ViewHolder(View view) {
                super(view);
                mContentView = (TextView) view.findViewById(R.id.prod_name);
                mPriceView = (TextView) view.findViewById(R.id.price);
            }
        }
    }
}
