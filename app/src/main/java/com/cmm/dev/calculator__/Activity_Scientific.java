package com.cmm.dev.calculator__;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Activity_Scientific extends ActionBarActivity {

    // In- / Output
    TextView input;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load required XML
        setContentView(R.layout.activity_scientific);

        // StringBuffer for input string gerneration
        final StringBuffer stringBuffer = new StringBuffer("");

        // load TextView from layout
        input = (TextView) findViewById(R.id.textInput);
        output = (TextView) findViewById(R.id.textOutput);

        // OnClickListener for button equal
        Button buttonEqual = (Button) findViewById(R.id.button_eqal);
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // TODO
            }
        });

        // OnClickListener for button add
        Button buttonAdd = (Button) findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append(" + ");
                input.setText(stringBuffer.toString());
            }
        });

        // OnClickListener for button sub
        Button buttonSub = (Button) findViewById(R.id.button_sub);
        buttonSub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append(" - ");
                input.setText(stringBuffer.toString());
            }
        });

        // OnClickListener for button mul
        Button buttonMul = (Button) findViewById(R.id.button_mul);
        buttonMul.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append(" * ");
                input.setText(stringBuffer.toString());
            }
        });

        // OnClickListener for button div
        Button buttonDiv = (Button) findViewById(R.id.button_div);
        buttonDiv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append(" / ");
                input.setText(stringBuffer.toString());
            }
        });

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

        // OnClickListener for button number 1
        Button buttonNum1 = (Button) findViewById(R.id.button_num_1);
        buttonNum1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('1');
                input.setText(stringBuffer.toString());
            }
        });

        // OnClickListener for button number 2
        Button buttonNum2 = (Button) findViewById(R.id.button_num_2);
        buttonNum2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('2');
                input.setText(stringBuffer.toString());
            }
        });

        // OnClickListener for button number 3
        Button buttonNum3 = (Button) findViewById(R.id.button_num_3);
        buttonNum3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('3');
                input.setText(stringBuffer.toString());
            }
        });

        // OnClickListener for button number 4
        Button buttonNum4 = (Button) findViewById(R.id.button_num_4);
        buttonNum4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('4');
                input.setText(stringBuffer.toString());
            }
        });

        // OnClickListener for button number 5
        Button buttonNum5 = (Button) findViewById(R.id.button_num_5);
        buttonNum5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('5');
                input.setText(stringBuffer.toString());
            }
        });

        // OnClickListener for button number 6
        Button buttonNum6 = (Button) findViewById(R.id.button_num_6);
        buttonNum6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('6');
                input.setText(stringBuffer.toString());
            }
        });

        // OnClickListener for button number 7
        Button buttonNum7 = (Button) findViewById(R.id.button_num_7);
        buttonNum7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('7');
                input.setText(stringBuffer.toString());
            }
        });

        // OnClickListener for button number 8
        Button buttonNum8 = (Button) findViewById(R.id.button_num_8);
        buttonNum8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('8');
                input.setText(stringBuffer.toString());
            }
        });

        // OnClickListener for button number 9
        Button buttonNum9 = (Button) findViewById(R.id.button_num_9);
        buttonNum9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('9');
                input.setText(stringBuffer.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_scientific, menu);
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
