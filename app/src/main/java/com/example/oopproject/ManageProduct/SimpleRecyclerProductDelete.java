package com.example.oopproject.ManageProduct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.oopproject.Dummy.Product;
import com.example.oopproject.NewOrder.ProductDetailActivity;
import com.example.oopproject.NewOrder.ProductDetailFragment;
import com.example.oopproject.NewOrder.ProductListActivity;
import com.example.oopproject.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.oopproject.AppCompatProject.dbManager;
import static com.example.oopproject.ManageProduct.DeleteFragment.list;
import static com.example.oopproject.ManageProduct.DeleteFragment.adapter;



public class SimpleRecyclerProductDelete extends RecyclerView.Adapter<SimpleRecyclerProductDelete.ViewHolder> {

    private List<Product> mValues;


    SimpleRecyclerProductDelete(List<Product> items) {
        mValues = items;
    }


    public static View.OnClickListener deleteFromDatabase = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dbManager.deleteProduct(list.get((int)view.getTag()));
            list.remove((int)view.getTag());
            adapter.notifyDataSetChanged();
            Toast.makeText(view.getContext(), R.string.prod_removed, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public SimpleRecyclerProductDelete.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_delete_content, parent, false);
        return new SimpleRecyclerProductDelete.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleRecyclerProductDelete.ViewHolder holder, final int position) {
        Product p = mValues.get(position);
        holder.mContentView.setText(p.getName());
        holder.mPriceView.setText(p.getPrice().toString() + "$");
        holder.bt_delete.setTag(position);
        holder.bt_delete.setOnClickListener(deleteFromDatabase);
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mContentView;
        final TextView mPriceView;
        Button bt_delete;


        ViewHolder(View view) {
            super(view);
            mContentView = (TextView) view.findViewById(R.id.prod_name);
            mPriceView = (TextView) view.findViewById(R.id.price);
            bt_delete = (Button) view.findViewById(R.id.delete_prod_button);

        }

    }
}