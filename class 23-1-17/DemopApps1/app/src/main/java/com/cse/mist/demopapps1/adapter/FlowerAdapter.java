package com.cse.mist.demopapps1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cse.mist.demopapps1.R;
import com.cse.mist.demopapps1.model.FlowerObj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salahuddin on 1/20/2017.
 */

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.MyFlowerHolder> {
   private Context context;
   private List<FlowerObj> fList = new ArrayList<FlowerObj>();

    public FlowerAdapter(List<FlowerObj> fList,Context context) {
       this.fList =fList;
       this.context =context;
    }


    public class MyFlowerHolder extends RecyclerView.ViewHolder{
        protected View mRootView;
        private TextView tvTitle;
        private TextView tvDesc;


        public MyFlowerHolder(View itemView) {
            super(itemView);

            this.tvTitle = (TextView)  itemView.findViewById(R.id.tvTitle);
            this.tvDesc = (TextView)   itemView .findViewById(R.id.tvDesc);
            mRootView = itemView;

        }
    }



    @Override
    public MyFlowerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row,parent,false);
        return new MyFlowerHolder(view);
    }

    @Override
    public void onBindViewHolder(MyFlowerHolder holder, int position) {

        FlowerObj fitem= fList.get(position);
        holder.tvTitle.setText(fitem.getName());
        holder.tvDesc.setText(fitem.getInstructions());

    }

    @Override
    public int getItemCount() {
        return fList.size();
    }


}
