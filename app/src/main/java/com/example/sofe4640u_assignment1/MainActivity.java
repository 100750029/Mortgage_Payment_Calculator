package com.example.sofe4640u_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] frequency = {"Weekly", "Bi-Weekly", "Monthly"}; // Array for Frequency inputs
    ArrayAdapter<String> adapterItems; // Variable for array adapter
    Button btn; // Variable for button

    // Variables for edit text inputs
    EditText Mortgage_Principal_Edit, Interest_Rate_Edit, Amortization_Period_Edit;

    // Variable for auto complete text view
    AutoCompleteTextView Payment_Frequency_Auto;

    // Variables for Strings
    String Mortgage_Principal, Interest_Rate, Amortization_Period, Payment_Frequency, Payment_Amount;

    // Variables for doubles
    double Mortgage_Principal_value, Interest_Rate_value, Amortization_Period_value, Payment_Frequency_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // When app is created the activity_main.xml file is loaded
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assigning ID inputs to each variable edit
        btn = findViewById(R.id.button_calculate);
        Mortgage_Principal_Edit = findViewById(R.id.editPrincipalAmount);
        Interest_Rate_Edit = findViewById(R.id.editInterestRate);
        Amortization_Period_Edit = findViewById(R.id.editAmortizationPeriod);
        Payment_Frequency_Auto = findViewById(R.id.autoPaymentFrequency);

        // OnClick event listener used to navigate to next page
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                // Intent used to go to next page
                Intent i = new Intent(MainActivity.this, Calculation.class);

                // Getting text inputs and converting them to string
                Mortgage_Principal = Mortgage_Principal_Edit.getText().toString();
                Interest_Rate = Interest_Rate_Edit.getText().toString();
                Amortization_Period = Amortization_Period_Edit.getText().toString();
                Payment_Frequency = Payment_Frequency_Auto.getText().toString();

                // Checking if all inputs are entered and displaying error message if they aren't
                if(Mortgage_Principal.isEmpty() || Interest_Rate.isEmpty() || Amortization_Period.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please make sure to enter the Mortgage Principal, Interest Rate and Amortization Period.",Toast.LENGTH_SHORT).show();

                } else {

                    // Parsing the string and converting them to double
                    Mortgage_Principal_value = Double.parseDouble(Mortgage_Principal);
                    Interest_Rate_value = Double.parseDouble(Interest_Rate);
                    Amortization_Period_value = Double.parseDouble(Amortization_Period);

                    // Calling frequencyConversion function
                    Payment_Frequency_value = frequencyConversion(Payment_Frequency);

                    // Mortgage payment calculations
                    double Interest_Rate_percentage = (Interest_Rate_value / 100) / Payment_Frequency_value;
                    double numberofPayments = Amortization_Period_value * Payment_Frequency_value;
                    double arithmetic = Math.pow(1 + Interest_Rate_percentage, numberofPayments);
                    double Payment_Amount_value = Math.round((Mortgage_Principal_value * Interest_Rate_percentage * (arithmetic / (arithmetic - 1))) * 100.0) / 100.0;

                    // Converting payment amount to string
                    Payment_Amount = Double.toString(Payment_Amount_value);

                    // Using Intents to pass the values to next page
                    i.putExtra("Mortgage", Mortgage_Principal);
                    i.putExtra("Interest", Interest_Rate);
                    i.putExtra("Amortization", Amortization_Period);
                    i.putExtra("Payment", Payment_Frequency);
                    i.putExtra("Payment_Amount", Payment_Amount);

                    // Starting and finishing the activity
                    startActivity(i);
                    finish();
                }
            }

        });

        // Calling the dropdown layout
        adapterItems = new ArrayAdapter<String>(this,R.layout.drop_down, frequency);
        Payment_Frequency_Auto.setAdapter(adapterItems);

    }

    // Function for converting dropdown value to double
    public double frequencyConversion(String Payment_Frequency){
        double conversion = 0;

        switch(Payment_Frequency){
            case "Monthly":
                conversion = 12;
                break;

            case "Bi-Weekly":
                conversion = 26;
                break;

            case "Weekly":
                conversion = 52;
                break;

            default:
                conversion = 12;
                break;
        }
        return conversion;
    }

}