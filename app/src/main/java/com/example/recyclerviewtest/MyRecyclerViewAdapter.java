package com.example.recyclerviewtest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<String> datas;

    public MyRecyclerViewAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    /**
     * 相当于getView方法中船舰view和viewHodler
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_layout, null);
        return new MyViewHolder(itemView);
    }

    /**
     * 相当于getview绑定数据的部分
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //根据位置得到的数据
        String data = datas.get(position);
        holder.tv_text.setText(data);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private OnItemclickListner ItemClickListener;

    public void setOnItemClickListener(OnItemclickListner itemClickListener) {
        this.ItemClickListener = itemClickListener;
    }

    public void addData(int position, String data) {
        datas.add(position, data);
        notifyItemInserted(position);
    }

    public void removeData(int i) {
        if (datas.size() > 0) {
            datas.remove(i);
            notifyItemRemoved(i);
        }
    }

    public interface OnItemclickListner {
        void OnItemClick(View view, String data);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_icon;
        private TextView tv_text;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_text = itemView.findViewById(R.id.tv_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ItemClickListener != null) {
                        ItemClickListener.OnItemClick(itemView, datas.get(getLayoutPosition()));
                    }
                }
            });
        }
    }
}
