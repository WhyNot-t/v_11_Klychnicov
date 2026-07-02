package com.example.Klychnicov_v_11;

import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserSettingsActivity extends AppCompatActivity {

    private Switch switchIncomingCalls;
    private Switch switchDnd;
    private Switch switchAutorenew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        switchIncomingCalls = findViewById(R.id.switchIncomingCalls);
        switchDnd = findViewById(R.id.switchDnd);
        switchAutorenew = findViewById(R.id.switchAutorenew);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        switchIncomingCalls.setOnCheckedChangeListener((buttonView, isChecked) ->
                showToggleToast(R.string.setting_incoming_calls, isChecked));
        switchDnd.setOnCheckedChangeListener((buttonView, isChecked) ->
                showToggleToast(R.string.setting_dnd_mode, isChecked));
        switchAutorenew.setOnCheckedChangeListener((buttonView, isChecked) ->
                showToggleToast(R.string.setting_autorenew, isChecked));

        findViewById(R.id.btnResetSettings).setOnClickListener(v -> resetSettings());
    }

    private void showToggleToast(int labelRes, boolean isChecked) {
        String state = getString(isChecked ? R.string.toast_setting_on : R.string.toast_setting_off);
        Toast.makeText(this, getString(labelRes) + ": " + state, Toast.LENGTH_SHORT).show();
    }

    private void resetSettings() {
        switchIncomingCalls.setChecked(true);
        switchDnd.setChecked(true);
        switchAutorenew.setChecked(true);
        Toast.makeText(this, R.string.toast_settings_reset, Toast.LENGTH_SHORT).show();
    }
}
