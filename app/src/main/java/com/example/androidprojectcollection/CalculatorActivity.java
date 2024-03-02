package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

public class CalculatorActivity extends AppCompatActivity {
    int i;
    ArrayList<Button> numbers = new ArrayList<>();
    ArrayList<Button> operations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        calculate();
    }


    public void calculate(){
        Button clearBtn =  (Button) findViewById(R.id.btnClearAll);
        Button equalBtn =  (Button) findViewById(R.id.btnEqual);
        Button removeBtn =  (Button) findViewById(R.id.btnRemove);
        Button pointBtn =  (Button) findViewById(R.id.btnPoint);
        TextView resultText =  (TextView) findViewById(R.id.txtResult);
        TextView expressionText =  (TextView) findViewById(R.id.txtExpression);

        float value1 = 0;
        float value2 = 0;

        numbers.add( (Button) findViewById(R.id.btnZero));
        numbers.add( (Button) findViewById(R.id.btnOne));
        numbers.add( (Button) findViewById(R.id.btnTwo));
        numbers.add( (Button) findViewById(R.id.btnThree));
        numbers.add( (Button) findViewById(R.id.btnFour));
        numbers.add( (Button) findViewById(R.id.btnFive));
        numbers.add( (Button) findViewById(R.id.btnSix));
        numbers.add( (Button) findViewById(R.id.btnSeven));
        numbers.add( (Button) findViewById(R.id.btnEighth));
        numbers.add( (Button) findViewById(R.id.btnNine));

        operations.add((Button) findViewById(R.id.btnAdd));
        operations.add((Button) findViewById(R.id.btnSubtract));
        operations.add((Button) findViewById(R.id.btnMultiply));
        operations.add((Button) findViewById(R.id.btnDivide));
        operations.add((Button) findViewById(R.id.btnModulo));

        for(int i = 0; i < numbers.size(); i++){
            numbers.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button b = (Button) view;
                    String buttonText = b.getText().toString();
                    expressionText.append(buttonText);
                }
            });
        }

        pointBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                String buttonText = b.getText().toString();
                expressionText.append(buttonText);
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String current = String.valueOf(expressionText.getText());
                if(current.equals(""))return;
                expressionText.setText(current.substring(0, current.length() - 1));
            }
        });

        for(int i = 0; i < operations.size(); i++){
            operations.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button b = (Button) view;
                    String buttonText = b.getText().toString();
                    String current = "" + expressionText.getText();

                    if(current == "") return;

                    Character last = current.charAt(current.length() - 1);

                    if(last == '+' || last == '-' || last == '*' || last == '/' || last == '%'){
                        current = current.substring(0, current.length() - 1) + buttonText;
                        expressionText.setText(current);
                    }else{
                        expressionText.append(buttonText);
                    }
                }
            });
        }
        equalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String expression = expressionText.getText() + "";
                if(expression.equals(""))return;
                Stack<Float> operands = new Stack<Float>();
                Stack<Character> operators = new Stack<Character>();

                for(int i = 0; i < expression.length(); i++){
                    Character current = expression.charAt(i);
                    if(Character.isDigit(current) || current == '.'){
                        StringBuilder sb = new StringBuilder();
                        while(i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')){
                            sb.append(expression.charAt(i));
                            i++;
                        }
                        i--;
                        operands.push(Float.parseFloat(sb.toString()));
                    }else{
                        while(!operators.isEmpty() && precedence(operators.peek()) >= precedence(current)){
                            evaluate(operands, operators);
                        }
                        operators.push(current);
                    }
                }
                while(!operands.isEmpty() && !operators.isEmpty()){
                    evaluate(operands, operators);
                }

                resultText.setText(Float.toString(operands.pop()));
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expressionText.setText("");
                resultText.setText("0");
            }
        });
    }


    public void evaluate(Stack<Float>operands, Stack<Character>operators){
        Character operator = operators.pop();
        Float value2 = operands.pop();
        Float value1 = operands.pop();
        float result = 0;
        switch(operator){
            case '+':
                result = value1 + value2;
                break;
            case '-':
                result = value1 - value2;
                break;
            case '*':
                result = value1 * value2;
                break;
            case '/':
                if(value2 == 0)throw new ArithmeticException("Division by zero");
                result = value1 / value2;
                break;
            case '%':
                result = value1 % value2;
                break;

        }
        operands.push(result);
    }

    public int precedence(Character symbol){
        switch(symbol){
            case '^':
                return 3;
            case '*':
            case '/':
            case '%':
                return 2;
            case '+':
            case '-':
                    return 1;
            default:
                break;
        }
        return 0;
    }

}