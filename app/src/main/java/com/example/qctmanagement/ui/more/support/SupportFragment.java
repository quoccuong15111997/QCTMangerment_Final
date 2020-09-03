package com.example.qctmanagement.ui.more.support;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qctmanagement.R;
import com.example.qctmanagement.adapter.SupportAdapter;
import com.example.qctmanagement.callback.SupportClickCallback;
import com.example.qctmanagement.databinding.SupportFragmentBinding;
import com.example.qctmanagement.firebase.SupportFirebase;
import com.example.qctmanagement.ui.more.chat.ChatActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SupportFragment extends Fragment {

    private SupportViewModel mViewModel;
    private SupportFragmentBinding binding;
    private List<SupportFirebase> stringList;
    private SupportAdapter supportAdapter;
    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.support_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
        binding.include7.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        supportAdapter.setSupportClickCallback(new SupportClickCallback() {
            @Override
            public void onItemClick(SupportFirebase key) {
                Intent intent = new Intent(getContext(), ChatActivity.class);
                intent.putExtra("KEY",key.getTitle());
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        stringList = new ArrayList<>();
        supportAdapter= new SupportAdapter(stringList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),linearLayoutManager.getOrientation());
        binding.recycle.setAdapter(supportAdapter);
        binding.recycle.setLayoutManager(linearLayoutManager);
        binding.recycle.addItemDecoration(dividerItemDecoration);

        binding.include7.txtTitle.setText("Danh sách khách hàng cần hỗ trợ");

        mData.child("customer")
                .child("supporter")
                .child("chats")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        SupportFirebase supportFirebase = new SupportFirebase();
                        supportFirebase.setTitle(dataSnapshot.getKey());
                        supportFirebase.setNumber(dataSnapshot.getChildrenCount());
                        stringList.add(supportFirebase);
                        supportAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SupportViewModel.class);
    }

}