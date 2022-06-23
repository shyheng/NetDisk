package com.shy.android_application.util;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shy.android_application.IndexActivity;
import com.shy.android_application.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<API.NetFile> files = new ArrayList<>();

    public Adapter(List<API.NetFile> files) {
        this.files = files;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(View.inflate(parent.getContext(),R.layout.itme_file,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fileName.setText(files.get(position).fileName);

    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView fileName;
        Button dow;
        Button del;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.fileName);
            dow = itemView.findViewById(R.id.dow);
            del = itemView.findViewById(R.id.del);

            Typeface tf = Typeface.createFromAsset(IndexActivity.mg, "fonts/gbsn00lp-2.ttf");
            fileName.setTypeface(tf);

            Typeface tfBut = Typeface.createFromAsset(IndexActivity.mg, "fonts/but-file.otf");
            dow.setTypeface(tfBut);
            del.setTypeface(tfBut);

            dow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerItemClickDow.OnRecyclerItemClickDow(getAdapterPosition());
                }
            });
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerItemClickDel.OnRecyclerItemClickDel(getAdapterPosition());
                }
            });
        }
    }

    private OnRecyclerItemClickDow onRecyclerItemClickDow;
    public void setOnRecyclerItemClickDow(OnRecyclerItemClickDow listener){
        onRecyclerItemClickDow = listener;
    }
    public interface OnRecyclerItemClickDow{
        void OnRecyclerItemClickDow(int position);
    }

    private OnRecyclerItemClickDel onRecyclerItemClickDel;
    public void setOnRecyclerItemClickDel(OnRecyclerItemClickDel listener){
        onRecyclerItemClickDel = listener;
    }
    public interface OnRecyclerItemClickDel{
        void OnRecyclerItemClickDel(int position);
    }
}
