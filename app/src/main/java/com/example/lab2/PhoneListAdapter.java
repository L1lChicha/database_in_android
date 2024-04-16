package com.example.lab2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneListAdapter extends
        RecyclerView.Adapter<PhoneListAdapter.PhoneViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Phone> mPhoneList;
    private RecyclerViewItemClickListener itemClickListener;



    public PhoneListAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);
        this.mPhoneList = null;


    }

    public void setItemClickListener(RecyclerViewItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }






    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View phoneView = mLayoutInflater.inflate(R.layout.item_view,parent, false);
        return  new PhoneViewHolder(phoneView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, @SuppressLint("RecyclerView") int position){
        if(mPhoneList != null){
            Phone phone = mPhoneList.get(position);
            holder.textView1.setText(phone.getBrand());
            holder.textView2.setText(phone.getModel());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onPhoneClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount(){
        if(mPhoneList != null){
            return mPhoneList.size();

        }
        return 0;
    }

    public void setPhoneList(List<Phone> phoneList){
        this.mPhoneList = phoneList;
        notifyDataSetChanged();
    }
    public void addItem(Phone newPhone) {
        mPhoneList.add(newPhone);
        notifyDataSetChanged();
    }


    public class PhoneViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView textView1;

        public TextView textView2;


        public PhoneViewHolder(View phoneView){
            super(phoneView);
            textView1 = phoneView.findViewById(R.id.brand);
            textView2 = phoneView.findViewById(R.id.model);

        }

        @Override
        public void onClick(View view){
            int position = getAdapterPosition() ;

        }
    }
}
