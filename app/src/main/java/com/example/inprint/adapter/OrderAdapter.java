package com.example.inprint.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inprint.R;
import com.example.inprint.bean.Uorder;

import java.util.List;

/*
 * @Author Wenpupil
 * @Time 2019.09.07
 * @Description 初始化订单列表子项视图
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<Uorder> uorderList;
    private OnItemClickListener listener;
    private Context context;

    public interface OnItemClickListener{
        void onClick(View v, Uorder uorder);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView order_filename;           //订单 文档名
        TextView order_time;               //订单 时间
        TextView order_status;             //订单 状态
        TextView order_number;             //订单 份数
        TextView order_cost;               //订单 付款
        TextView order_open;               //订单 取件
        ViewHolder(View view){
            super(view);
            order_filename=view.findViewById(R.id.tv_order_item_filename);
            order_time=view.findViewById(R.id.tv_order_item_time);
            order_status=view.findViewById(R.id.tv_order_item_status);
            order_number=view.findViewById(R.id.tv_order_item_number);
            order_cost=view.findViewById(R.id.tv_order_item_cost);
            order_open=view.findViewById(R.id.tv_order_item_open);
        }
    }
    public void setContext(Context context){
        this.context=context;
    }
    public OrderAdapter(List<Uorder> uorderList){
        this.uorderList = uorderList;
    }
    public void setListener(OnItemClickListener listener){
        this.listener=listener;
    }
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item,parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position){
        Uorder uorder = uorderList.get(position);
        holder.order_filename.setText(uorder.getDocName());
        holder.order_time.setText(uorder.getTime());

        String number="x"+ uorder.getNumber();
        holder.order_number.setText(number);

        String cost="￥"+ uorder.getCost();
        holder.order_cost.setText(cost);

        holder.itemView.setTag(uorder);
        holder.order_open.setTag(uorder);
        holder.order_open.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(listener!=null){
                    listener.onClick(view,(Uorder)view.getTag());
                }
            }
        });
        if(uorder.getStatus()!=null) {
            switch (uorder.getStatus()) {
                case "0":
                    holder.order_status.setText("正在打印");
                    break;
                case "1":
                    holder.order_status.setText("打印完成");
                    break;
                case "2":
                    holder.order_status.setText("打印完成");
                    holder.order_open.setText("已取件");
                    holder.order_open.setBackground(
                            context.getResources().getDrawable(R.drawable.order_item_open_false));
                    holder.order_open.setTextColor(
                            context.getResources().getColor(R.color.order_grey)
                    );
                    break;
                default:
            }
        }
    }

    @Override
    public int getItemCount(){
        return uorderList.size();
    }
}
