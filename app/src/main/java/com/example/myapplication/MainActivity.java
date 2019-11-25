package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    TextView equationText;
    Button clearBtn, decimalBtn, equals, add, subtract, multiply, divide,  numZero, numOne, numTwo, numThree, numFour, numFive, numSix, numSeven, numEight, numNine;
    ArrayList<Object> equation = new ArrayList<>();
    boolean isSolved = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equationText = findViewById(R.id.equationText);
        clearBtn = findViewById(R.id.clearBtn);
        decimalBtn = findViewById(R.id.decimalBtn);
        equals = findViewById(R.id.equals);
        add = findViewById(R.id.addBtn);
        subtract = findViewById(R.id.minusBtn);
        multiply = findViewById(R.id.multBtn);
        divide = findViewById(R.id.divideBtn);
        numZero = findViewById(R.id.numZero);
        numOne = findViewById(R.id.numOne);
        numTwo = findViewById(R.id.numTwo);
        numThree = findViewById(R.id.numThree);
        numFour = findViewById(R.id.numFour);
        numFive = findViewById(R.id.numFive);
        numSix = findViewById(R.id.numSix);
        numSeven = findViewById(R.id.numSeven);
        numEight = findViewById(R.id.numEight);
        numNine = findViewById(R.id.numNine);

        equationText.setText("");

        OnClick(clearBtn);
        OnClick(equals);
        OnClick(decimalBtn);
        OnClick(add);
        OnClick(subtract);
        OnClick(multiply);
        OnClick(divide);
        OnClick(numZero);
        OnClick(numOne);
        OnClick(numTwo);
        OnClick(numThree);
        OnClick(numFour);
        OnClick(numFive);
        OnClick(numSix);
        OnClick(numSeven);
        OnClick(numEight);
        OnClick(numNine);
    }

    public void OnClick(final Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnString = btn.getText().toString();
                Pattern regex = Pattern.compile("[0-9]");
                Matcher match = regex.matcher(btnString);

                if(isSolved && match.find()){
                    equationText.setText("");
                    equation.clear();
                    isSolved = false;
                }
                else if(isSolved && !btnString.equals("=")){
                    isSolved = false;
                }

                switch(btnString){
                    case "C":
                        equationText.setText("");
                        equation.clear();
                        break;
                    case ".":
                        Append(".");
                        break;
                    case "=":
                        isSolved = true;
                        equationText.setText(String.valueOf(Solve(equation)));
                        double temp = Solve(equation);
                        equation.clear();
                        equation.add(temp);
                        break;
                    case "+":
                        Append("+");
                        break;
                    case "-":
                        Append("-");
                        break;
                    case "*":
                        Append("*");
                        break;
                    case "/":
                        Append("/");
                        break;
                    case "0":
                        Append(0);
                        break;
                    case "1":
                        Append(1);
                        break;
                    case "2":
                        Append(2);
                        break;
                    case "3":
                        Append(3);
                        break;
                    case "4":
                        Append(4);
                        break;
                    case "5":
                        Append(5);
                        break;
                    case "6":
                        Append(6);
                        break;
                    case "7":
                        Append(7);
                        break;
                    case "8":
                        Append(8);
                        break;
                    case "9":
                        Append(9);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void Append(int value){
        equation.add(value);
        equationText.append(Integer.toString(value));
    }

    public void Append(String value){
        equation.add(value);
        equationText.append(value);
    }

    public double Solve(ArrayList<Object> equation){

        StringBuilder num1 = new StringBuilder();
        StringBuilder num2 = new StringBuilder();
        boolean added = false;
        boolean subtracted = false;
        boolean multiplied = false;
        boolean divided = false;
        int numCount = 0;
        for(int i = 0; i < equation.size(); i++){
            switch (equation.get(i).toString()){
                case "+":
                    added = true;
                    i++;
                    numCount++;
                    break;
                case "-":
                    subtracted = true;
                    i++;
                    numCount++;
                    break;
                case "*":
                    multiplied = true;
                    i++;
                    numCount++;
                    break;
                case "/":
                    divided = true;
                    i++;
                    numCount++;
                    break;
                default:
                    break;
            }


            if(added || subtracted || multiplied || divided){
                num2.append(equation.get(i));
            }
            else{
                num1.append(equation.get(i));
            }
        }

        double firstNum;
        double secondNum;

        if(num1.length()> 0){
            firstNum = Double.parseDouble(num1.toString());
        }
        else{
            firstNum = 0;
        }

        if(num2.length()> 0){
            secondNum = Double.parseDouble(num2.toString());
        }
        else{
            secondNum = 0;
        }



        if(added) {
            return firstNum + secondNum;
        }
        else if(subtracted){
            return firstNum - secondNum;
        }
        else if(multiplied){
            return firstNum * secondNum;
        }
        else if(divided){
            return firstNum / secondNum;
        }
        else {
            return firstNum;
        }
    }
}
