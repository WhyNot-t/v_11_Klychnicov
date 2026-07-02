package com.example.Klychnicov_v_11;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ProfileActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "extra_username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String username = getIntent().getStringExtra(EXTRA_USERNAME);
        if (TextUtils.isEmpty(username)) {
            username = getString(R.string.default_username);
        }
        bindGreeting(username);
        bindUpdatedText();

        findViewById(R.id.btnExit).setOnClickListener(v -> finish());
        findViewById(R.id.btnSettings).setOnClickListener(v ->
                startActivity(new Intent(ProfileActivity.this, UserSettingsActivity.class)));
        findViewById(R.id.btnCall).setOnClickListener(v -> callBusinessNumber());
    }

    private void bindGreeting(String username) {
        TextView textGreeting = findViewById(R.id.textGreeting);
        String prefix = getString(R.string.greeting_prefix);
        String suffix = getString(R.string.greeting_suffix);
        String full = prefix + username + suffix;

        SpannableString spannable = new SpannableString(full);
        int purple = ContextCompat.getColor(this, R.color.purple_main);
        spannable.setSpan(
                new ForegroundColorSpan(purple),
                prefix.length(),
                prefix.length() + username.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textGreeting.setText(spannable);
    }

    private void bindUpdatedText() {
        TextView textUpdated = findViewById(R.id.textUpdated);
        String prefix = getString(R.string.updated_prefix);
        String value = getString(R.string.updated_value);
        String full = prefix + value;

        SpannableString spannable = new SpannableString(full);
        int purple = ContextCompat.getColor(this, R.color.purple_main);
        int dark = ContextCompat.getColor(this, R.color.text_dark);

        spannable.setSpan(
                new ForegroundColorSpan(purple),
                prefix.length(),
                full.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(
                new ForegroundColorSpan(dark),
                0,
                prefix.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textUpdated.setText(spannable);
    }

    private void callBusinessNumber() {
        String businessNumber = getString(R.string.business_number_value);
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", businessNumber, null));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.toast_no_dialer, Toast.LENGTH_SHORT).show();
        }
    }
}
