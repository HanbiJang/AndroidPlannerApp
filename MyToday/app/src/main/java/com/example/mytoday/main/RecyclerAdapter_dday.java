package com.example.mytoday.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytoday.R;
import com.example.mytoday.main.preference.PreferenceManager;

import java.util.ArrayList;

public class RecyclerAdapter_dday extends RecyclerView.Adapter<RecyclerAdapter_dday.ItemViewHolder> {
    // adapter에 들어갈 list 입니다.
    private ArrayList<Data_dday>  listData = new ArrayList<>();
    private Context context;
    private RecyclerView recyclerView_dday;

    RecyclerAdapter_dday(Context context, RecyclerView recyclerView_dday){

        //context 설정 (프레그먼트)
        this.context = context;
        this.recyclerView_dday = recyclerView_dday;
    }

    public void RecyclerAdapter_dday_set_lostData(ArrayList listData){
        this.listData = listData;
    }

    public ArrayList<Data_dday> getListData() {
        return listData;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_dday, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData.get(position));
        Log.d("onBindViewHolder 내부: ", "listData.get(position): " +listData.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    void addItem(Data_dday data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
        Log.e("addItem", "data: "+data.getTitle());
    }
    void clearItem(){
        //아이템 모든 내용을 제거시키는 함수
        listData.clear();
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private LinearLayout linear_item_dday;
        private TextView textView1_dday;

        protected  TextView textView1_dday_leave;
        private Data_dday data;

        ItemViewHolder(View itemView) {
            super(itemView);
            textView1_dday_leave =itemView.findViewById(R.id.textView1_dday_leave);
            linear_item_dday = itemView.findViewById(R.id.linear_item_dday);
            textView1_dday = itemView.findViewById(R.id.textView1_dday);
        }

        void onBind(Data_dday data) {
            this.data =data;
            textView1_dday.setText(data.getTitle());

            Integer ddayDap = data.getLeaveday();
            if(ddayDap < 0 ){ //둘의 날짜 차이가 음수라면 == 이미 지난 디데이라면
                ddayDap = ddayDap * -1; //양수로 만들기 ex) D--2 => D-2
                textView1_dday_leave.setText("D+"+(ddayDap.toString()));
            }
            else {
                textView1_dday_leave.setText("D-" + (ddayDap.toString()));
            }

            //클릭 시의 동작
            linear_item_dday.setOnClickListener(this);
            itemView.setOnClickListener(this);
            linear_item_dday.setOnClickListener(this);
            textView1_dday_leave.setOnClickListener(this);
        }

        //리사이클러뷰 각 디데이 클릭 시의 동작
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.linear_item_dday
                    || (v.getId() == R.id.textView1_dday))
            {

                Intent intent2 = new Intent(context, PopupActivity_dday.class);
                intent2.putExtra("data.getTitle()",data.getTitle());
                intent2.putExtra("userClickDdayNum",data.getThisPlanNum());
                context.startActivity(intent2);

/*                new AlertDialog.Builder(context)
                        .setTitle("").setMessage("디데이 설정")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Integer myDdayNum = PreferenceManager.getInt(context, "myDdayNum"); // 디데이 총개수 (n)
                                Integer m = data.getThisPlanNum();
                                for(int i = m+1; i<= myDdayNum; i++){
                                    PreferenceManager.setString(context, "myDday",
                                            PreferenceManager.getString(context, "myPlan"+ (i)));
                                }
                                PreferenceManager.removeKey(context,"myDday"+myDdayNum); //마지막 요소 삭제
                                PreferenceManager.setInt(context,"myDdayNum", myDdayNum-1);

                                //디데이  날짜 없애기
                                PreferenceManager.removeKey(context, "myDdayDate"+ myDdayNum);

                                Toast.makeText(context,data.getTitle() + "(이)가 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .show();*/
            }


        }

    }

}

