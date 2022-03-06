package com.example.loginpage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.loginpage.DBObject.Course;

import org.w3c.dom.Text;

import java.util.List;

public class CustomCourseItem extends ArrayAdapter {

    private Activity context ;
    private UserMainPage userMainPage;
    private List<Course> allCourses;

    public CustomCourseItem(Activity context, List<Course> allCourses){
        super(context,R.layout.fragment_new_course_icone,allCourses);
        this.context = context;
        this.allCourses = allCourses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null) {
            row = inflater.inflate(R.layout.fragment_new_course_icone, null, true);
        }
        TextView textViewDescription = (TextView)  row.findViewById(R.id.textNewCourse);
        Button buttonSelectCourse = (Button)   row.findViewById(R.id.buttonSelectCourse);

        textViewDescription.setText(allCourses.get(position).getCity());
        buttonSelectCourse.setText(String.valueOf(allCourses.get(position).getPrice()) + " CHF");

        buttonSelectCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userMainPage.purchaseCourse(view,position);
            }
        });

        return row;
    }

    public void setUserMainPage(UserMainPage userMainPage){
        this.userMainPage = userMainPage;
    }

    public List<Course> getAllCourses() {
        return allCourses;
    }
}
