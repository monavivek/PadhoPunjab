package c2d.com.codetodevelop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import c2d.com.codetodevelop.javaClass.CustomOnItemSelectedListener;

public class SelectGrade extends AppCompatActivity {
    Spinner spinnergrade,spinnersubject;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_grade);
        getSupportActionBar().hide(); //hide the title bar

      addItemOnSubject();
      addListenerOnButton();
      addListenerOnSpinnerItemSelection();
    }
    public void addItemOnSubject()
    {
        spinnersubject=(Spinner)findViewById(R.id.subjectspinner);
        List<String> list= new ArrayList<String>();
        list.add("RationalNumber");
        list.add("Algebra");
        list.add("Integers");
        list.add("Fractions");
        list.add("Decimal");
        list.add("DataHandling");
        list.add("WholeNumbers");
        ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersubject.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection()
    {
        spinnergrade=(Spinner)findViewById(R.id.classspinner);
        spinnergrade.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListenerOnButton()
    {
        spinnergrade=(Spinner)findViewById(R.id.classspinner);
        spinnersubject=(Spinner)findViewById(R.id.subjectspinner);
        submit=(Button)findViewById(R.id.btn_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SelectGrade.this,SelectLevel.class);
                startActivity(intent);

            }
        });
    }
}