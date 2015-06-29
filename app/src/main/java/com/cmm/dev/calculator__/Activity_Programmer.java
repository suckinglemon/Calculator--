package com.cmm.dev.calculator__;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Activity_Programmer extends ActionBarActivity {

    // In- / Output
    TextView input;
    TextView output;

    // StringBuffer for input string generation
    StringBuffer stringBuffer;

    // Active input mode: 1 = DEZ, 2 = BIN, 3 = HEX
    int inputMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load required XML
        setContentView(R.layout.activity_programmer);

        // new StringBuffer for input string generation
        stringBuffer = new StringBuffer("");

        // load TextView from layout
        input = (TextView) findViewById(R.id.textInput);
        output = (TextView) findViewById(R.id.textOutput);

        // Set standard input mode
        inputMode = 1;

        // OnClickListener for button to dez
        Button buttonToDez = (Button) findViewById(R.id.button_to_dez);
        buttonToDez.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (inputMode == 1) {
                    output.setText("= from dez to dez");
                } else if (inputMode == 2) {
                    output.setText("= from bin to dez");
                } else if (inputMode == 3) {
                    output.setText("= from hex to dez");
                }
            }
        });

        // OnClickListener for button to bin
        Button buttonToBin = (Button) findViewById(R.id.button_to_bin);
        buttonToBin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (inputMode == 1) {
                    output.setText("= from dez to bin");
                } else if (inputMode == 2) {
                    output.setText("= from bin to bin");
                } else if (inputMode == 3) {
                    output.setText("= from hex to bin");
                }
            }
        });

        // OnClickListener for button to hex
        Button buttonToHex = (Button) findViewById(R.id.button_to_hex);
        buttonToHex.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (inputMode == 1) {
                    output.setText("= from dez to hex");
                } else if (inputMode == 2) {
                    output.setText("= from bin to hex");
                } else if (inputMode == 3) {
                    output.setText("= from hex to hex");
                }
            }
        });

        // OnClickListener for button number a
        Button buttonNumA = (Button) findViewById(R.id.button_alph_a);
        buttonNumA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('A');
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button number b
        Button buttonNumB = (Button) findViewById(R.id.button_alph_b);
        buttonNumB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('B');
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button number c
        Button buttonNumC = (Button) findViewById(R.id.button_alph_c);
        buttonNumC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('C');
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button number d
        Button buttonNumD = (Button) findViewById(R.id.button_alph_d);
        buttonNumD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('D');
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button number e
        Button buttonNumE = (Button) findViewById(R.id.button_alph_e);
        buttonNumE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('E');
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button number f
        Button buttonNumF = (Button) findViewById(R.id.button_alph_f);
        buttonNumF.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('F');
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // Same as Simple
        // OnClickListener for button equal
        Button buttonEqual = (Button) findViewById(R.id.button_eqal);
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // TODO
                output.setText("= ");
            }
        });

        // OnClickListener for button add
        Button buttonAdd = (Button) findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append("+");
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button sub
        Button buttonSub = (Button) findViewById(R.id.button_sub);
        buttonSub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append("-");
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button mul
        Button buttonMul = (Button) findViewById(R.id.button_mul);
        buttonMul.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append("*");
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button div
        Button buttonDiv = (Button) findViewById(R.id.button_div);
        buttonDiv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append("/");
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button clear
        Button buttonC = (Button) findViewById(R.id.button_c);
        buttonC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                int l = stringBuffer.length() - 1;
                if (l > 0) {
                    stringBuffer.deleteCharAt(l);
                    input.setText(stringBuffer.toString());
                    output.setText(" ");
                } else if (l == 0){
                    stringBuffer.deleteCharAt(0);
                    input.setText(" ");
                    output.setText(" ");
                } else {
                    input.setText(" ");
                    output.setText(" ");
                }
            }
        });

        // OnClickListener for button clear entirely
        Button buttonCE = (Button) findViewById(R.id.button_ce);
        buttonCE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer = new StringBuffer();
                input.setText(" ");
                output.setText(" ");
            }
        });

        // OnClickListener for button point
        Button buttonPoint = (Button) findViewById(R.id.button_point);
        buttonPoint.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('.');
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button number 0
        Button buttonNum0 = (Button) findViewById(R.id.button_num_0);
        buttonNum0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('0');
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button number 1
        Button buttonNum1 = (Button) findViewById(R.id.button_num_1);
        buttonNum1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('1');
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button number 2
        Button buttonNum2 = (Button) findViewById(R.id.button_num_2);
        buttonNum2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('2');
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button number 3
        Button buttonNum3 = (Button) findViewById(R.id.button_num_3);
        buttonNum3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('3');
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button number 4
        Button buttonNum4 = (Button) findViewById(R.id.button_num_4);
        buttonNum4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('4');
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button number 5
        Button buttonNum5 = (Button) findViewById(R.id.button_num_5);
        buttonNum5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('5');
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button number 6
        Button buttonNum6 = (Button) findViewById(R.id.button_num_6);
        buttonNum6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('6');
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button number 7
        Button buttonNum7 = (Button) findViewById(R.id.button_num_7);
        buttonNum7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('7');
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button number 8
        Button buttonNum8 = (Button) findViewById(R.id.button_num_8);
        buttonNum8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('8');
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button number 9
        Button buttonNum9 = (Button) findViewById(R.id.button_num_9);
        buttonNum9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append('9');
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });
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

        //Change Activity to Programmer Mode decimal if Item selected
        if (id == R.id.action_programmer_mode_dez) {
            // Load required XML
            setContentView(R.layout.activity_programmer);

            /*
            // load TextView from layout
            input = (TextView) findViewById(R.id.textInput);
            output = (TextView) findViewById(R.id.textOutput);
            */

            inputMode = 1;

            return true;
        }

        //Change Activity to Programmer Mode binary if Item selected
        if (id == R.id.action_programmer_mode_bin) {
            // Load required XML
            setContentView(R.layout.activity_programmer_bin);

            /*
            // load TextView from layout
            input = (TextView) findViewById(R.id.textInput);
            output = (TextView) findViewById(R.id.textOutput);
            */

            inputMode = 2;

            return true;
        }

        //Change Activity to Programmer Mode hexadecimal if Item selected
        if (id == R.id.action_programmer_mode_hex) {
            // Load required XML
            setContentView(R.layout.activity_programmer_hex);

            /*
            // load TextView from layout
            input = (TextView) findViewById(R.id.textInput);
            output = (TextView) findViewById(R.id.textOutput);
            */

            inputMode = 3;

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
