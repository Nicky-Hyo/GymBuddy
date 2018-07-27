package com.example.nicky.gymbuddy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends Fragment implements
        OnMapReadyCallback {

    MapView mapView;
    private GoogleMap mMap;
    Double x, y;
    String loc_name;
    JsonArrayRequest request;
    private RequestQueue requestQueue;
    String JSON_URL = "https://bnatalia45.000webhostapp.com/092209_Api/gymlocation.php";


    public MapsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_gym, container, false);

        SupportMapFragment mapFragment =
                (SupportMapFragment)
                        getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        markerRequest();

        return view;


    }

    private void markerRequest() {
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        x = jsonObject.getDouble("latitude");
                        y = jsonObject.getDouble("longitude");
                        loc_name = jsonObject.getString("location");


                        Bitmap img = BitmapFactory.decodeResource(getResources(),
                                R.mipmap.gymarker);
                        BitmapDescriptor bitmapDescriptor =
                                BitmapDescriptorFactory.fromBitmap(img);

                        LatLng gymloc = new LatLng(y, x);
                        mMap.addMarker(new MarkerOptions().
                                position(gymloc).
                                title(loc_name)
                                .icon(bitmapDescriptor));

                        mMap.moveCamera(CameraUpdateFactory.newLatLng(gymloc));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMaxZoomPreference(10);

        markerRequest();

    }
}