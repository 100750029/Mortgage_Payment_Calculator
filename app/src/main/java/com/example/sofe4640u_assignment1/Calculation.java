package com.example.sofe4640u_assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Calculation extends AppCompatActivity {

    Button btn; // Variable for button

    // Variables for text views
    TextView Mortgage_Principal_text, Interest_Rate_text, Amortization_Period_text, Payment_Frequency_text, Payment_Amount_text;

    // Variables for Strings
    String Mortgage_Principal, Interest_Rate, Amortization_Period, Payment_Frequency, Payment_Amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // When app is created the calculation.xml file is loaded
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculation);

        // Assigning ID inputs to each variable text
        btn = findViewById(R.id.button_return);
        Mortgage_Principal_text = findViewById(R.id.textPrincipalAmount);
        Interest_Rate_text = findViewById(R.id.textInterestRate);
        Amortization_Period_text = findViewById(R.id.textAmortizationPeriod);
        Payment_Frequency_text = findViewById(R.id.textPaymentFrequency);
        Payment_Amount_text = findViewById(R.id.textPaymentAmount);

        // Using Intents to get the values
        Mortgage_Principal = getIntent().getExtras().getString("Mortgage");
        Interest_Rate = getIntent().getExtras().getString("Interest");
        Amortization_Period = getIntent().getExtras().getString("Amortization");
        Payment_Frequency = getIntent().getExtras().getString("Payment");
        Payment_Amount = getIntent().getExtras().getString("Payment_Amount");

        // Setting the values back to appropriate text views
        Mortgage_Principal_text.setText("Mortgage Principal Amount: $" + Mortgage_Principal);
        Interest_Rate_text.setText("Interest Rate: " + Interest_Rate +"%");
        Amortization_Period_text.setText("Amortization Period: " + Amortization_Period + " years");
        Payment_Frequency_text.setText("Payment Frequency: " + Payment_Frequency);
        Payment_Amount_text.setText("Payment Amount: $" + Payment_Amount+"/"+Payment_Frequency);

        // OnClick listener with intent used to navigate back to home page
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Calculation.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
