package com.e.peoplelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewPersonForm extends AppCompatActivity {

    Button btn_cancel, btn_save;
    EditText et_name, et_age, et_picID;
    int positionToEdit = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person_form);

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_save = findViewById(R.id.btn_save);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_picID = findViewById(R.id.et_picID);

        // Listen for incoming data
        Bundle incomingIntent = getIntent().getExtras();

       if (incomingIntent != null){
           String name = incomingIntent.getString("name");
           int age = incomingIntent.getInt("age");
           int picID = incomingIntent.getInt("picID");
           positionToEdit = incomingIntent.getInt("edit");

           et_name.setText(name);
           et_age.setText(String.valueOf(age));
           et_picID.setText(String.valueOf(picID));
       }

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), MainActivity.class);
                startActivity(i);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newName = et_name.getText().toString();
                String newAge = et_age.getText().toString();
                String newPicID = et_picID.getText().toString();

                Intent i = new Intent(view.getContext(), MainActivity.class);

                i.putExtra("edit", positionToEdit);
                i.putExtra("name", newName);
                i.putExtra("age", newAge);
                i.putExtra("picID", newPicID);

                startActivity(i);
            }
        });
    }
}
