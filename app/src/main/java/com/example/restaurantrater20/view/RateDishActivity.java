package com.example.restaurantrater20.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.restaurantrater20.R;
import com.example.restaurantrater20.database.RestaurantDataSource;
import com.example.restaurantrater20.model.Dish;

public class RateDishActivity extends AppCompatActivity {

    private Dish currentDish;
    private int restaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rate_dish);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        restaurantId = getIntent().getIntExtra("restaurant_id", -1);
        currentDish = new Dish();
        cancelBtn();
        rateDish();
    }


    private void rateDish() {
        Button btnRate = findViewById(R.id.btnRate);
        btnRate.setOnClickListener(v -> {

            EditText dishName = findViewById(R.id.editDishName);
            EditText dishType = findViewById(R.id.editDishType);
            RatingBar dishRating = findViewById(R.id.ratingBar);

            currentDish.setName(dishName.getText().toString());
            currentDish.setType(dishType.getText().toString());
            currentDish.setRating(dishRating.getRating());

            currentDish.setRestaurant_id(restaurantId);

            RestaurantDataSource ds = new RestaurantDataSource(this);
            try {
                ds.open();
                if (currentDish.getDish_id() == -1) {
                    boolean wasInserted = ds.insertDish(currentDish);
                    if (wasInserted) {
                        int newId = ds.getLastDishId();
                        currentDish.setDish_id(newId);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                ds.close();
                Log.println(Log.INFO, "Dish", "Dish ID: " + currentDish.getDish_id());
                finish();
            }
        });
    }

    private void cancelBtn() {
        Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(v -> {
            finish();
        });
    }
}