package com.example.thirdtopic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChargeAdapter extends RecyclerView.Adapter<ChargeAdapter.ViewHolder> {

    private List<String> mTimeList;
    private List<Integer> mMoneyList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView timeTv, moneyTv;

        public ViewHolder(View view) {
            super(view);
            timeTv = view.findViewById(R.id.time_code);
            moneyTv = view.findViewById(R.id.money_code);
        }
    }

    public ChargeAdapter(List<String> mTimeList, List<Integer> mMoneyList) {
        this.mTimeList = mTimeList;
        this.mMoneyList = mMoneyList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.charge_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ChargeAdapter.ViewHolder holder, int position) {
        System.out.println("1111111111111111"+mTimeList.size());
        holder.timeTv.setText(mTimeList.get(position));
        holder.moneyTv.setText(mMoneyList.get(position) + "");
    }


    @Override
    public int getItemCount() {
        return mTimeList.size();
    }
}
