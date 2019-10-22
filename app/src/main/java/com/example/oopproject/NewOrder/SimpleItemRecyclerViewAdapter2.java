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

import java.util.Arrays;
import java.util.List;

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
                .inflate(R.layout.product_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product p = mValues.get(position);
        holder.mContentView.setText(p.getName());
        holder.mPriceView.setText(p.getPrice().toString() + "$");
        holder.itemView.setTag(mValues.get(position));
        holder.bt.setTag(position);
        holder.bt.setOnClickListener(remove);
        holder.bt_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                final String str[] = new String[p.ingredients.size()];
                // Boolean array for initial selected items
                final boolean[] checked = new boolean[p.ingredients.size()];
                for (int i=0; i<p.ingredients.size(); i++){
                    checked[i]=true;
                    str[i]= (String) p.ingredients.get(i);
                }
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
                        String currentItem = p.ingredients.get(which);

                    }
                });

                // Specify the dialog is not cancelable
                builder.setCancelable(false);

                // Set a title for alert dialog
                builder.setTitle("Your preferred colors?");

                // Set the positive/yes button click listener
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when click positive button
                           /* tv.setText("Your preferred colors..... \n");
                            for (int i = 0; i<checkedColors.length; i++){
                                boolean checked = checkedColors[i];
                                if (checked) {
                                    tv.setText(tv.getText() + colorsList.get(i) + "\n");
                                }
                            }*/
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
            mContentView = (TextView) view.findViewById(R.id.prod_name);
            mPriceView = (TextView) view.findViewById(R.id.price);
            bt = (Button) view.findViewById(R.id.Add_button);
            bt.setText("REMOVE");
            bt_modify = view.findViewById(R.id.Modify);
        }

    }
}