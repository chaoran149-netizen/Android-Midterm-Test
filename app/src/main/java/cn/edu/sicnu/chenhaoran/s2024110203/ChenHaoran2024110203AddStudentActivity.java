package cn.edu.sicnu.chenhaoran.s2024110203;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;

public final class ChenHaoran2024110203AddStudentActivity extends Activity {
    static final String EXTRA_STUDENT = "student";
    static final String EXTRA_IDS = "existing_ids";

    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_add_student);
        setResult(RESULT_CANCELED);
        findViewById(R.id.cancel_add).setOnClickListener(v -> finish());
        findViewById(R.id.confirm_add).setOnClickListener(v -> submit());
    }

    private void submit() {
        String number = value(R.id.input_number);
        String name = value(R.id.input_name);
        String group = value(R.id.input_class);
        if (!number.matches("[0-9]{1,20}")) { showError(R.string.invalid_id); return; }
        if (name.isEmpty()) { showError(R.string.missing_name); return; }
        if (group.isEmpty()) { showError(R.string.missing_class); return; }
        ArrayList<String> ids = getIntent().getStringArrayListExtra(EXTRA_IDS);
        if (ids != null && ids.contains(number)) { showError(R.string.duplicate); return; }

        RadioGroup gender = findViewById(R.id.gender_group);
        ChenHaoran2024110203Student student = new ChenHaoran2024110203Student(number, name,
                gender.getCheckedRadioButtonId() == R.id.gender_female, group);
        setResult(RESULT_OK, new Intent().putExtra(EXTRA_STUDENT, student));
        finish();
    }

    private String value(int id) { return ((EditText) findViewById(id)).getText().toString().trim(); }
    private void showError(int message) {
        TextView status = findViewById(R.id.form_status);
        status.setText(message);
        status.setVisibility(View.VISIBLE);
    }
}
