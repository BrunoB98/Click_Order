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
                .inflate(R.layout.product_order_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Product p = mValues.get(position);
        final List<String> ing = new ArrayList<String>(dbManager.viewProductDetails(p));
        order.list.get(position).setProductIngredients(ing);
        final String str[] = new String[ing.size()];
        final boolean[] checked = new boolean[ing.size()];
        for (int i=0; i<ing.size(); i++){
            checked[i]=true;
            str[i]= (String) ing.get(i);
        }

        holder.mContentView.setText(p.getName());
        holder.mPriceView.setText(p.getPrice().toString() + "$");
        holder.itemView.setTag(mValues.get(position));
        holder.bt.setTag(position);
        holder.bt.setOnClickListener(remove);
        holder.bt_modify.setTag(position);
        holder.bt_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View vi = v;
                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
               // Integer dim = order.list.get(position).ingredients.size();
                // Boolean array for initial selected items

                // Set multiple choice items for alert dialog
                /*
                    AlertDialog.Builder setMultiChoiceItems(CharSequence[] items, boolean[]
                    checkedItems, DialogInterface.OnMultiChoiceClickListener listener)
                        Set a list of items to be displayed in the dialog as the content,
                        you will be notified of the selected item via the supplied listener.
                 */
                /*
                    DialogInterface.OnMultiChoiceClickListener
                    public abstract void onClick (DialogInterface dialog, int which, boolean isChecked)

                        This method will be invoked when an item in the dialog is clicked.

                        Parameters
                        dialog The dialog where the selection was made.
                        which The position of the item in the list that was clicked.
                        isChecked True if the click checked the item, else false.
                 */

                builder.setMultiChoiceItems(str, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        // Update the current focused item's checked status
                        checked[which] = isChecked;
                        // Get the current focused item
                       // String currentItem = ing.get(which);

                    }
                });

                // Specify the dialog is not cancelable
                builder.setCancelable(false);

                // Set a title for alert dialog
                builder.setTitle(R.string.modify_ing);

                // Set the positive/yes button click listener
                builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when click positive button
                        for(int i = 0; i < ing.size(); i++) {
                            if(checked[i] == false) {
                                ing.remove(i);
                              /*  String s = p.ingredients.get(i).toString();
                                order.list.get((int) vi.getTag()).removeIngredient(s);*/
                              //  System.out.println("prodotto: " + order.list.get((int) vi.getTag()).toString());
                            }
                        }
                        order.list.get(position).setProductIngredients(ing);
                        order.printOrder();
                    }

                });

                // Set the negative/no button click listener
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when click the negative button
                    }
                });

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();
            }
        });


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