package com.skyblue.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    APIInterface apiInterface;
    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseText = findViewById(R.id.serverResponse);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        // Get list resources
        Call<MultipleResource> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<MultipleResource>() {
            @Override
            public void onResponse(Call<MultipleResource> call, Response<MultipleResource> response) {
                Log.d("TAG", response.code()+"");
                String displayResponse = "";

                MultipleResource resource = response.body();
                Integer text = resource.page;
                Integer total = resource.total;
                Integer totalPages = resource.totalPages;
                List<MultipleResource.Datum> datumList = resource.data;

                //displayResponse += text + "Page\n" + total + "Total\n" + totalPages + "TotalPages\n";
                displayResponse += "Page :" + text + "\n" + "Total :" + total + "\n" + "Total Pages :" + totalPages;

                for (MultipleResource.Datum datum : datumList){
                    displayResponse += "\n" + datum.id + "" + datum.name + datum.pantoneValue + datum.year ;
                }
                responseText.setText(displayResponse);
            }

            @Override
            public void onFailure(Call<MultipleResource> call, Throwable t) {

            }
        });

    }
}