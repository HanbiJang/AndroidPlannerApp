package com.example.mytoday.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytoday.R;
import com.example.mytoday.main.preference.PreferenceManager;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {
    // adapter에 들어갈 list 입니다.
    private ArrayList<Data> listData = new ArrayList<>();
    private Context context;
    private TextView tv_datedate;
    private RecyclerAdapter_plan adapter_plan;
    private RecyclerView recyclerView_plan;

    RecyclerAdapter(Context context,TextView tv_datedate){
        //context 설정 (프레그먼트)
        this.context = context;
        this.tv_datedate = tv_datedate;
    }

    void clearItem(){
        //아이템 모든 내용을 제거시키는 함수
        listData.clear();
    }

    public void setAdapter_plan(RecyclerAdapter_plan adapter_plan) {
        this.adapter_plan = adapter_plan;
    }

    public void setRecyclerView_plan(RecyclerView recyclerView_plan) {
        this.recyclerView_plan = recyclerView_plan;
    }

    public ArrayList<Data> getListData() {
        return listData;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
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

    void addItem(Data data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private LinearLayout linear_item;
        private TextView textView1;
        private TextView textView2;
        private ImageView imageView;
        private TextView tv_plannum;
        private Data data;

        ItemViewHolder(View itemView) {
            super(itemView);
            linear_item = itemView.findViewById(R.id.linear_item);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
            tv_plannum = itemView.findViewById(R.id.tv_plannum);
        }

        void onBind(Data data) {
            this.data =data;
            textView1.setText(data.getTitle());
            textView2.setText(data.getContent());
            imageView.setImageResource(data.getResId());
            tv_plannum.setText(data.getPlanNum().toString());
            //클릭 시의 동작
            linear_item.setOnClickListener(this);
            itemView.setOnClickListener(this);
            textView1.setOnClickListener(this);
            textView2.setOnClickListener(this);
            imageView.setOnClickListener(this);
            tv_plannum.setOnClickListener(this);
        }

        //리사이클러뷰 클릭 시의 동작
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.linear_item
                    || (v.getId() == R.id.textView1)
                    || (v.getId() == R.id.textView2)
                    || (v.getId() == R.id.imageView)
                    || (v.getId() == R.id.tv_plannum))
            {
               Log.e("날짜와 날씨", "TITLE : " + data.getTitle() + "\nContent : " + data.getContent());
                //날짜 저장하기
                tv_datedate.setText(data.getTitle());
                //유저가 클릭한 날짜 저장
                PreferenceManager.setString(context, "userClickDate",data.getTitle());
                //날짜 추가 관련
                //계획 보여주는 리사이클러뷰의 정보를 셋해주어야함
                getData_plan(adapter_plan,recyclerView_plan,data.getTitle());
            }
        }
    }

    private void getData_plan(RecyclerAdapter_plan adapter,RecyclerView recyclerView,String clickdate) {
        //클릭한 날짜에 대해서 데이터를 변경해야함
        //기준: 현재 날짜로부터 앞 뒤로 31일
        //날짜 형식 : 2020. 10/19 (sat) => getDate_future(~)

        //정보가 쌓이지 않도록 초기화 작업
        adapter.clearItem();

        String plan = null; //계획
        Integer myPlanNum = null;
        myPlanNum = PreferenceManager.getInt(context, //총갯수
                "myPlanNum" + clickdate);

        if(myPlanNum>=1){
            for(int j=1; j<=myPlanNum; j++){
                plan = PreferenceManager.getString(context,
                        "myPlan" + clickdate + j);
                if (plan == null) {
                    //정보 없음
                } else {
                    Data_plan data = new Data_plan();
                    data.setTitle(plan);
                    data.setThisPlanNum(j);//(==계획번호)
                    adapter.addItem(data);
                }
            }
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
        //리사이클러뷰 스크롤 조정
        recyclerView_plan.scrollToPosition(myPlanNum-1);
    }

}

