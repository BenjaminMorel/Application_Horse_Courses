//package com.example.Horse_App.OLD;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.FragmentTransaction;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ListView;
//
//import com.example.Horse_App.CustomCourseItem;
//import com.example.Horse_App.Database.Entity.Course;
//import com.example.Horse_App.Database.Entity.Ride;
//import com.example.Horse_App.ViewModel.RideListViewModel;
//import com.google.android.gms.maps.SupportMapFragment;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserMainPage extends AppCompatActivity {
//
//    private ListView listView;
//
//    private List allCourses = new ArrayList<Course>();
//    private List rides = new ArrayList<Ride>();
//    private CustomCourseItem customCourseItem;
//
//    private MapsFragment mapsFragment;
//    private RideListViewModel rideListViewModel;
//    private void initializeMapsFragment() {
//        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
//        mapsFragment = new MapsFragment();
//        SupportMapFragment supportMapFragment = mapsFragment;
//        mTransaction.add(R.id.fragment_maps_container, supportMapFragment);
//        mTransaction.commit();
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_main_page);
//
//        RideListViewModel.Factory factory = new RideListViewModel.Factory(getApplication());
//
//
//    //    AppDatabase db = AppDatabase.getAppDateBase(this);
//
//      //   allCourses = db.courseDao().getAllCourse();
//     //   rides = db.rideDao().getAll();
//        listView = (ListView) findViewById(R.id.list_Course);
//
//        customCourseItem = new CustomCourseItem(this, allCourses,rides);
//        customCourseItem.setUserMainPage(this);
//        listView.setAdapter(customCourseItem);
//
//    }
//
//    public void editProfile(View view){
//        Intent intent = new Intent(this, EditProfile.class);
//        startActivity(intent);
//    }
//
//    public void purchaseCourse(View view, int position){
//        customCourseItem.setUserMainPage(this);
//        setContentView(R.layout.fragment_register__course_);
//        setValueOnInfoPage(position);
//        initializeMapsFragment();
//    }
//
//    public void backToMainPage(View view){
//
//        setContentView(R.layout.activity_user_main_page);
//        listView = (ListView) findViewById(R.id.list_Course);
//        CustomCourseItem customCourseItem = new CustomCourseItem(this, allCourses,rides);
//        listView.setAdapter(customCourseItem);
//        customCourseItem.setUserMainPage(this);
//    }
//
//    public void generateList(){
////        Course course1 = new Course("Martigny",4,2.0,5.6,60.00,"Super balade au bord de la Dranse");
////        Course course2 = new Course("Sion", 1, 1.0, 2.4,25.00,"Balade jusqu'au chateau et vue sur la ville");
////        Course course3 = new Course("Sierre", 1, 1.5, 3.2,32.50,"Super promenade au bord du Rhone");
////        allCourses.add(course1);
////        allCourses.add(course2);
////        allCourses.add(course3);
//    }
//
//    public void setValueOnInfoPage(int position){
//
////        TextView description = findViewById(R.id.purchaseCourse_title);
////        TextView length = findViewById(R.id.purchaseCourse_length);
////        TextView duration = findViewById(R.id.purchaseCourse_duration);
////        TextView difficulty = findViewById(R.id.purchaseCourse_difficulty);
////        TextView price = findViewById(R.id.purchaseCourse_price);
////
////        description.setText(customCourseItem.getAllCourses().get(position).getDescription());
////        length.setText("Lenght : " + String.valueOf(customCourseItem.getAllCourses().get(position).getLength()) + " KM");
////        duration.setText("Duration : " +String.valueOf(customCourseItem.getAllCourses().get(position).getDuration()) + " Heure");
////        difficulty.setText("Difficulty : " + String.valueOf(customCourseItem.getAllCourses().get(position).getDifficulty()) + "/5");
////        price.setText("Price : " + String.valueOf(customCourseItem.getAllCourses().get(position).getPrice()) + " CHF");
//
//
//    }
//
//
//
//
//}