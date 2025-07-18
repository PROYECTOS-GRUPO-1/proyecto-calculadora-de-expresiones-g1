package src;

import java.util.Stack;

public class Calculadora {

    public static double evaluar(String expresion) {
        try {
            return evaluarPostfijo(infixToPostfix(expresion));
        } catch (Exception e) {
            throw new IllegalArgumentException("Expresión inválida");
        }
    }

    private static String infixToPostfix(String expresion) {
        StringBuilder resultado = new StringBuilder();
        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            if (Character.isDigit(c)) {
                resultado.append(c);
                while (i + 1 < expresion.length() && (Character.isDigit(expresion.charAt(i + 1)) || expresion.charAt(i + 1) == '.')) {
                    i++;
                    resultado.append(expresion.charAt(i));
                }
                resultado.append(' ');
            } else if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    resultado.append(pila.pop()).append(' ');
                }
                pila.pop(); // saca el '('
            } else if (esOperador(c)) {
                while (!pila.isEmpty() && prioridad(pila.peek()) >= prioridad(c)) {
                    resultado.append(pila.pop()).append(' ');
                }
                pila.push(c);
            }
        }

        while (!pila.isEmpty()) {
            resultado.append(pila.pop()).append(' ');
        }

        return resultado.toString();
    }

    private static double evaluarPostfijo(String postfijo) {
        Stack<Double> pila = new Stack<>();
        String[] tokens = postfijo.split(" ");

        for (String token : tokens) {
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                pila.push(Double.parseDouble(token));
            } else if (token.length() == 1 && esOperador(token.charAt(0))) {
                double b = pila.pop();
                double a = pila.pop();
                switch (token.charAt(0)) {
                    case '+': pila.push(a + b); break;
                    case '-': pila.push(a - b); break;
                    case '*': pila.push(a * b); break;
                    case '/': pila.push(a / b); break;
                }
            }
        }
        return pila.pop();
    }

    private static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int prioridad(char operador) {
        if (operador == '+' || operador == '-') return 1;
        if (operador == '*' || operador == '/') return 2;
        return 0;
    }
} 
