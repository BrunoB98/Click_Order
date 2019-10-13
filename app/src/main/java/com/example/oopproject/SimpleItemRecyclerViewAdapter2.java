package com.example.oopproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oopproject.Dummy.Product;

import java.util.List;

import static com.example.oopproject.AppCompatProjectOrder.order;
import static com.example.oopproject.activity_order.adapter;
import static com.example.oopproject.activity_order.tot;

public class SimpleItemRecyclerViewAdapter2 extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter2.ViewHolder> {

    private List<Product> mValues;


    SimpleItemRecyclerViewAdapter2(List<Product> items) {
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