package com.example.qctmanagement;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.qctmanagement.ui.dashboard.DashboardFragment;
import com.example.qctmanagement.ui.more.MoreFragment;
import com.example.qctmanagement.ui.order.OrderFragment;
import com.example.qctmanagement.ui.product.ProductFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navView;
    private Fragment fragmentDashboard, fragmentMore, fragmentOrder, fragmentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_dashboard :{
                        if (fragmentDashboard==null){
                            fragmentDashboard= new DashboardFragment();
                        }
                        loadFragment(fragmentDashboard);
                            break;

                    }
                    case R.id.navigation_order : {

                        if (fragmentOrder==null){
                            fragmentOrder= new OrderFragment();
                        }
                        loadFragment(fragmentOrder);
                        break;
                    }
                    case R.id.navigation_product: {
                        if (fragmentProduct==null){
                            fragmentProduct= new ProductFragment();
                        }
                        loadFragment(fragmentProduct);
                        break;
                    }
                    case R.id.navigation_more : {
                        if (fragmentMore==null){
                            fragmentMore= new MoreFragment();
                        }
                        loadFragment(fragmentMore);
                        break;
                    }

                }
                return true;
            }
        });
    }

    private void addControls() {
        navView = findViewById(R.id.nav_view);
        if (fragmentDashboard==null){
            fragmentDashboard= new DashboardFragment();
        }
        loadFragment(fragmentDashboard);
    }
    private boolean loadFragment(Fragment fragment){
        if (fragment!=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container,fragment)
                    .commit();
            return true;
        }
        return false;
    }
}