package com.example.oopproject.NewOrder;


import android.app.AlertDialog;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        holder.mPriceView.setText(p.getPrice().toString() + "$");
        holder.itemView.setTag(mValues.get(position));
        String strOK = "Ingredienti presenti: ", strNO = "Senza: ";
        List<String> list = dbManager.viewProductDetails(p);
        for(int i = 0; i < p.ingredients.size(); i++ ) {
            if (p.checked[i] == true) {
                strOK = strOK + list.get(i) + ", ";
            }
            else {
                strNO = strNO + list.get(i) +  ", ";
            }
        }
        holder.modification.setText(strOK + '\n' + strNO);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mContentView;
        final TextView mPriceView;
        final TextView modification;


        ViewHolder(View view) {
            super(view);
            mContentView = (TextView) view.findViewById(R.id.confirm_pname);
            mPriceView = (TextView) view.findViewById(R.id.confirm_pprice);
            modification = (TextView) view.findViewById(R.id.modification);
        }

    }
}
