package com.eliamtechnologies.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartBuyMedicineActivity extends AppCompatActivity {

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    TextView tvTotal;
    ListView lst;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, btnCheckout, btnBack;
    private String[][] packages = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine);

        dateButton = findViewById(R.id.buttonBMCartDate);
        btnCheckout = findViewById(R.id.buttonBMCartCheckout);
        btnBack = findViewById(R.id.buttonBMCartBack);
        tvTotal = findViewById(R.id.textViewBMCartTotalCost);
        lst = findViewById(R.id.listViewBMCart);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();

        // get instance of the Database "Userdata"
        Database db = new Database(getApplicationContext(), "Userdata", null, 1);

        // fetch "cart" data and store it in ArrayList
        float totalAmount = 0;
        ArrayList dbData = db.getCartData(username, "medicine");
        // Toast.makeText(getApplicationContext(), "" + dbData, Toast.LENGTH_SHORT).show();

        // this will get the result from database and add them to "packages" array
        packages = new String[dbData.size()][];
        for(int i = 0; i < packages.length; i++){
            packages[i] = new String[5];
        }

        // this will split the string result in "packages" array with the help of the "$" sign
        for(int i = 0; i < dbData.size(); i++){
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0] = strData[0];
            packages[i][4] = "Cost : £" + strData[i];
            totalAmount = totalAmount + Float.parseFloat(strData[1]);
        }
        // display the "totalAmount" in "tvTotal"
        tvTotal.setText("Total Cost :" + totalAmount);

        // map the items in "packages" into "list" array object
        list = new ArrayList();
        for (int i = 0; i < packages.length; i++){
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]);
            list.add(item);
        }

        // set the "list" array object to "multi_lines" layout using adapter
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

        // display the "sa" adapter to ListView "lst"
        lst.setAdapter(sa);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartBuyMedicineActivity.this, BuyMedicineActivity.class));
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CartBuyMedicineActivity.this, BuyMedicineBookActivity.class);
                it.putExtra("price", tvTotal.getText());
                it.putExtra("date", dateButton.getText());
                startActivity(it);
            }
        });

        // datepicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

    }


    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                dateButton.setText(dayOfMonth + "/" + month + "/" + year);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }
}