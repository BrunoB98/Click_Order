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


    public static View.OnClickListener remove = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            order.remove(order.list.get((int)view.getTag()));
            adapter.notifyDataSetChanged();
            tot.setText(order.total.toString() + " $");
            Toast.makeText(view.getContext(), "Removed to order", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_content, parent, false);
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
       /* for(int i = 0; i<list.size(); i++ ) {
            if (p.ingredients.contains(list.get(i))) {
                strOK = list.get(i) + ", "
            }
            else {
                strNO = strNO + list.get(i)
            }
        }
        holder.modification.setText();*/
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
            mContentView = (TextView) view.findViewById(R.id.prod_name);
            mPriceView = (TextView) view.findViewById(R.id.price);
            modification = (TextView) view.findViewById(R.id.modification);
        }

    }
}
