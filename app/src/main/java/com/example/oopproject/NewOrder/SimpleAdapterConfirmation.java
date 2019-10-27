package com.example.oopproject.NewOrder;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.oopproject.Dummy.Product;
import com.example.oopproject.R;

import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context.*;
import static com.example.oopproject.AppCompatProject.dbManager;
import static com.example.oopproject.AppCompatProjectOrder.order;
import static com.example.oopproject.NewOrder.activity_order.adapter;
import static com.example.oopproject.NewOrder.activity_order.tot;

public class SimpleAdapterConfirmation extends RecyclerView.Adapter<SimpleAdapterConfirmation.ViewHolder> {

    private List<Product> mValues;


    SimpleAdapterConfirmation(List<Product> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.confirmation_order_line, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product p = mValues.get(position);
        holder.mContentView.setText(p.getName());
        holder.mPriceView.setText(p.getPrice().toString() + "€");
        holder.itemView.setTag(mValues.get(position));
        List<String> list = dbManager.viewProductDetails(p);
        for(int i = 0; i < p.ingredients.size(); i++ ) {
            if (p.checked[i] == true) {
                holder.s_ok = holder.s_ok + list.get(i) + ", ";
            }
            else {
                holder.s_no = holder.s_no + list.get(i) +  ", ";
            }
        }
        holder.modification.setText(holder.s_ok + '\n' + holder.s_no);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mContentView;
        final TextView mPriceView;
        final TextView modification;
        String s_no, s_ok;



        ViewHolder(View view) {
            super(view);
            mContentView = (TextView) view.findViewById(R.id.confirm_pname);
            mPriceView = (TextView) view.findViewById(R.id.confirm_pprice);
            modification = (TextView) view.findViewById(R.id.modification);
            s_ok = view.getContext().getString(R.string.str_ok);
            s_no = view.getContext().getString(R.string.str_no);

        }

    }
}
