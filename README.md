# Application Horse Courses

The goal of this project is to develop a mobile application, and, in a second step, to store the
application data inside the cloud. For this, we will use respectively Android and Firebase.

Our topic is about horse lessons and the goal is to be able to book horse riding. We have several walks available, and the user can choose a place and a date to go for a ride.

This application was developed by Benjamin Morel and Hugo Vouillamoz as part of the 644-1_Mobile and Cloud Computing course with our teachers Michael Ignaz Schumacher and Yvan Pannatier as part of our 4th semester of training at the HES-SO Valais/Wallis in Sierre.

## Run the application
In order to be able to launch the application, it is necessary to add in the "local.properties" file in the gradle scripts, the google maps API Key that we used:

    MAPS_API_KEY=AIzaSyCosLACRBJxK_I-Pgg4IIRnuGL1e5Fvj5U

To be able to connect, we have 2 users,
- Email: hugo.vouillamoz@hes-so.ch, password: password123 
- Email: benjamin.morel@hes-so.ch, password: letmein123

## Dependencies
### For Firebase

Link to our project: https://console.firebase.google.com/u/0/project/application-horse-courses/overview

    implementation 'com.google.firebase:firebase-database:20.0.4'
    implementation 'com.google.firebase:firebase-auth:21.0.3'
    implementation 'com.google.firebase:firebase-firestore:24.1.0'
    implementation platform('com.google.firebase:firebase-bom:29.2.1')
    implementation 'com.google.firebase:firebase-analytics'

### For Room Database
We used version 31 for the compileSdk and for Room Database the version 2.4.2. We added thoses dependencies:
    
    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-rxjava2:$room_version"
    implementation "androidx.room:room-rxjava3:$room_version"
    implementation "androidx.room:room-guava:$room_version"
    testImplementation "androidx.room:room-testing:$room_version"
    implementation "androidx.room:room-paging:2.5.0-alpha01"

## Application features requested
The application that you will develop must include the following features:
- User interface allowing to:
  - Show the dataset information
  - Change/Delete this information
  - Add new data
- Action bar (or navigation drawer using fragments)
- Settings, allowing to display an About information (information about the App) and other useful settings
- Correct management of activities
- Correct navigation hierarchy
- Storing data on the phone using Google’s Room API, and not on the cloud (1st part of project)
- Storing data inside Google cloud using Firebase (2nd part of project)
- Other useful features to make your application coherent and attractive
- The code must be self-documented and self-explaining
