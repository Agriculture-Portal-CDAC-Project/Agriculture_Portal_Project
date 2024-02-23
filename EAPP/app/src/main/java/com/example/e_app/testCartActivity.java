package com.example.e_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class testCartActivity extends AppCompatActivity {

    TextView text_quantity,text1,text2,result;
    int quantity = 1;

    int total=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_cart);
        text_quantity = findViewById(R.id.text_quantity);
        text1 = findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        result=findViewById(R.id.result);
    }

    public void add(View view)
    {
        quantity++;
        text_quantity.setText(""+quantity);
        calculatePrice();
    }
    public void remove(View view)
    {
        if(quantity>0)
            quantity--;
        text_quantity.setText(""+quantity);
        calculatePrice();
    }

    public void calculatePrice(){
        int temp = Integer.parseInt(text1.getText().toString());
        total = temp*quantity;
        result.setText("Toatal"+total);


    }

}