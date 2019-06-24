package com.e.peoplelist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyFriends {

    List<Person> myFriendsList = new ArrayList<Person>();

    public List<Person> getMyFriendsList() {
        return myFriendsList;
    }

    public void setMyFriendsList(List<Person> myFriendsList) {
        this.myFriendsList = myFriendsList;
    }

    public MyFriends(List<Person> myFriendsList) {
        this.myFriendsList = myFriendsList;
    }

    public MyFriends() {
        String[] startingNames = {"Ricky","Bobby","Sarah","Rachel"};
        Random rnd = new Random();
        for (int i = 0; i < startingNames.length; i++){
            myFriendsList.add(new Person(startingNames[i], rnd.nextInt(50)+15,rnd.nextInt(6)));
        }
    }
}
