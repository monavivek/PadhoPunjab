package c2d.com.codetodevelop.javaClass;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
     //   Toast.makeText(parent.getContext(), "You Selected :"+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
