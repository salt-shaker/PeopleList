package com.e.peoplelist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonAdapter extends BaseAdapter {

    Activity myActivity;
    MyFriends myFriends;

    public PersonAdapter(Activity myActivity, MyFriends myFriends) {
        this.myActivity = myActivity;
        this.myFriends = myFriends;
    }

    @Override
    public int getCount() {
        return myFriends.getMyFriendsList().size();
    }

    @Override
    public Person getItem(int position) {
        return myFriends.getMyFriendsList().get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View onePersonLine;

        LayoutInflater inflater = (LayoutInflater) myActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        onePersonLine = inflater.inflate(R.layout.person_one_line, viewGroup, false);

        TextView tv_name = onePersonLine.findViewById(R.id.tv_name);
        TextView tv_age = onePersonLine.findViewById(R.id.tv_age);
        ImageView iv_contactImage = onePersonLine.findViewById(R.id.iv_contactImage);

        Person p = this.getItem(i);

        tv_name.setText(p.getName());
        tv_age.setText(String.valueOf(p.getAge()));

        int resource_icon_numbers[] = {
                R.drawable.img01,
                R.drawable.img02,
                R.drawable.img03,
                R.drawable.img04,
                R.drawable.img05,
                R.drawable.img06,
                R.drawable.img07,
        };

        iv_contactImage.setImageResource(resource_icon_numbers[p.getPictureNumber()]);

        return onePersonLine;
    }
}
