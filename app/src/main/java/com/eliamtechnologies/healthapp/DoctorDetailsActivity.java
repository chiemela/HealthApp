package com.eliamtechnologies.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 = {
            {"Doctor Name : John Wick", "Hospital Address : New York", "Exp : 5yrs", "Mobile No:0201123456", "600"},
            {"Doctor Name : Harry Potter", "Hospital Address : London", "Exp : 20yrs", "Mobile No:0202123456", "900"},
            {"Doctor Name : Black Panther", "Hospital Address : Wakanda", "Exp : 7yrs", "Mobile No:0203123456", "300"},
            {"Doctor Name : Spider Man", "Hospital Address : Queens", "Exp : 10yrs", "Mobile No:0204123456", "500"},
            {"Doctor Name : Bat Man", "Hospital Address : Gotham", "Exp : 15yrs", "Mobile No:0205123456", "800"}
    };
    private String[][] doctor_details2 = {
            {"Doctor Name : Iron Man", "Hospital Address : New York", "Exp : 5yrs", "Mobile No:0201123456", "460"},
            {"Doctor Name : Peter Pan", "Hospital Address : London", "Exp : 20yrs", "Mobile No:0202123456", "490"},
            {"Doctor Name : Aqua Man", "Hospital Address : Wakanda", "Exp : 7yrs", "Mobile No:0203123456", "430"},
            {"Doctor Name : Hulk", "Hospital Address : Queens", "Exp : 10yrs", "Mobile No:0204123456", "450"},
            {"Doctor Name : Wonder Woman", "Hospital Address : Gotham", "Exp : 15yrs", "Mobile No:0205123456", "480"}
    };
    private String[][] doctor_details3 = {
            {"Doctor Name : Cat Woman", "Hospital Address : New York", "Exp : 5yrs", "Mobile No:0201123456", "360"},
            {"Doctor Name : Flash", "Hospital Address : London", "Exp : 20yrs", "Mobile No:0202123456", "390"},
            {"Doctor Name : Captain America", "Hospital Address : Wakanda", "Exp : 7yrs", "Mobile No:0203123456", "330"},
            {"Doctor Name : Vision", "Hospital Address : Queens", "Exp : 10yrs", "Mobile No:0204123456", "350"},
            {"Doctor Name : Thor", "Hospital Address : Gotham", "Exp : 15yrs", "Mobile No:0205123456", "380"}
    };
    private String[][] doctor_details4 = {
            {"Doctor Name : Chris Pratt", "Hospital Address : New York", "Exp : 5yrs", "Mobile No:0201123456", "260"},
            {"Doctor Name : DeadPool", "Hospital Address : London", "Exp : 20yrs", "Mobile No:0202123456", "290"},
            {"Doctor Name : Wolverine", "Hospital Address : Wakanda", "Exp : 7yrs", "Mobile No:0203123456", "230"},
            {"Doctor Name : Magneto", "Hospital Address : Queens", "Exp : 10yrs", "Mobile No:0204123456", "250"},
            {"Doctor Name : Venom", "Hospital Address : Gotham", "Exp : 15yrs", "Mobile No:0205123456", "280"}
    };
    private String[][] doctor_details5 = {
            {"Doctor Name : SandMan", "Hospital Address : New York", "Exp : 5yrs", "Mobile No:0201123456", "160"},
            {"Doctor Name : Ant Man", "Hospital Address : London", "Exp : 20yrs", "Mobile No:0202123456", "190"},
            {"Doctor Name : Groot", "Hospital Address : Wakanda", "Exp : 7yrs", "Mobile No:0203123456", "130"},
            {"Doctor Name : Odin", "Hospital Address : Queens", "Exp : 10yrs", "Mobile No:0204123456", "150"},
            {"Doctor Name : Mobius", "Hospital Address : Gotham", "Exp : 15yrs", "Mobile No:0205123456", "180"}
    };
    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonCartBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Physician") == 0)
            doctor_details = doctor_details1;
        else
        if(title.compareTo("Dietitian") == 0)
            doctor_details = doctor_details2;
        else
        if(title.compareTo("Dentist") == 0)
            doctor_details = doctor_details3;
        else
        if(title.compareTo("Surgeon") == 0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        // this gets the objects in the array and puts it in a HashMap
        list = new ArrayList();
        for(int i = 0; i < doctor_details.length; i++){
            item = new HashMap<String, String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons fees: £"+doctor_details[i][4]);
            list.add(item);
        }

        // this adapter populates the "ListView" in "multi_lines" layout file
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst = findViewById(R.id.listViewCart);
        lst.setAdapter(sa);

        // this parses "doctor_details" from "DoctorDetailsActivity" to "BookAppointmentActivity" and starts the intent
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[position][0]);
                it.putExtra("text3", doctor_details[position][1]);
                it.putExtra("text4", doctor_details[position][3]);
                it.putExtra("text5", doctor_details[position][4]);
                startActivity(it);
            }
        });
    }
}