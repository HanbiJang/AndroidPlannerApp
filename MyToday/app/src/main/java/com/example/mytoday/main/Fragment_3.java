package com.example.mytoday.main;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import com.example.mytoday.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.CLIPBOARD_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

public class Fragment_3 extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    //파일 이름
    final String fileName = "/myMemo.txt";

    // newInstance constructor for creating fragment with arguments
    public static Fragment_3 newInstance(int page, String title) {
        Fragment_3 fragment = new Fragment_3();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }



    // Inflate the view for the fragment based on layout XML
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        final EditText et_writememo;
        final ImageButton imgbtn_addmemo;
        ListView listView1;
        ArrayAdapter<String> adapter = null;


        final ArrayList<String> listItem;
        ArrayList<String> listItem1;

        listItem1 = new ArrayList<String>();


        ArrayList<String> myMemoList;
        myMemoList = new ArrayList<>();


        //파일 읽어오기
        File saveFile = new File(getContext().getFilesDir() , "/userData"); // 저장 경로
        myMemoList = readMyMemoFile(saveFile);

        //myMemoList에 내용이 없다면
        if(myMemoList == null ){
            listItem1.add("31일까지 책 반납해야됨");
            listItem1.add("열쇠 구두 밑에 있음");
            listItem1.add("비밀번호: 486");
            listItem = listItem1;
        }
        //myMemoList에 내용이 있다면
        //listItem을 저장한 파일 myMemo 을 읽어와서 listItem에 할당해주기
        else{
            listItem = myMemoList;
        }

        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,listItem);

        imgbtn_addmemo=view.findViewById(R.id.imgbtn_addmemo);
        et_writememo = view.findViewById(R.id.et_writememo);


        //파일관련
        // 메모 추가하기
        final ArrayAdapter<String> finalAdapter1 = adapter;
        imgbtn_addmemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listItem.add(et_writememo.getText().toString());
                finalAdapter1.notifyDataSetChanged(); // 변경되었음을 어답터에 알려준다.
                et_writememo.setText("");

                //파일에 추가된 리스트 내용을 새로 저장하기
                File saveFile = new File(getContext().getFilesDir() , "/userData"); // 저장 경로
                saveNewMemo(listItem,saveFile);

            }
        });

            listView1 = view.findViewById(R.id.listView1);
            listView1.setAdapter(adapter);

            // 각 아이템 클릭시 해당 아이템 삭제한다.
        final ArrayAdapter<String> finalAdapter2 = adapter;


            listView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {

                    new AlertDialog.Builder(getContext())
                            .setTitle("").setMessage("메모를 삭제하실 건가요?")
                            .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                    Toast.makeText(getContext(),listItem.get(i).toString() + "(이)가 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                                    listItem.remove(i);
                                    finalAdapter2.notifyDataSetChanged();

                                    //파일에 삭제된 내용을 새로 저장하기
                                    File saveFile = new File(getContext().getFilesDir() , "/userData"); // 저장 경로
                                    saveNewMemo(listItem,saveFile);

                                }
                            })
                            .setNeutralButton("취소", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                }
                            })
                    .show();

                    return true;
                }
            });


        //채널 생성
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel(
                    "plan_b", "계획비", NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationChannel.setDescription("channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(R.color.colorPurple);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 100, 200});
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            notificationManager.createNotificationChannel(notificationChannel);
        }



        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                // 콜백매개변수는 순서대로 어댑터뷰, 해당 아이템의 뷰, 클릭한 순번, 항목의 아이디
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                    new AlertDialog.Builder(getContext())
                            .setTitle("").setMessage("메모에 대한 동작을 선택하세요")
                            .setPositiveButton("검색", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

//                                    //만약 사이트명이 포함되었을 경우
//                                    if(listItem.get(i).contains("https://")){
//
//                                        int index_https = listItem.get(i).indexOf("https://");
//                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(listItem.get(i)));
//                                        startActivity(intent);
//                                    }
                                    //사이트명이 아닐 경우 구글 검색하기
//                                    else{
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q="+listItem.get(i)+"&oq="+listItem.get(i)+"&aqs=chrome..69i57j69i59l3j69i61l3.436j0j9&sourceid=chrome&ie=UTF-8"));
                                        // intent.setPackage("com.android.chrome");   // 브라우저가 여러개 인 경우 콕 찍어서 크롬을 지정할 경우
                                        startActivity(intent);
//                                    }



                                }
                            })



                            //메모 사항 푸시에 띄워놓기 기능
                            .setNeutralButton("푸시", new DialogInterface.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    NotificationManager notificationManager;
                                    //아이콘 설정
                                    Bitmap mLargeIconForNoti=
                                            BitmapFactory.decodeResource(getResources(),R.drawable.smallicon);
                                    //알림 울리기
                                    //채널 등록
                                    Notification.Builder builder = new Notification.Builder(getContext(), "plan_b")
                                            .setContentTitle(listItem.get(i))
                                            .setContentText(" ")
                                            .setSmallIcon(R.drawable.smallicon)
                                            .setLargeIcon(mLargeIconForNoti)
                                            .setAutoCancel(true);
                                    notificationManager = (NotificationManager)getContext().getSystemService(NOTIFICATION_SERVICE);
                                    notificationManager.notify(0, builder.build());


                                }
                            })
                            .setNegativeButton("복사", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //클립보드 사용 코드
                                    ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService(CLIPBOARD_SERVICE);
                                    ClipData clipData = ClipData.newPlainText("momo",listItem.get(i)); //클립보드에 ID라는 이름표로 id 값을 복사하여 저장
                                    clipboardManager.setPrimaryClip(clipData);

                                    //복사가 되었다면 토스트메시지 노출
                                    Toast.makeText(getContext(),"메모가 복사되었어요",Toast.LENGTH_SHORT).show();

                                }
                            })
                            .show();

                }
            });

        return view;

    }

    //
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public ArrayList readMyMemoFile(File saveFile){
        //파일 읽기
        //폴더생성
        if(!saveFile.exists()){ // 폴더 없을 경우
            saveFile.mkdir(); // 폴더 생성
        }


        try(
                FileReader rw = new FileReader(saveFile+fileName);
                BufferedReader br = new BufferedReader( rw );
        ){


            String readLine = null ;
            Integer i = 1;

            ArrayList<String> buffer = new ArrayList<>();
            while( ( readLine = br.readLine() ) != null ){
                //readLine 저장해야함
                // 1: (나머지 3) 날짜 2: (나머지 2) 1번 yes 여부 3: (나머지 1) 2번 yes여부 4: (나머지 0) 만족도 점수

                buffer.add(readLine);
                i++;


            }
            //끝
            return buffer;

        }catch ( IOException e ) {
            System.out.println(e);
            return null;
        }
    }

    //파일에 listItem 요소 내용을 덮어쓰기
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void saveNewMemo(ArrayList<String> listItem, File saveFile){

        try(
                // 파일 객체 생성
                FileWriter fw_append = new FileWriter(saveFile+fileName, false);
        ){

            StringBuffer str = new StringBuffer();

            int i = 0;
            while(i < listItem.size()){
                str.append(listItem.get(i)+"\n");
                i++;
            }

            //파일 쓰기
            fw_append.write(String.valueOf(str));


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }
}
