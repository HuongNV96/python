package com.example.huongnv.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView txt;
    private TensorFlowInferenceInterface tensorFlowInferenceInterface;
    private static final String MODEL_NAME = "file:///android_asset/frozen_Regession_Linear.pb";
    private static final String INPUT_NAME = "modelInput_X";
    private static final String OUTPUT_NAME = "modelPred";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button);
        txt=findViewById(R.id.textView);
        tensorFlowInferenceInterface =new TensorFlowInferenceInterface(this.getAssets(),MODEL_NAME);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float [] input = {2,2};
                float [] result= {0};
                try {
                    tensorFlowInferenceInterface.feed(INPUT_NAME,input,1,2);
                    tensorFlowInferenceInterface.run(new String [] {OUTPUT_NAME});
                    tensorFlowInferenceInterface.fetch(OUTPUT_NAME,result);
                    txt.setText(Float.toString(result[0]));
                } catch (Exception ex) {
                    Log.d("ERROR_AI", ex.getMessage());
                }
            }
        });
    }
}
