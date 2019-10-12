package com.example.oopproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.*;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oopproject.Dummy.Product;

import java.util.ArrayList;
import java.util.List;

public class activity_order extends AppCompatProjectOrder {
    boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        TextView tot = findViewById(R.id.total);
        tot.setText(order.total.toString() + " $");
        View recyclerView = findViewById(R.id.summary_order);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    /* Funzione per settare un nuovo Adapter utile per visualizzare gli elementi */
    public void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new activity_order.SimpleItemRecyclerViewAdapter2(this, order.list, mTwoPane));
    }

    /* Nested static class to manage the RecyclerView */
    public static class SimpleItemRecyclerViewAdapter2
            extends RecyclerView.Adapter<activity_order.SimpleItemRecyclerViewAdapter2.ViewHolder> {

        private final activity_order mParentActivity;
        private final List<Product> mValues;
        private final boolean mTwoPane;

        private final View.OnClickListener remove = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // order.add(l.get((int)view.getTag()));
                Toast.makeText(view.getContext(), "Removed to order", Toast.LENGTH_SHORT).show();
            }
        };
        /*
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
        };*/

        SimpleItemRecyclerViewAdapter2(activity_order parent,
                                      List<Product> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public activity_order.SimpleItemRecyclerViewAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.product_list_content, parent, false);
            return new activity_order.SimpleItemRecyclerViewAdapter2.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final activity_order.SimpleItemRecyclerViewAdapter2.ViewHolder holder, int position) {
            Product p = mValues.get(position);
            holder.mContentView.setText(p.getName());
            holder.mPriceView.setText(p.getPrice().toString() + "$");
            holder.itemView.setTag(mValues.get(position));
            holder.bt.setTag(position);
            holder.bt.setOnClickListener(remove);

        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mContentView;
            final TextView mPriceView;
            Button bt;



            ViewHolder(View view) {
                super(view);
                mContentView = (TextView) view.findViewById(R.id.prod_name);
                mPriceView = (TextView) view.findViewById(R.id.price);
                bt = (Button) view.findViewById(R.id.Add_button);
                bt.setText("REMOVE");
            }
        }
    }

}
