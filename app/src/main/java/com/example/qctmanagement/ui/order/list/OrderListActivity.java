package com.example.qctmanagement.ui.order.list;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;

        import com.example.qctmanagement.R;
        import com.example.qctmanagement.ui.order.OrderFragment;

public class OrderListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_list_order, new OrderListFragment())
                .commit();
    }
}