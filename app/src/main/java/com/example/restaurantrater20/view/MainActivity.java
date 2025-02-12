package com.example.restaurantrater20.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.restaurantrater20.R;
import com.example.restaurantrater20.database.RestaurantDataSource;
import com.example.restaurantrater20.model.Restaurant;

public class MainActivity extends AppCompatActivity {
    //class object
    private Restaurant currentRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        currentRestaurant = new Restaurant();
        rateDish();
        initSave();
        initTextChangedField();
    }

    private void rateDish() {
        Button btnRate = findViewById(R.id.buttonRate);
        btnRate.setOnClickListener(v -> {
            // Rate the dish at the next activity layout
            RestaurantDataSource ds = new RestaurantDataSource(this);
            try {
                ds.open();
                if (currentRestaurant.getRestaurant_id() == -1) {
                    boolean wasInserted = ds.insertRestaurant(currentRestaurant);
                    if (wasInserted) {
                        int newId = ds.getLastRestaurantId();
                        currentRestaurant.setRestaurant_id(newId);
                    }
                } else {
                    ds.updateRestaurant(currentRestaurant);
                }
            } catch (Exception e) {
                // Do noting, error will be displayed in the log
            } finally {
                ds.close();
            }

            Intent intent = new Intent(MainActivity.this, RateDishActivity.class);
            // Pass the restaurant id to the next activity to connect to the dish table
            intent.putExtra("restaurant_id", currentRestaurant.getRestaurant_id());
            startActivity(intent);
        });
    }

    //Save all data
    private void initSave() {
        Button btnSave = findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(v -> {
            RestaurantDataSource ds = new RestaurantDataSource(this);
            try {
                ds.open();
                if (currentRestaurant.getRestaurant_id() == -1) {
                    boolean wasInserted = ds.insertRestaurant(currentRestaurant);
                    if (wasInserted) {
                        int newId = ds.getLastRestaurantId();
                        currentRestaurant.setRestaurant_id(newId);
                    }
                } else {
                    ds.updateRestaurant(currentRestaurant);
                }
            } catch (Exception e) {
                // Do noting, error will be displayed in the log
            } finally {
                ds.close();
            }
        });
    }
    //Sets and holds the text fields when going back and forth between activities
    private void initTextChangedField() {
        EditText etName = findViewById(R.id.editName);
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                currentRestaurant.setName(etName.getText().toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        EditText etStreet = findViewById(R.id.editAddress);
        etStreet.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                currentRestaurant.setStreetAddress(etStreet.getText().toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        EditText etCity = findViewById(R.id.editCity);
        etCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                currentRestaurant.setCity(etCity.getText().toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        EditText etState = findViewById(R.id.editState);
        etState.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                currentRestaurant.setState(etState.getText().toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        EditText etZip = findViewById(R.id.editZipcode);
        etZip.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                currentRestaurant.setZipcode(etZip.getText().toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }
}