package com.example.oopproject.ManageProduct;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.oopproject.Dummy.Product;
import com.example.oopproject.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.oopproject.Dummy.Product;
import com.example.oopproject.R;

import java.util.List;

import java.util.List;

public class SimpleRecyclerViewListAdapter extends RecyclerView.Adapter<SimpleRecyclerViewListAdapter.ViewHolder> {
        private List<Product> mValues;


        SimpleRecyclerViewListAdapter(List<Product> items) {
            mValues = items;
        }

    @Override
        public SimpleRecyclerViewListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_view_list, parent, false);
            return new SimpleRecyclerViewListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SimpleRecyclerViewListAdapter.ViewHolder holder, int position) {
            Product p = mValues.get(position);
            holder.mContentView.setText(p.getName());
            holder.mPriceView.setText(p.getPrice().toString() + "$");
            holder.mCategView.setText(p.getCategory().toString());
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mContentView;
            final TextView mPriceView;
            final TextView mCategView;


            ViewHolder(View view) {
                super(view);
                mContentView = (TextView) view.findViewById(R.id.view_prod_name);
                mPriceView = (TextView) view.findViewById(R.id.view_prod_price);
                mCategView = (TextView) view.findViewById(R.id.view_prod_categ);
            }

        }
}

