package com.example.oopproject.ManageCateg;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oopproject.R;

import static com.example.oopproject.AppCompatProject.dbManager;
public class DeleteCategoryFragment extends Fragment {

    Button delete_category;
    TextView category_sel;
    Spinner categories;
    ArrayAdapter<String> adapter;
    AlertDialog.Builder alertDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_category, container, false);

        delete_category = view.findViewById(R.id.delete_category_button);
        category_sel = view.findViewById(R.id.category_selection);
        categories = view.findViewById(R.id.prod_cat);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, dbManager.viewCategory());
        categories.setAdapter(adapter);
        delete_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle(R.string.attention);
                alertDialog.setMessage(R.string.alert_message);
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), R.string.deletedcat, Toast.LENGTH_SHORT).show();
                        dbManager.deleteCategory(categories.getSelectedItem().toString());
                        dbManager.deleteProdCat(categories.getSelectedItem().toString());
                        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, dbManager.viewCategory());
                        categories.setAdapter(adapter);

                    }
                });
                alertDialog.setNegativeButton(R.string.CANCEL, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                });
                alertDialog.show();

            }
        });
        return view;
    }
}
