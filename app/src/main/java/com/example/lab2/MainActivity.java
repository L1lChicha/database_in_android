package com.example.lab2;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity {

    private PhoneViewModel mPhoneViewModel;
    private PhoneListAdapter mAdapter;

    private RecyclerView mRecyclerView;

    static final int REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#01574a")));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initializeRecycleView();

        mPhoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);

        mPhoneViewModel.getAllPhones().observe(this,phones -> {
            mAdapter.setPhoneList(phones);

        });

        FloatingActionButton fab = findViewById(R.id.fabMain);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setItemClickListener(new RecyclerViewItemClickListener() {


            @Override
            public void onPhoneClick(int position) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("position", position);

                LiveData<List<Phone>> phoneListLiveData = mPhoneViewModel.getAllPhones();
                List<Phone> itemList = phoneListLiveData.getValue();
                Phone selectedPhone = itemList.get(position);

                intent.putExtra("phone_id", selectedPhone.getId());
                intent.putExtra("phone_brand", selectedPhone.getBrand());
                intent.putExtra("phone_model", selectedPhone.getModel());
                intent.putExtra("phone_android_version", selectedPhone.getAndroidVersion());
                intent.putExtra("phone_website", selectedPhone.getWebsite());

                startActivity(intent);
            }

        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {



                    PhoneViewModel viewModel = new PhoneViewModel(getApplication());
                    String brand = data.getStringExtra("brand");
                    String model = data.getStringExtra("model");
                    String androidVersion = data.getStringExtra("androidVersion");
                    String site = data.getStringExtra("site");

                    Phone phone = new Phone(brand, model, androidVersion, site);
                    viewModel.insert(phone);
                }
            }
        }
    }


    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(8, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();

            LiveData<List<Phone>> phoneListLiveData = mPhoneViewModel.getAllPhones();
            List<Phone> itemList = phoneListLiveData.getValue();
            Phone desiredItem = itemList.get(position);
            PhoneViewModel viewModel = new PhoneViewModel(getApplication());
            viewModel.delete(desiredItem);
            mAdapter.notifyItemRemoved(position);

        }


    };



    private void initializeRecycleView(){
        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new PhoneListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item1) {
            PhoneViewModel viewModel = new PhoneViewModel(getApplication());
            viewModel.deleteAll();
            return true;
        }
      return super.onOptionsItemSelected(item);
    }



}