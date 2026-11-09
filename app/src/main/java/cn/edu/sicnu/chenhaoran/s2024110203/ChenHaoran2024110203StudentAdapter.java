package cn.edu.sicnu.chenhaoran.s2024110203;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public final class ChenHaoran2024110203StudentAdapter extends ArrayAdapter<ChenHaoran2024110203Student> {
    public ChenHaoran2024110203StudentAdapter(Context context, List<ChenHaoran2024110203Student> students) {
        super(context, R.layout.student_row, students);
    }

    @Override public View getView(int position, View row, ViewGroup parent) {
        if (row == null) row = LayoutInflater.from(getContext()).inflate(R.layout.student_row, parent, false);
        ChenHaoran2024110203Student student = getItem(position);
        if (student == null) return row;
        ((TextView) row.findViewById(R.id.row_name)).setText(student.name);
        ((TextView) row.findViewById(R.id.row_id)).setText(getContext().getString(R.string.row_id, student.number));
        String gender = getContext().getString(student.female ? R.string.female : R.string.male);
        ((TextView) row.findViewById(R.id.row_details)).setText(
                getContext().getString(R.string.row_details, gender, student.className));
        return row;
    }
}
