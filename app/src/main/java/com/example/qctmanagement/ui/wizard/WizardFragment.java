package com.example.qctmanagement.ui.wizard;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.qctmanagement.R;
import com.example.qctmanagement.adapter.WizardAdapter;
import com.example.qctmanagement.databinding.WizardFragmentBinding;
import com.example.qctmanagement.helper.QCTHelper;
import com.example.qctmanagement.model.WizardModel;

import java.util.List;

public class WizardFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private WizardViewModel mViewModel;
    private WizardFragmentBinding binding;
    private static final int MAX_STEP = 4;
    private WizardAdapter wizardAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.wizard_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addControls() {
        bottomProgressDots(0);
        QCTHelper.setSystemBarColor(getActivity(), R.color.grey_10);
        QCTHelper.setSystemBarLight(getActivity());
    }

    private void addEvents() {
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = binding.viewPager.getCurrentItem() + 1;
                if (current < MAX_STEP) {
                    // move to next screen
                    binding.viewPager.setCurrentItem(current);
                } else {
                    getActivity().finish();
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(WizardViewModel.class);
        mViewModel.getWizardViewModelData().observe(getViewLifecycleOwner(), new Observer<List<WizardModel>>() {
            @Override
            public void onChanged(final List<WizardModel> wizardViewModels) {
                wizardAdapter= new WizardAdapter(getContext(),wizardViewModels);
                binding.viewPager.setAdapter(wizardAdapter);
                binding.viewPager.addOnPageChangeListener(WizardFragment.this);
                binding.viewPager.setClipToPadding(false);
                binding.viewPager.setPadding(0, 0, 0, 0);
                binding.viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.viewpager_margin_overlap));
                binding.viewPager.setOffscreenPageLimit(4);
                binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        if (binding.viewPager.getCurrentItem() == wizardViewModels.size()  - 1) {
                            binding.btnNext.setText(getResources().getString(R.string.get_started));
                        } else {
                            binding.btnNext.setText(getResources().getString(R.string.next));
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomProgressDots(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private void bottomProgressDots(int current_index) {
        ImageView[] dots = new ImageView[MAX_STEP];

        binding.layoutDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(getContext());
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
            binding.layoutDots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.shape_circle);
            dots[current_index].setColorFilter(getResources().getColor(R.color.light_green_600), PorterDuff.Mode.SRC_IN);
        }
    }
}