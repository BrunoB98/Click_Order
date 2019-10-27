package com.example.oopproject.RegisterOrder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.oopproject.Dummy.Order;
import com.example.oopproject.Dummy.Product;
import com.example.oopproject.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import static com.example.oopproject.AppCompatProject.dbManager;

public class SimpleAdapterHistoryOrder extends RecyclerView.Adapter<SimpleAdapterHistoryOrder.ViewHolder> {

    private List<Order> mValues;
    DecimalFormat df = new DecimalFormat("##.##");

    SimpleAdapterHistoryOrder(List<Order> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_order_line, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Order o = mValues.get(position);
        o.printOrder();

        holder.id_order.setText(Integer.toString(o.getId()));
        holder.date_order.setText(o.getMydate());
        holder.total_order.setText(df.format(o.getTotal()) + " €");
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView id_order;
        final TextView date_order;
        final TextView total_order;


        ViewHolder(View view) {
            super(view);
            id_order = (TextView) view.findViewById(R.id.id_order);
            date_order = (TextView) view.findViewById(R.id.date_order);
            total_order = (TextView) view.findViewById(R.id.tot_order);
        }

    }
}