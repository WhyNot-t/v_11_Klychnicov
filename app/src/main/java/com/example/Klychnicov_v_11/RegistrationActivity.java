package com.example.Klychnicov_v_11;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editLogin;
    private EditText editPassword;
    private ImageButton btnTogglePassword;
    private boolean passwordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editLogin = findViewById(R.id.editLogin);
        editPassword = findViewById(R.id.editPassword);
        btnTogglePassword = findViewById(R.id.btnTogglePassword);

        btnTogglePassword.setOnClickListener(v -> togglePasswordVisibility());

        findViewById(R.id.btnAuthorize).setOnClickListener(v -> onAuthorizeClicked());
    }

    private void togglePasswordVisibility() {
        passwordVisible = !passwordVisible;
        int selection = editPassword.getSelectionEnd();
        if (passwordVisible) {
            editPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            btnTogglePassword.setImageResource(R.drawable.ic_eye);
        } else {
            editPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            btnTogglePassword.setImageResource(R.drawable.ic_eye_off);
        }
        editPassword.setSelection(selection);
    }

    private void onAuthorizeClicked() {
        String login = editLogin.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (TextUtils.isEmpty(login) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, R.string.toast_empty_fields, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(RegistrationActivity.this, ProfileActivity.class);
        intent.putExtra(ProfileActivity.EXTRA_USERNAME, login);
        startActivity(intent);
    }
}
