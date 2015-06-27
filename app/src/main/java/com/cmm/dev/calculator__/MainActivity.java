package com.cmm.dev.calculator__;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    TextView input;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load required XML
        setContentView(R.layout.activity_main);

        // StringBuffer for input string gerneration
        final StringBuffer stringBuffer = new StringBuffer("");

        // load TextView from layout
        input = (TextView) findViewById(R.id.textInput);
        output = (TextView) findViewById(R.id.textOutput);

        // OnClickListener for button point
        Button buttonPoint = (Button) findViewById(R.id.button_point);
        buttonPoint.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('.');
                input.setText(stringBuffer.toString());
            }
        });

        // OnClickListener for button number 0
        Button buttonNum0 = (Button) findViewById(R.id.button_num_0);
        buttonNum0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('0');
                input.setText(stringBuffer.toString());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Change Activity to Simple Mode if Item selected
        if (id == R.id.action_simple_mode) {
            Intent mode = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mode);
            return true;
        }

        //Change Activity to Scientific Mode if Item selected
        if (id == R.id.action_scientific_mode) {
            Intent mode = new Intent(getApplicationContext(), Activity_Scientific.class);
            startActivity(mode);
            return true;
        }

        //Change Activity to Programmer Mode if Item selected
        if (id == R.id.action_programmer_mode) {
            Intent mode = new Intent(getApplicationContext(), Activity_Programmer.class);
            startActivity(mode);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
