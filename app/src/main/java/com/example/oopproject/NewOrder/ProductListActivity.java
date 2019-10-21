package com.example.oopproject.NewOrder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import android.text.method.CharacterPickerDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oopproject.AppCompatProjectOrder;
import com.example.oopproject.Dummy.*;
import com.example.oopproject.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductListActivity extends AppCompatProjectOrder {
    private boolean mTwoPane;
    protected static List<Product> l = new ArrayList<Product>();
    Spinner spin;
    ImageButton goSearch;
    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);


        spin = findViewById(R.id.spin_categ);
        goSearch = findViewById(R.id.go_search);
       // setSupportActionBar(spin);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dbManager.viewCategory());
        spin.setAdapter(adapter);
        /* funzione che cambia la visualizzazione in base alla grandezza del tablet */
        if (findViewById(R.id.product_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        recyclerView = findViewById(R.id.product_list);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);
    }

    public void goSearch(View view) {
        setupRecyclerView(recyclerView);
    }

    public void ShowOrder(View view) {
        Intent i = new Intent(this, activity_order.class);
        startActivity(i);
    }

    /* Funzione per settare un nuovo Adapter utile per visualizzare gli elementi */
    public void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        l = dbManager.viewProducts(spin.getSelectedItem().toString());
        adapter = new SimpleItemRecyclerViewAdapter(this, l, mTwoPane);
        recyclerView.setAdapter(adapter);
    }




    /* Nested static class to manage the RecyclerView */
    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ProductListActivity mParentActivity;
        private final List<Product> mValues;
        private final boolean mTwoPane;

        private final View.OnClickListener addToOrder = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                order.add(l.get((int)view.getTag()));
                Toast.makeText(view.getContext(), "Added to order", Toast.LENGTH_SHORT).show();
            }
        };

        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product prod = (Product) view.getTag();
                /* si verifica se lo schermo è abbastanza grande oppure no.
                Se nTwoPane è vero (schermo grande) allora

                Se nTwoPane è falso (schermo normale) allora passa all'activity ProductDetailActivity mandandogli,  tramite un Intent ,
                 una stringa prelevata dal Fragment ProductDetailFragment e un seriale.
                All'activity ProductDetailActivity vengono mandati ARG_ITEM_ID (definito nel fragment) e l'id del prodotto
                  In sostanza quando avviene un click su un prodotto
                 si passa all'activity che mostra i dettagli di quel prodotto.
                 */
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putInt(ProductDetailFragment.ARG_ITEM_ID, prod.id);
                    ProductDetailFragment fragment = new ProductDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.product_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra(ProductDetailFragment.ARG_ITEM_ID, prod.id);
                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ProductListActivity parent,
                                      List<Product> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.product_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final Product p = mValues.get(position);
            p.setProductIngredients(dbManager.viewProductDetails(p));
            holder.mContentView.setText(p.getName());
            holder.mPriceView.setText(p.getPrice().toString() + "$");
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
            holder.bt_add.setTag(position);
            holder.bt_add.setOnClickListener(addToOrder);
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
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when click the negative button
                        }
                    });

                    // Set the neutral/cancel button click listener
                    builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when click the neutral button
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
            Button bt_add;
            Button bt_modify;



            ViewHolder(View view) {
                super(view);
                mContentView = (TextView) view.findViewById(R.id.prod_name);
                mPriceView = (TextView) view.findViewById(R.id.price);
                bt_add = (Button) view.findViewById(R.id.Add_button);
                bt_modify = (Button) view.findViewById(R.id.Modify);
            }
        }
    }
}
