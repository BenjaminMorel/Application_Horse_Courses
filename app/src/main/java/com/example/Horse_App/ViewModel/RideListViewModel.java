package com.example.Horse_App.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.Entity.Ride;
import com.example.Horse_App.Database.repository.RideRepository;

import java.util.List;

public class RideListViewModel extends AndroidViewModel {

    private final MediatorLiveData<List<Ride>> observableRides;

    public RideListViewModel(@NonNull Application application, RideRepository rideRepository){
        super(application);
        observableRides = new MediatorLiveData<>();
        observableRides.setValue(null);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;
        private final RideRepository rideRepository;

        public Factory(@NonNull Application application){
            this.application = application;
            rideRepository = ((BaseApp) application).getRideRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass){
            return (T) new RideListViewModel(application, rideRepository);
        }
    }

    public LiveData<List<Ride>> getRides(){ return observableRides;}
}
