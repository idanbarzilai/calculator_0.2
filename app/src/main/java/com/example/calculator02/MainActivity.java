package com.example.calculator02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonAdd , buttonSub, buttonMul, buttonDiv, buttonEcl;
    EditText editTextN1, editTextN2;
    TextView textViewAns, textViewLast;

    SharedPreferences sharedPreferences;
    int num1, num2, answer;
    int lastAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.btn_add);
        buttonSub = findViewById(R.id.btn_sub);
        buttonMul = findViewById(R.id.btn_mul);
        buttonDiv = findViewById(R.id.btn_div);
        buttonEcl = findViewById(R.id.btn_ecl);

        editTextN1 = findViewById(R.id.number1);
        editTextN2 = findViewById(R.id.number2);

        textViewAns = findViewById(R.id.answer);
        textViewLast = findViewById(R.id.last_answer);




        buttonAdd.setOnClickListener(this);
        buttonSub.setOnClickListener(this);
        buttonMul.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonEcl.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        lastAnswer = sharedPreferences.getInt("lastAnswer", 0);
        if (lastAnswer != 0) {
            textViewLast.setText(String.valueOf(lastAnswer));
        }
    }

    public int getIntFromEditText(EditText editText){
        if (editText.getText().toString().equals("")){
                    Toast.makeText(this, "Enter number, Otherwise the result will be wrong! ", Toast.LENGTH_LONG).show();
             return 0;// לבדוק מה קורה אם אני לא מכניס ערך פעמיים רצוף, אם זה בפעם השניה יחשיב לי את זה כאילו שהכנסתי 0. בדקתי, אין ברירה כל אופציה אחרת תגרום לאפליקציה לקרוס.
        }
        else{
            return Integer.parseInt(editText.getText().toString());
        }
    }


    @Override
    public void onClick(View v) {

        num1 = getIntFromEditText(editTextN1);
        num2 = getIntFromEditText(editTextN2);

        if (v.getId() == R.id.btn_add) {
            answer = num1 + num2;
        }
        else if (v.getId() == R.id.btn_sub) {
            answer = num1 - num2;
        }
        else if (v.getId() == R.id.btn_mul) {
            answer = num1 * num2;
        }
        else if (v.getId() == R.id.btn_div) {
            if (num2 != 0) {
                answer = num1 / num2;
            }
            else {
                Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
            }
        }
        else if (v.getId() == R.id.btn_ecl) {
            textViewAns.setText(String.valueOf(answer));

            sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            lastAnswer = sharedPreferences.getInt("lastAnswer", 0);
                textViewLast.setText(String.valueOf(lastAnswer));

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("lastAnswer", answer);
            editor.apply();
        }





    }
}