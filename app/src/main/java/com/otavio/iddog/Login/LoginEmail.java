package com.otavio.iddog.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.otavio.iddog.Interfaces.ServiceAPI;
import com.otavio.iddog.ListaDogs;
import com.otavio.iddog.MainActivity;
import com.otavio.iddog.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginEmail extends AppCompatActivity {

    private EditText mEmail;
    private Button mEmailSignInButton;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);

        mEmail = findViewById(R.id.email_login);

         mEmailSignInButton = (Button) findViewById(R.id.email_sign_in);

        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });



    }

    private void login() {

        dialog = new ProgressDialog(LoginEmail.this);
        dialog.setMessage("Carregando...");
        dialog.setCancelable(false);
        dialog.show();


        boolean cancel = false;
        View focusView = null;

        final String email = mEmail.getText().toString();


        // Verifica um endereço de email válido.

        if (TextUtils.isEmpty(email)) {

            mEmail.setError(getString(R.string.error_field_required));
            focusView = mEmail;
            cancel = true;
            dialog.dismiss();

        } else if (!isEmailValid(email)) {

            mEmail.setError(getString(R.string.error_invalid_email));
            focusView = mEmail;
            cancel = true;
            dialog.dismiss();

        }else{

            final User user = new User();
            user.setEmail(email);

            UserResponse userResponse = new UserResponse();
            userResponse.setUser(user);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api-iddog.idwall.co")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

             ServiceAPI serviceAPI = retrofit.create(ServiceAPI.class);
             Call<UserResponse> call = serviceAPI.login(user);

             call.enqueue(new Callback<UserResponse>() {
                 @Override
                 public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                     if (response.isSuccessful()) {
                         if (dialog.isShowing())
                             dialog.dismiss();

                         String token  = response.body().getUser().getToken();

                         SharedPreferences prefs = getSharedPreferences("token", Context.MODE_PRIVATE);
                         SharedPreferences.Editor editor;
                         editor = prefs.edit();
                         editor.putString("token", token);
                         editor.apply();

                         Intent intent = new Intent(LoginEmail.this, MainActivity.class);
                         startActivity(intent);

                         mEmail.setText("");
                     }

                 }

                 @Override
                 public void onFailure(Call<UserResponse> call, Throwable t) {

                 }
             });






        }


    }

    private boolean isEmailValid(String email) {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

}

