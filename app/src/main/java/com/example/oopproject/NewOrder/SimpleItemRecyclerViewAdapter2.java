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

public class SimpleItemRecyclerViewAdapter2 extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter2.ViewHolder> {

    private List<Product> mValues;


    SimpleItemRecyclerViewAdapter2(List<Product> items) {
        mValues = items;
    }

    public static View.OnClickListener setModification = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final View vi = v;
            final Product p2 = order.list.get((int)vi.getTag());
            System.out.println("Questo è il prodotto: " + p2.toString());
            final boolean[] check = new boolean[p2.ingredients.size()];
            final String[] str = new String[p2.ingredients.size()];

            for (int i = 0; i < p2.ingredients.size(); i++) {
                check[i] = p2.checked[i];
                str[i] = p2.ingredients.get(i);
            }


            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setMultiChoiceItems(str, check, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    check[which] = isChecked;
                }
            });
            builder.setCancelable(false);
            builder.setTitle(R.string.modify_ing);
            builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do something when click positive button
                    for(int i = 0; i < p2.ingredients.size(); i++) {
                            order.list.get((int) vi.getTag()).checked[i] = check[i];
                        }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    };


    public static View.OnClickListener remove = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            order.remove(order.list.get((int)view.getTag()));
            adapter.notifyDataSetChanged();
            tot.setText(order.total.toString() + " $");
            Toast.makeText(view.getContext(), R.string.removed_to, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_order_content, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Product p = mValues.get(position);
        holder.mContentView.setText(p.getName());
        holder.mPriceView.setText(p.getPrice().toString() + "$");
        holder.itemView.setTag(mValues.get(position));
        holder.bt.setTag(position);
        holder.bt.setOnClickListener(remove);
        holder.bt_modify.setTag(position);
        holder.bt_modify.setOnClickListener(setModification);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mContentView;
        final TextView mPriceView;
        Button bt, bt_modify;


        ViewHolder(View view) {
            super(view);
            mContentView = (TextView) view.findViewById(R.id.prod_name_order);
            mPriceView = (TextView) view.findViewById(R.id.price_order);
            bt = (Button) view.findViewById(R.id.Add_button_order);
            bt_modify = view.findViewById(R.id.Modify_button_order);
        }

    }
}