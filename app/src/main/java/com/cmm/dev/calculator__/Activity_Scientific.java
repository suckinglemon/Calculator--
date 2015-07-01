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

    // StringBuffer for input string generation
    StringBuffer stringBuffer;

    // History
    History history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load required XML
        setContentView(R.layout.activity_scientific);

        // new StringBuffer for input string generation
        stringBuffer = new StringBuffer("");

        // load TextView from layout
        input = (TextView) findViewById(R.id.textInput);
        output = (TextView) findViewById(R.id.textOutput);

        // New History
        history = new History();

        setOnClickListeners();

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

        //Change Activity to Programmer Mode if Item selected
        if (id == R.id.action_programmer_mode) {
            Intent mode = new Intent(getApplicationContext(), Activity_Programmer.class);
            startActivity(mode);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setOnClickListeners () {
        // OnClickListener for button sinus
        Button buttonSin = (Button) findViewById(R.id.button_sin);
        buttonSin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                double resultNumeric;
                String resultString = " ";
                try {
                    resultString = Interpreter.solve(stringBuffer.toString());
                    resultNumeric = Double.parseDouble(resultString);
                    output.setText("SIN(" + stringBuffer.toString() + ") = " + Math.sin(resultNumeric));
                } catch (NumberFormatException nfe) {
                    output.setText(resultString);
                } catch (Exception e) {
                    output.setText("Error!");
                }
            }
        });

        // OnClickListener for button cosinus
        Button buttonCos = (Button) findViewById(R.id.button_cos);
        buttonCos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                double resultNumeric;
                String resultString = " ";
                try {
                    resultString = Interpreter.solve(stringBuffer.toString());
                    resultNumeric = Double.parseDouble(resultString);
                    output.setText("COS(" + stringBuffer.toString() + ") = " + Math.cos(resultNumeric));
                } catch (NumberFormatException nfe) {
                    output.setText(resultString);
                } catch (Exception e) {
                    output.setText("Error!");
                }
            }
        });

        // OnClickListener for button tangens
        Button buttonTan = (Button) findViewById(R.id.button_tan);
        buttonTan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                double resultNumeric;
                String resultString = " ";
                try {
                    resultString = Interpreter.solve(stringBuffer.toString());
                    resultNumeric = Double.parseDouble(resultString);
                    output.setText("TAN(" + stringBuffer.toString() + ") = " + Math.tan(resultNumeric));
                } catch (NumberFormatException nfe) {
                    output.setText(resultString);
                } catch (Exception e) {
                    output.setText("Error!");
                }
            }
        });

        // OnClickListener for button log
        Button buttonLog = (Button) findViewById(R.id.button_log);
        buttonLog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                double resultNumeric;
                String resultString = " ";
                try {
                    resultString = Interpreter.solve(stringBuffer.toString());
                    resultNumeric = Double.parseDouble(resultString);
                    output.setText("LOG(" + stringBuffer.toString() + ") = " + Math.log10(resultNumeric));
                } catch (NumberFormatException nfe) {
                    output.setText(resultString);
                } catch (Exception e) {
                    output.setText("Error!");
                }
            }
        });

        // OnClickListener for button square root
        Button buttonSqrt = (Button) findViewById(R.id.button_sqrt);
        buttonSqrt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                double resultNumeric;
                String resultString = " ";
                try {
                    resultString = Interpreter.solve(stringBuffer.toString());
                    resultNumeric = Double.parseDouble(resultString);
                    output.setText("√(" + stringBuffer.toString() + ") = " + Math.sqrt(resultNumeric));
                } catch (NumberFormatException nfe) {
                    output.setText(resultString);
                } catch (Exception e) {
                    output.setText("Error!");
                }
            }
        });

        // OnClickListener for button power
        Button buttonPow = (Button) findViewById(R.id.button_pow);
        buttonPow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append("^");
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button open bracket
        Button buttonOpenBracket = (Button) findViewById(R.id.button_open_bracket);
        buttonOpenBracket.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append("(");
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button close bracket
        Button buttonCloseBracket = (Button) findViewById(R.id.button_close_bracket);
        buttonCloseBracket.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append(")");
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button pi
        Button buttonPi = (Button) findViewById(R.id.button_pi);
        buttonPi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append("π");
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });

        // OnClickListener for button e
        Button buttonE = (Button) findViewById(R.id.button_e);
        buttonE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                stringBuffer.append("e");
                input.setText(stringBuffer.toString());
                output.setText(" ");
            }
        });


        // Same as Simple
        // OnClickListener for button history pre
        Button buttonHistoryPre = (Button) findViewById(R.id.button_history_pre);
        buttonHistoryPre.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                try {
                    // Load result from History
                    String[] hist = history.getPreElement().split(" ");
                    output.setText("= " + hist[1]);
                    // Load term from History
                    input.setText(hist[0]);
                } catch (Exception e) {
                    output.setText(e.getMessage());
                }
            }
        });

        // OnClickListener for button history next
        Button buttonHistoryNext = (Button) findViewById(R.id.button_history_nex);
        buttonHistoryNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                try {
                    // Load result from History
                    String[] hist = history.getNextElement().split(" ");
                    output.setText("= " + hist[1]);
                    // Load term from History
                    input.setText(hist[0]);
                } catch (Exception e) {
                    output.setText(e.getMessage());
                }
            }
        });

        // OnClickListener for button equal
        Button buttonEqual = (Button) findViewById(R.id.button_eqal);
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                try {
                    // Solve, set output string and add result to History
                    String result = Interpreter.solve(stringBuffer.toString());
                    output.setText("= " + result);
                    // Add term to History
                    history.addElement(stringBuffer.toString() + " " + result);
                } catch (Exception e) {
                    output.setText(e.getMessage());
                }
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
                stringBuffer = new StringBuffer("");
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
}
