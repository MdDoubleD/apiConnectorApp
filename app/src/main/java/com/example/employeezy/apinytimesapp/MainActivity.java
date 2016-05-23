package com.example.employeezy.apinytimesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements XkcdRetriever.ApiResponseHandler{

    TextView responseView;
    EditText searchTV;
    ProgressBar progressBar;
    ImageView imageViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button searchButton = (Button) findViewById(R.id.searchbutton);
        assert searchButton != null;
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTV = (EditText) findViewById(R.id.searchTV);
                int foo = Integer.parseInt(searchTV.getText().toString());
                if (foo <= 0 || foo > 1684) {
                    searchButton.setClickable(false);
                    Toast.makeText(MainActivity.this, "Wrong Number Chochy", Toast.LENGTH_SHORT).show();

                }else{
                    searchButton.setClickable(true);
                    XkcdRetriever.getInstance(MainActivity.this).doRequest(foo);
                }
            }
        });
    }

    @Override
    public void handleResponse(String response) {
//        responseView = (TextView) findViewById(R.id.responseView);
//        responseView.setText(response);
        imageViewer = (ImageView) findViewById(R.id.imageViewer);
        Picasso.with(MainActivity.this).load(response).into(imageViewer);
    }
}