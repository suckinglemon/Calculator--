package com.cmm.dev.calculator__;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Activity_Programmer extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programmer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_programmer, menu);
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
