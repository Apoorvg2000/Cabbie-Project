package com.example.uberclone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;


public class BookCabActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Location userCurrentLocation;
    Location dest;
    FusedLocationProviderClient fusedLocationClient;
    ArrayList<Marker> markers = new ArrayList<>();
    ArrayList<LatLng> latLngArrayList;
    Button bookCabButton;
    Button completeRideButton;
    Boolean isBooked = false;
    AutocompleteSupportFragment autocompleteSupportFragment;
    CountDownTimer timer, waitTimer;

    public void callUber(View view) {

//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Requests" + FirebaseAuth.getInstance().getCurrentUser().getUid());
        if(isBooked){
//            reference.child("PickUp Location").child("Latitude").removeValue(new DatabaseReference.CompletionListener() {
//                @Override
//                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                    if(error != null){
//                        Toast.makeText(BookCabActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//
//            reference.child("PickUp Location").child("Longitude").removeValue(new DatabaseReference.CompletionListener() {
//                @Override
//                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                    if(error != null){
//                        Toast.makeText(BookCabActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//
//            reference.child("Destination").child("Latitude").removeValue(new DatabaseReference.CompletionListener() {
//                @Override
//                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                    if(error != null){
//                        Toast.makeText(BookCabActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//
//            reference.child("Destination").child("Longitude").removeValue(new DatabaseReference.CompletionListener() {
//                @Override
//                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                    if(error != null){
//                        Toast.makeText(BookCabActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });

            isBooked = false;
            bookCabButton.setText("Book cab");
            dest = null;
            mMap.clear();
            waitTimer.cancel();
            autocompleteSupportFragment.getView().setEnabled(true);
            getLocation();
            updateMap(userCurrentLocation,"Your location",false);

        } else {
            if (dest != null) {
//                reference.child("PickUp Location").child("Latitude").setValue(userCurrentLocation.getLatitude(), new DatabaseReference.CompletionListener() {
//                    @Override
//                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                        if (error != null) {
//                            Toast.makeText(BookCabActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(BookCabActivity.this, "Your request has been recorded", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//                reference.child("PickUp Location").child("Longitude").setValue(userCurrentLocation.getLongitude(), new DatabaseReference.CompletionListener() {
//                    @Override
//                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                        if (error != null) {
//                            Toast.makeText(BookCabActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//                reference.child("Destination").child("Latitude").setValue(dest.getLatitude(), new DatabaseReference.CompletionListener() {
//                    @Override
//                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                        if (error != null) {
//                            Toast.makeText(BookCabActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//                reference.child("Destination").child("Longitude").setValue(dest.getLongitude(), new DatabaseReference.CompletionListener() {
//                    @Override
//                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                        if (error != null) {
//                            Toast.makeText(BookCabActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                        } else {
//                            bookCabButton.setText("Cancel cab");
//                            isBooked = true;
//                            autocompleteSupportFragment.getView().setEnabled(false);
//                        }
//                    }
//                });
                Toast.makeText(BookCabActivity.this, "Your request has been recorded", Toast.LENGTH_SHORT).show();
                bookCabButton.setText("Cancel cab");
                isBooked = true;
                autocompleteSupportFragment.getView().setEnabled(false);

                waitTimer = new CountDownTimer(15000, 15000) {
                    @Override
                    public void onTick(long l) {
                        Toast.makeText(BookCabActivity.this,"You can cancel the cab until your request has been accepted.",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinish() {
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Requests");
                        Toast.makeText(BookCabActivity.this,"Your request has been accepted. You cannot cancel the cab now.",Toast.LENGTH_LONG).show();
                        int n = (int) (Math.random() * 3 + 1);
                        bookCabButton.setVisibility(View.INVISIBLE);
                        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                snapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).getRef().removeValue(new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        if(error == null){

                                        } else {
                                            Toast.makeText(BookCabActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(BookCabActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });

                        NotificationCompat.Builder builder = new NotificationCompat.Builder(BookCabActivity.this, "a")
                                .setSmallIcon(R.drawable.carlogo)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.carlogo))
                                .setContentTitle("Request Accepted")
                                .setStyle(new NotificationCompat.BigTextStyle().bigText("Your request has been accepted. Your driver will reach the pickup point in " + n + " minutes."))
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setAutoCancel(true);

                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(BookCabActivity.this);

                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
                        {
                            int importance = NotificationManager.IMPORTANCE_HIGH;
                            NotificationChannel notificationChannel = new NotificationChannel("Waiting_channel", "NOTIFICATION_CHANNEL_NAME", importance);
                            notificationChannel.enableLights(true);
                            notificationChannel.setLightColor(Color.RED);
                            notificationChannel.enableVibration(true);
                            notificationChannel.setVibrationPattern(new long[]{100, 200});
                            assert notificationManager != null;
                            builder.setChannelId("Waiting_channel");
                            notificationManager.createNotificationChannel(notificationChannel);
                        }

                        // notificationId is a unique int for each notification that you must define
                        notificationManager.notify(1, builder.build());

                        timer = new CountDownTimer(n * 60 * 1000,1000) {
                            @Override
                            public void onTick(long l) {}

                            @Override
                            public void onFinish() {
                                completeRideButton.setVisibility(View.VISIBLE);

                                mMap.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener() {
                                    @Override
                                    public void onPolylineClick(Polyline polyline) {
                                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                                Uri.parse("http://maps.google.com/maps?saddr=" + userCurrentLocation.getLatitude() + "," + userCurrentLocation.getLongitude() + "&daddr=" + dest.getLatitude() + "," + dest.getLongitude()));
                                        startActivity(intent);
                                    }
                                });

                                NotificationCompat.Builder builder1 = new NotificationCompat.Builder(BookCabActivity.this, "b")
                                        .setSmallIcon(R.drawable.carlogo)
                                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.carlogo))
                                        .setContentTitle("PickUp Notification")
                                        .setContentText("Your cab is at the pickup location.")
                                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                        .setAutoCancel(true);

                                NotificationManagerCompat notificationManager1 = NotificationManagerCompat.from(BookCabActivity.this);

                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
                                {
                                    int importance = NotificationManager.IMPORTANCE_HIGH;
                                    NotificationChannel notificationChannel = new NotificationChannel("Booked_channel", "NOTIFICATION_CHANNEL_NAME", importance);
                                    notificationChannel.enableLights(true);
                                    notificationChannel.setLightColor(Color.RED);
                                    notificationChannel.enableVibration(true);
                                    notificationChannel.setVibrationPattern(new long[]{100, 200});
                                    assert notificationManager1 != null;
                                    builder1.setChannelId("Booked_channel");
                                    notificationManager1.createNotificationChannel(notificationChannel);
                                }

                                // notificationId is a unique int for each notification that you must define
                                notificationManager1.notify(2, builder1.build());
                            }
                        }.start();

                    }
                }.start();

