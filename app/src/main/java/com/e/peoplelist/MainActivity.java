package com.e.peoplelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    Button btn_ABC, btn_123, btn_add;
    ListView lv_friendsList;

    PersonAdapter adapter;
    MyFriends myFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_ABC = findViewById(R.id.btn_ABC);
        btn_123 = findViewById(R.id.btn_123);
        btn_add = findViewById(R.id.btn_add);
        lv_friendsList = findViewById(R.id.lv_PeopleList);

        myFriends = ((MyApplication) this.getApplication()).getMyFriends();

        adapter = new PersonAdapter(MainActivity.this, myFriends);

        lv_friendsList.setAdapter(adapter);

        // Listen for incoming messages
        Bundle incomingMessages = getIntent().getExtras();

        if (incomingMessages != null){
            String name = incomingMessages.getString("name");
            int age = Integer.parseInt(incomingMessages.getString("age"));
            int picID = Integer.parseInt(incomingMessages.getString("picID"));
            int positionToEdit = incomingMessages.getInt("edit");

            Toast.makeText(this, "Position to edit is " + positionToEdit, Toast.LENGTH_SHORT).show();

            // Create new person
            Person p = new Person(name, age, picID);

            if (positionToEdit > -1) {
                myFriends.getMyFriendsList().remove(positionToEdit);

            }

            // Update list
            myFriends.getMyFriendsList().add(p);

            // Notify adapter of change
            adapter.notifyDataSetChanged();
        }


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), NewPersonForm.class);
                startActivity(i);
            }
        });

        btn_ABC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Collections.sort(myFriends.getMyFriendsList());
               adapter.notifyDataSetChanged();
            }
        });

        btn_123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(myFriends.getMyFriendsList(), new Comparator<Person>() {
                    @Override
                    public int compare(Person p1, Person p2) {
                        return p1.getAge() - p2.getAge();
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });

        lv_friendsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                editPerson(position);
            }
        });

    }

    public void editPerson(int position){

        Intent i = new Intent(getApplicationContext(), NewPersonForm.class);

        Person p = myFriends.getMyFriendsList().get(position);

        i.putExtra("edit", position);
        i.putExtra("name", p.getName());
        i.putExtra("age", p.getAge());
        i.putExtra("picID", p.getPictureNumber());

        startActivity(i);
    }

}
