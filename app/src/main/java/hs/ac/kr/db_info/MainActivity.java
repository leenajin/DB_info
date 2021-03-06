package hs.ac.kr.db_info;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import hs.ac.kr.db_info.InfoData;
import hs.ac.kr.db_info.InfoResponse;
import hs.ac.kr.db_info.R;
import hs.ac.kr.db_info.RetrofitClient;
import hs.ac.kr.db_info.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText mIdView;
    private EditText mSexView;
    private EditText mBirthView;
    private EditText mJobView;
    private Button mInfoButton;
    private ProgressBar mProgressView;
    private ServiceApi service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIdView = (EditText) findViewById(R.id.info_id);
        mSexView = (EditText) findViewById(R.id.info_sex);
        mBirthView = (EditText) findViewById(R.id.info_birth);
        mJobView = (EditText) findViewById(R.id.info_job);
        mInfoButton = (Button) findViewById(R.id.info_button);
        mProgressView = (ProgressBar) findViewById(R.id.info_progress);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        mInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptInfo();
            }
        });
    }

    private void attemptInfo() {
        mIdView.setError(null);
        mSexView.setError(null);
        mBirthView.setError(null);
        mJobView.setError(null);

        String id = mIdView.getText().toString();
        String sex = mSexView.getText().toString();
        String birth = mBirthView.getText().toString();
        String job = mJobView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // ????????? ????????? ??????
        if (id.isEmpty()) {
            mIdView.setError("????????? ??????????????????.");
            focusView = mIdView;
            cancel = true;
        }

        // ????????? ????????? ??????
        if (sex.isEmpty()) {
            mSexView.setError("????????? ??????????????????.");
            focusView = mSexView;
            cancel = true;
        }

        // ??????????????? ????????? ??????
            if (birth.isEmpty()) {
                mBirthView.setError("??????????????? ??????????????????.");
                focusView = mBirthView;
                cancel = true;
            }

            // ????????? ????????? ??????
            if (job.isEmpty()) {
                mJobView.setError("????????? ??????????????????.");
                focusView = mJobView;
                cancel = true;
            }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startInfo(new InfoData(id, sex, birth, job));
            showProgress(true);
        }
    }

    private void startInfo(InfoData data) {
        service.userInfo(data).enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {
                InfoResponse result = response.body();
                Toast.makeText(MainActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "??? ????????? ????????????!", Toast.LENGTH_SHORT).show();
                Log.e("??? ????????? ????????????!", t.getMessage());
                showProgress(false);
            }
        });
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
