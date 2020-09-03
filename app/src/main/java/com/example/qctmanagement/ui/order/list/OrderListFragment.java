package com.example.qctmanagement.ui.order.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.qctmanagement.R;
import com.example.qctmanagement.databinding.OrderListFragmentBinding;
import com.example.qctmanagement.ui.order.list.status.OrderStatusFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class OrderListFragment extends Fragment {

    private OrderListViewModel mViewModel;
    private OrderListFragmentBinding binding;
    private ViewPager view_pager;
    private TabLayout tab_layout;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.order_list_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
        binding.include4.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    private void addControls() {

        view_pager = binding.viewPager;
        tab_layout = binding.tabLayout;
        setupViewPager(view_pager);
        tab_layout.setupWithViewPager(view_pager);

        binding.include4.txtTitle.setText("Danh sách đơn đặt hàng");
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new OrderStatusFragment(1), "Chờ xác nhận");
        adapter.addFragment(new OrderStatusFragment(3), "Chờ lấy hàng");
        adapter.addFragment(new OrderStatusFragment(5), "Đang giao");
        adapter.addFragment(new OrderStatusFragment(7), "Đã hủy");
        adapter.addFragment(new OrderStatusFragment(6), "Đã giao");
        adapter.addFragment(new OrderStatusFragment(9), "Hoàn thành");
        adapter.addFragment(new OrderStatusFragment(8), "Trả hàng");
        viewPager.setAdapter(adapter);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OrderListViewModel.class);
        // TODO: Use the ViewModel
    }
    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}