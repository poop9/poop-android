package com.poop9.poop;

import android.app.Activity;
import android.icu.text.AlphabeticIndex;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class SocketJava extends Activity {

    private int[] layoutIds = {
            R.layout.fragment_onboarding,
            R.layout.fragment_onboarding,
            R.layout.fragment_onboarding
    };
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://chat.socket.io");
        } catch (URISyntaxException e) {}
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        mSocket.connect();
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            SocketJava.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    String message;
                    try {
                        username = data.getString("username");
                        message = data.getString("message");
                    } catch (JSONException e) {
                        return;
                    }

                    // add the message to view
//                    addMessage(username, message);
                }
            });
        }
    };



    private void setChart(List<AlphabeticIndex.Record> records) {
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0,1f));
        entries.add(new Entry(1,2f));
        entries.add(new Entry(2,3f));
        entries.add(new Entry(3,4f));

        LineDataSet lineDataSet = new LineDataSet(entries, "Emotion");// entries와 지정할 label을 생성자에 넘겨준다.

        lineDataSet.setLineWidth(2); // 선 굵기
        lineDataSet.setCircleRadius(6); // 곡률
        lineDataSet.setCircleColor(ContextCompat.getColor(this, R.color.lightish_blue));
        lineDataSet.setCircleHoleColor(ContextCompat.getColor(this, R.color.lightish_blue));
        lineDataSet.setColor(ContextCompat.getColor(this, R.color.lightish_blue));

// 그리고 기타 설정 등도 할 수 있다.

        LineData lineData = new LineData(lineDataSet);
// 라인 데이터의 텍스트 컬러 / 사이즈를 설정할 수 있다.
// 물론 나는 사용하지 않았다.
        lineData.setValueTextColor(ContextCompat.getColor(this, R.color.black));
        lineData.setValueTextSize(9);


        LineChart lineChart = findViewById(R.id.report_test_java_chart);
        lineChart.invalidate(); //차트 초기화 작업
        lineChart.clear();
    }

    public class GraphAxisValueFormatter extends ValueFormatter {
        private String[] mValues;
        // 생성자 초기화
        public GraphAxisValueFormatter() {
            super();
        }
    }
}

