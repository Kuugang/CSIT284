package com.example.androidprojectcollection;

import android.media.audiofx.AutomaticGainControl;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.PriorityBlockingQueue;
public class Calculator {
    public float calculate(String expression, boolean sequential) throws Exception{
        if(expression.equals(""))throw new Exception("Invalid input");

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
                if(sequential){
                    while(!operators.isEmpty() && precedence(operators.peek()) <= precedence(current)){
                        evaluate(operands, operators);
                    }
                }else{
                    while(!operators.isEmpty() && precedence(operators.peek()) >= precedence(current)){
                        evaluate(operands, operators);
                    }
                }
                operators.push(current);
            }
        }

        while(!operands.isEmpty() && !operators.isEmpty()){
            evaluate(operands, operators);
        }

        return operands.pop();
    }

    public void evaluate(Stack<Float> operands, Stack<Character> operators){
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