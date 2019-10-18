package com.example.oopproject.NewOrder;

import android.app.Activity;
import android.os.Bundle;

import com.example.oopproject.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oopproject.Dummy.*;

import static com.example.oopproject.AppCompatProject.ITEM_MAP;
import static com.example.oopproject.AppCompatProject.dbManager;

public class ProductDetailFragment extends Fragment {
    //ARG_ITEM_ID è l'etichetta utile per passare i dati tramite intent
    public static final String ARG_ITEM_ID = "product_id";
    private Product mItem;
    public ProductDetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = ITEM_MAP.get(getArguments().getInt(ARG_ITEM_ID));
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.name);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_detail, container, false);
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.product_detail)).setText(dbManager.viewProductDetails(mItem).toString());
        }
        return rootView;
    }

}
