package cn.edu.sicnu.chenhaoran.s2024110203;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public final class ChenHaoran2024110203MainActivity extends Activity {
    private static final int ADD_REQUEST = 1;
    private static final String STATE_STUDENTS = "students";
    private final ArrayList<ChenHaoran2024110203Student> students = new ArrayList<>();
    private ChenHaoran2024110203StudentAdapter adapter;

    @SuppressWarnings("deprecation")
    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        setTitle(R.string.app_name);
        setContentView(R.layout.activity_main);
        if (state == null) {
            String group = getString(R.string.sample_class);
            students.add(new ChenHaoran2024110203Student("20240001", getString(R.string.sample_a), false, group));
            students.add(new ChenHaoran2024110203Student("20240002", getString(R.string.sample_b), true, group));
        } else if (state.getSerializable(STATE_STUDENTS) instanceof ArrayList<?>) {
            for (Object item : (ArrayList<?>) state.getSerializable(STATE_STUDENTS)) {
                if (item instanceof ChenHaoran2024110203Student) students.add((ChenHaoran2024110203Student) item);
            }
        }
        ListView list = findViewById(R.id.students);
        adapter = new ChenHaoran2024110203StudentAdapter(this, students);
        list.setAdapter(adapter);
        updateCount();
        findViewById(R.id.add_student).setOnClickListener(v -> openAddStudent());
    }

    @SuppressWarnings("deprecation")
    private void openAddStudent() {
        ArrayList<String> ids = new ArrayList<>();
        for (ChenHaoran2024110203Student student : students) ids.add(student.number);
        Intent intent = new Intent(this, ChenHaoran2024110203AddStudentActivity.class);
        intent.putStringArrayListExtra(ChenHaoran2024110203AddStudentActivity.EXTRA_IDS, ids);
        startActivityForResult(intent, ADD_REQUEST);
    }

    @SuppressWarnings("deprecation")
    @Override protected void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);
        if (request != ADD_REQUEST || result != RESULT_OK || data == null) return;
        Object value = data.getSerializableExtra(ChenHaoran2024110203AddStudentActivity.EXTRA_STUDENT);
        if (!(value instanceof ChenHaoran2024110203Student)) return;
        ChenHaoran2024110203Student student = (ChenHaoran2024110203Student) value;
        for (ChenHaoran2024110203Student existing : students) {
            if (existing.number.equals(student.number)) return;
        }
        students.add(student);
        adapter.notifyDataSetChanged();
        updateCount();
        TextView status = findViewById(R.id.list_status);
        status.setText(getString(R.string.added, student.name));
        status.setVisibility(View.VISIBLE);
    }

    private void updateCount() {
        ((TextView) findViewById(R.id.student_count)).setText(getString(R.string.count, students.size()));
    }

    @Override protected void onSaveInstanceState(Bundle out) {
        out.putSerializable(STATE_STUDENTS, new ArrayList<>(students));
        super.onSaveInstanceState(out);
    }
}
