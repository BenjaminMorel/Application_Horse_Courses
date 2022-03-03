package com.example.loginpage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class CustomCourseItem extends ArrayAdapter {

    private Activity context ;
    private List<String> definition;
    private List<String> price;

    public CustomCourseItem(Activity context, List<String> definition, List<String> price){
        super(context,R.layout.fragment_new_course_icone,definition);
        this.context = context;
        this.price = price;
        this.definition = definition;
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

        textViewDescription.setText(definition.get(position));
        buttonSelectCourse.setText(price.get(position));

        return row;

    }
}
