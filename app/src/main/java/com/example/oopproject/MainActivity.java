package com.example.oopproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.oopproject.Database.DBManager;
import com.example.oopproject.Dummy.Order;
import com.example.oopproject.Dummy.Product;
import com.example.oopproject.ManageCateg.AddCategoryFragment;
import com.example.oopproject.ManageCateg.ManageCategories;
import com.example.oopproject.ManageProduct.ManageProducts;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.example.oopproject.NewOrder.ProductListActivity;
import com.example.oopproject.RegisterOrder.HistoryOrder;
import com.example.oopproject.Settings.BusinessFragment;
import com.example.oopproject.Settings.SettingsActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import android.view.Menu;
import android.widget.TextView;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatProject
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        dbManager = new DBManager(this);
        dbManager.open();
        dbManager.update();
        COUNT = ITEMS.size();
        View headerView = navigationView.getHeaderView(0);
        TextView name = (TextView) headerView.findViewById(R.id.textView2);
        TextView address = (TextView) headerView.findViewById(R.id.textView);
        name.setText(dbManager.getBusinessName());
        address.setText(dbManager.getBusinessAddress());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_history) {
            startActivity(new Intent(this, HistoryOrder.class));
        } else if (id == R.id.nav_view) {

        } else if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        } else if (id == R.id.nav_manage_category) {
            startActivity(new Intent(this, ManageCategories.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /* Si passa all'activity di categoria prodotti indicando quale bottone è stato toccato*/
    public void SelectOperation(View view) {
            Intent i;
            switch (view.getId()) {
                case R.id.new_order:
                    if (COUNT == 0) {
                        AlertDialog.Builder alertDialog;
                        alertDialog = new AlertDialog.Builder(this);
                        alertDialog.setTitle(R.string.error);
                        alertDialog.setMessage(R.string.error_db);
                        alertDialog.setIcon(android.R.drawable.ic_delete);
                        alertDialog.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        alertDialog.show();
                        return;
                    } else {
                        i = new Intent(this, ProductListActivity.class);
                    }
                    break;
                case R.id.manage_product:
                    if(dbManager.categoryNumber() == 0)
                    {
                        AlertDialog.Builder alertDialog;
                        alertDialog = new AlertDialog.Builder(this);
                        alertDialog.setTitle(R.string.error);
                        alertDialog.setMessage(R.string.alert_categories_empty);
                        alertDialog.setIcon(android.R.drawable.ic_delete);
                        alertDialog.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getApplicationContext(), ManageCategories.class));
                            }
                        });
                        alertDialog.setNegativeButton(R.string.CANCEL, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}
                        });
                        alertDialog.show();
                        return;
                    }
                    else {
                        i = new Intent(this, ManageProducts.class);
                    }
                    break;
                    default:
                        return;
            }
        i.putExtra("view", view.getId());
        startActivity(i);

        }
}
