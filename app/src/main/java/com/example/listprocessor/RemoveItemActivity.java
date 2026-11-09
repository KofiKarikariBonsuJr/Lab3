
package com.example.listprocessor;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@AndroidEntryPoint
public class RemoveItemActivity extends AppCompatActivity {

    @Inject
    ItemList the_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_remove_item);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.remove_main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());

                    v.setPadding(systemBars.left, systemBars.top,
                            systemBars.right, systemBars.bottom);

                    return insets;
                });
    }

    public void removeItem(View view) {

        EditText et;
        int pos;

        et = (EditText) findViewById(R.id.remove_position);

        try {
            pos = Integer.parseInt(et.getText().toString());

            the_list.remove(pos);

            Snackbar.make(findViewById(R.id.remove_root),
                    "Item removed from the list",
                    Snackbar.LENGTH_SHORT).show();

        }
        catch (IndexOutOfBoundsException e) {

            Snackbar.make(findViewById(R.id.remove_root),
                    "Invalid position",
                    Snackbar.LENGTH_SHORT).show();

        }
        catch (NumberFormatException e) {

            Snackbar.make(findViewById(R.id.remove_root),
                    "Please enter a valid position",
                    Snackbar.LENGTH_SHORT).show();

        }
    }
}