//                new CountDownTimer(10000,2000) {
//
//                    @Override
//                    public void onTick(long l) {
//                        Toast.makeText(BookCabActivity.this, "Yes", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFinish() {
//
//                    }
//                }.start();

            } else {
                Toast.makeText(BookCabActivity.this, "Please enter a destination", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void completeRide(View view) {
        completeRideButton.setVisibility(View.INVISIBLE);
        bookCabButton.setVisibility(View.VISIBLE);
        isBooked = false;
        bookCabButton.setText("Book cab");
        dest = null;
        mMap.clear();
        autocompleteSupportFragment.getView().setEnabled(true);

        Intent intent = new Intent(BookCabActivity.this,PaymentActivity.class);
        startActivity(intent);

        getLocation();
        updateMap(userCurrentLocation,"Your location",false);
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                String result = "";
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data != -1){
                    char ch = (char) data;
                    result += ch;
                    data = reader.read();
                }
                return result;

            } catch (Exception e) {
                Toast.makeText(BookCabActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray routeObject = jsonObject.getJSONArray("routes");
                JSONObject routes = routeObject.getJSONObject(0);
                JSONObject overviewPolylines = routes
                        .getJSONObject("overview_polyline");
                String encodedString = overviewPolylines.getString("points");

                latLngArrayList.clear();
                latLngArrayList = (ArrayList<LatLng>) PolyUtil.decode(encodedString);

                PolylineOptions options = new PolylineOptions().width(20).color(Color.BLUE).geodesic(true);
                for (int z = 0; z < latLngArrayList.size(); z++) {
                    LatLng point = latLngArrayList.get(z);
                    options.add(point);
                }
                Polyline polyline = mMap.addPolyline(options);
                polyline.setClickable(true);

            } catch (Exception e) {
                Log.i("ERROR",e.toString());
            }

        }
    }

    public void updateMap(Location location, String title, boolean isDest) {
        LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 18));
        if(isDest) {
            markers.add(mMap.addMarker(new MarkerOptions().position(userLocation).title(title).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))));
            mMap.addMarker(new MarkerOptions().position(userLocation).title(title).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            RelativeLayout mapLayout = (RelativeLayout)findViewById(R.id.mapRelativeLayout);
            mapLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    for (Marker marker : markers) {
                        builder.include(marker.getPosition());
                    }
                    LatLngBounds bounds = builder.build();
                    int padding = 150; // offset from edges of the map in pixels
                    CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                    mMap.animateCamera(cu);
                }
            });

        } else {
            mMap.clear();
            markers.clear();
            markers.add(mMap.addMarker(new MarkerOptions().position(userLocation).title(title)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 18));
            mMap.addMarker(new MarkerOptions().position(userLocation).title(title));
        }
    }

    public void getLocation() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(BookCabActivity.this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        userCurrentLocation = location;
                        updateMap(location, getString(R.string.markerTitle), false);
                    }
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_cab);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.logout :
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(BookCabActivity.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        latLngArrayList = new ArrayList<>();
        bookCabButton = findViewById(R.id.callUber);
        completeRideButton = findViewById(R.id.complete_ride);
        completeRideButton.setVisibility(View.INVISIBLE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            getLocation();
        } else {
            getLocation();
        }

        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(),getString(R.string.google_maps_key));
        }

        autocompleteSupportFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.LAT_LNG, Place.Field.ADDRESS));
        autocompleteSupportFragment.getView().setBackgroundColor(Color.BLACK);
        autocompleteSupportFragment.setHint("Where to...");

        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                LatLng latLng = place.getLatLng();
                String address = place.getAddress();
                Location location = new Location(LocationManager.GPS_PROVIDER);
                location.setLatitude(latLng.latitude);
                location.setLongitude(latLng.longitude);
                mMap.clear();
                updateMap(userCurrentLocation, "Your location", false);
                dest = location;

                DownloadTask downloadTask = new DownloadTask();
                downloadTask.execute("https://maps.googleapis.com/maps/api/directions/json?" +
                        "origin=" + userCurrentLocation.getLatitude() + "," + userCurrentLocation.getLongitude() + "&destination=" + dest.getLatitude() + "," + dest.getLongitude()
                        + "&mode=driving&key=" + getString(R.string.google_maps_key));

                if(markers.size() > 1){
                    markers.remove(markers.size()-1);
                }
                updateMap(location, "Your destination: " + address, true);
            }

            @Override
            public void onError(@NonNull Status status) {}
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}

