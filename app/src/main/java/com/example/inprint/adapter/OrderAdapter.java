package com.example.inprint.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inprint.R;
import com.example.inprint.bean.Order;

import java.util.List;

/*
 * @Author Wenpupil
 * @Time 2019.09.07
 * @Description 初始化订单列表子项视图
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<Order> orderList;
    private OnItemClickListener listener;
    private Context context;

    public interface OnItemClickListener{
        void onClick(View v,Order order);
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
    public OrderAdapter(List<Order> orderList){
        this.orderList=orderList;
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
        Order order=orderList.get(position);
        holder.order_filename.setText(order.getDocName());
        holder.order_time.setText(order.getTime());

        String number="x"+order.getNumber();
        holder.order_number.setText(number);

        String cost="￥"+order.getCost();
        holder.order_cost.setText(cost);

        holder.itemView.setTag(order);
        holder.order_open.setTag(order);
        holder.order_open.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(listener!=null){
                    listener.onClick(view,(Order)view.getTag());
                }
            }
        });
        if(order.getStatus()!=null) {
            switch (order.getStatus()) {
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
        return orderList.size();
    }
}
