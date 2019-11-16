package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    final int CONN_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button);
        final TextView text1 = (TextView) findViewById(R.id.textView);
        final TextInputEditText nameTIE = (TextInputEditText) findViewById(R.id.textInputEditText);
        final TextInputEditText phoneNumTIE = (TextInputEditText) findViewById(R.id.textInputEditText2);

        button1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {//버튼이 눌렸을때 이벤트
                // if NFC
                // if 텍스트가 없음 => 토스트
                // sendData
                // if 결과가 잘 들어왔어 => 보냈습니다 토스트
                // else 잘 안들어왔어 => 다시 시도해주세요
                Log.d("test", nameTIE.getText().toString());
                Log.d("test", phoneNumTIE.getText().toString());

                text1.setText(" NFC인식기에 핸드폰을 인식 시켜주시고 대피하십쇼");//

                new Thread() {
                    public void run() {
                        Log.w("dd", test("http://sdh306.dothome.co.kr/?user="+phoneNumTIE.getText().toString() +"  " +nameTIE.getText().toString()));


                    }
                }.start();

                startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
                Toast.makeText(getApplicationContext(), "정보가 성공적으로 전송되었습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void sendData(final String _url, final String postData,final String postData2) {
        try {
            URL url = new URL(_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           // conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.disconnect();

         //   return result;
        } catch (Exception e) {
            Log.e("PHPRequest", "전송에 실패하였습니다."+e);
           // return null;
        }
    }

    private String test(final String u){
        String naverHtml = "";

        HttpURLConnection con = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try{
            URL url = new URL(u);
            con = (HttpURLConnection) url.openConnection();
           // con.setConnectTimeout(CONN_TIME);
            //con.setReadTimeout(CONN_TIME);

           isr = new InputStreamReader(con.getInputStream());
            br = new BufferedReader(isr);

//            String str = null;
//            while ((str = br.readLine()) != null) {
//                naverHtml += str + "\n";
//            }
//
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(con != null){
                try{con.disconnect();}catch(Exception e){}
            }

            if(isr != null){
                try{isr.close();}catch(Exception e){}
            }

            if(br != null){
                try{br.close();}catch(Exception e){}
            }
        }
        return naverHtml;
    }
}
