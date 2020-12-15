package com.example.mytoday.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytoday.R;

import java.util.ArrayList;

public class RecyclerAdapter_plan_today extends RecyclerView.Adapter<RecyclerAdapter_plan_today.ItemViewHolder> {
    // adapter에 들어갈 list 입니다.
    private ArrayList<Data_plan> listData = new ArrayList<>();
    private Context context;
    private TextView tv_datedate;
    private RecyclerView recyclerView_plan;

    RecyclerAdapter_plan_today(Context context, RecyclerView recyclerView_plan){
        //context 설정 (프레그먼트)
        this.context = context;
        this.recyclerView_plan = recyclerView_plan;
    }

    public ArrayList<Data_plan> getListData() {
        return listData;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_plan, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    void addItem(Data_plan data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }
    void clearItem(){
        //아이템 모든 내용을 제거시키는 함수
        listData.clear();
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private LinearLayout linear_item;
        private TextView textView1;

        private Data_plan data;

        ItemViewHolder(View itemView) {
            super(itemView);
            linear_item = itemView.findViewById(R.id.linear_item);
            textView1 = itemView.findViewById(R.id.textView1);
        }

        void onBind(Data_plan data) {
            this.data =data;
            textView1.setText(data.getTitle());
            //클릭 시의 동작
            linear_item.setOnClickListener(this);
            itemView.setOnClickListener(this);
            textView1.setOnClickListener(this);
        }

        //리사이클러뷰 각 계획 클릭 시의 동작
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.linear_item
                    || (v.getId() == R.id.textView1))
            {
                Intent intent2 = new Intent(context, PopupActivity_plan_today.class);
                intent2.putExtra("data.getTitle()",data.getTitle());
                intent2.putExtra("userClickPlanNum",data.getThisPlanNum());
                context.startActivity(intent2);
            }

        }

    }

}

