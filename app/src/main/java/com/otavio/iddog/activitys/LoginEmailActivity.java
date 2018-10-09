package com.otavio.iddog.activitys;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.otavio.iddog.utils.ServiceAPI;
import com.otavio.iddog.R;
import com.otavio.iddog.models.User;
import com.otavio.iddog.models.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginEmailActivity extends AppCompatActivity {

    private EditText mEmail;
    private Button mEmailSignInButton;
    ProgressDialog dialog;
    AlertDialog alerta;


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

        dialog = new ProgressDialog(LoginEmailActivity.this);
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
            requisitarEmailAPI(email);
        }
    }

    private boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    private void requisitarEmailAPI(String email){

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

                    //guardando o token
                    SharedPreferences prefs = getSharedPreferences("token", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor;
                    editor = prefs.edit();
                    editor.putString("token", token);
                    editor.apply();

                    Intent intent = new Intent(LoginEmailActivity.this, MainActivity.class);
                    startActivity(intent);

                    mEmail.setText("");
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                alertaError(t.getMessage().toString());

            }
        });

    }

    private void  alertaError(String mensagem){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titleAlertDialog);
        builder.setMessage(mensagem);
        alerta = builder.create();
        alerta.show();

    }

}

