package com.davidtran.mobiapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SubmitFormActivity extends AppCompatActivity {

    private Button btnSubmitForm, btnGoBack;
    private EditText inputSubmission;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_form);

        btnSubmitForm = findViewById(R.id.btn_submit_form);
        btnGoBack = findViewById(R.id.btn_go_back);
        inputSubmission = findViewById(R.id.form);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnSubmitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputSubmission.getText().toString();
                if (TextUtils.isEmpty(input)) {
                    Toast.makeText(getApplicationContext(), "Field cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
               inputSubmission.setText("");
               Toast.makeText(getApplicationContext(), "Sent!", Toast.LENGTH_SHORT).show();
                mDatabase.child(input).setValue("");
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SubmitFormActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
