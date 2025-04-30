package com.example.battlepets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BattleLogAdapter extends RecyclerView.Adapter<BattleLogAdapter.ViewHolder> {

    private List<BattleLog> battleLogs;

    public BattleLogAdapter(List<BattleLog> battleLogs){
        this.battleLogs = battleLogs;
    }

    @Override
    public BattleLogAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_battle_log,
                parent,false); //make the layout

        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(BattleLogAdapter.ViewHolder holder, int position) {
        holder.logText.setText(battleLogs.get(position).getSummary());
    }

    @Override
    public int getItemCount() {
        return battleLogs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView logText;

        public ViewHolder(View view) {
            super(view);
            logText = view.findViewById(R.id.logText); //needs a layout
        }
    }
}

