package ru.vsu.cs.farsharing.service.app;

import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.Locale;

import ru.vsu.cs.farsharing.config.FarSharingApp;

public class GeocoderService {
    public static String getStreetFromLocation(Double x, Double y) {
        Geocoder geocoder = new Geocoder(FarSharingApp.getContext(), Locale.getDefault());
        String fullAddress = "";
        try {
            Address address = geocoder.getFromLocation(x, y, 100).get(0);
            fullAddress = address.getThoroughfare() + ", " + address.getSubThoroughfare() + ", " + address.getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fullAddress;
    }
}
