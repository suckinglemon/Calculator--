package com.cmm.dev.calculator__;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {
    // Konstanten für Fehlermeldungen
    private static final String ERROR = "Error!";
    private static final String PARENTHESIS = "Error! Wrong order of brackets";
    private static final String DIV_BY_ZERO = "Error! Divided by 0!";

    // Konstanten für Links- bzw. Rechtsassoziativität der Operatoren
    private static final int LEFT_ASSOC = 0;
    private static final int RIGHT_ASSOC = 1;

    // Operatoren mit jeweiliger Priorität und Assoziativität
    private static final Map<String, int[]> operators = new HashMap<String, int[]>();

    static {
        operators.put("+", new int[]{0, LEFT_ASSOC});
        operators.put("-", new int[]{0, LEFT_ASSOC});
        operators.put("*", new int[]{1, LEFT_ASSOC});
        operators.put("/", new int[]{1, LEFT_ASSOC});
        operators.put("%", new int[]{1, LEFT_ASSOC});
        operators.put("^", new int[]{2, RIGHT_ASSOC});
    }

    // Prüft ob token ein Operator ist
    private static boolean isOperator(String token) {
        return operators.containsKey(token);
    }

    // Prüft ob token Assoziativität type besitzt
    private static boolean isAssociative(String token, int type) throws IllegalArgumentException {
        if (!isOperator(token)) {
            throw new IllegalArgumentException("Invalid token: " + token);
        }
        return operators.get(token)[1] == type;
    }

    // Vergleicht Prioritäten von token1 und token2
    // Ergebnis < 0 : token2 hat höhere Priorität
    // Ergebnis > 0 : token1 hat höhere Priorität
    private static int cmpPrecedence(String token1, String token2) throws IllegalArgumentException {
        if (!isOperator(token1) || !isOperator(token2)) {
            throw new IllegalArgumentException("Invalid tokens: " + token1
                    + " " + token2);
        }
        return operators.get(token1)[0] - operators.get(token2)[0];
    }

    // Überprüfung der Klammern
    private static boolean parenthesisChecker(String[] expression) {
        Stack<String> stack = new Stack<String>();

        for (String token : expression) {
            if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                if (!stack.isEmpty() && stack.peek().equals("(")) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    // Konvertiert Stringarray in Infix-Notation zu RPN-Notation
    private static String[] convert(String[] inputTokens) {
        ArrayList<String> out = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();
        // Lies alle Tokens aus inputTokens
        for (String token : inputTokens) {
            // Wenn Token = Operator
            if (isOperator(token)) {
                // Wenn schon ein Operator auf dem Stack liegt
                while (!stack.empty() && isOperator(stack.peek())) {
					/*
					 * Wenn token linksassoziativ ist und niedrigere bzw.
					 * gleiche Priorität wie Operator auf Stack hat Oder token
					 * rechtsassoziativ ist und niedrigere Priorität als
					 * Operator auf Stack hat
					 */
                    if ((isAssociative(token, LEFT_ASSOC) && cmpPrecedence(
                            token, stack.peek()) <= 0)
                            || (isAssociative(token, RIGHT_ASSOC) && cmpPrecedence(
                            token, stack.peek()) < 0)) {
                        // Nimm Operator vom Stack und füge ihn zu out hinzu
                        out.add(stack.pop());
                        continue;
                    }
                    break;
                }
                // sonst push Operator auf Stack
                stack.push(token);
                // Wenn token "(" ist dann push auf Stack
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                // Wenn token ")" ist dann nimm solange Operatoren vom stack bis
                // "(" erreicht wird
                while (!stack.empty() && !stack.peek().equals("(")) {
                    out.add(stack.pop());
                }
                // Entferne "(" vom Stack
                stack.pop();
            } else {
                // sonst gebe Zahl an out
                out.add(token);
            }
        }
        // restliche Operatoren vom Stack nehmen und and out geben
        while (!stack.empty()) {
            if (stack.peek().equals("(")) {
                stack.pop();
                continue;
            }
            out.add(stack.pop());
        }
        String[] output = new String[out.size()];
        return out.toArray(output);
    }

    // Prüft Eingabe auf mögliche Fehler
    private static boolean validate(String expression) {
        Pattern pattern = Pattern
                .compile("^[\\)\\*/\\^%\\.]|\\([\\+\\-\\*/\\^%\\.]*\\)|\\)[\\.\\d]*\\(|[\\+\\-\\*/\\^%\\.][\\+\\-\\*/\\^%\\.]|\\d\\.\\d+\\.|\\)[\\.\\d]+|[πe]\\d|[\\+\\-\\(\\*/\\^%\\.]$");
        Matcher matcher = pattern.matcher(expression);
        return !matcher.find();
    }

    // Löst mathematischen Ausdruck
    public static String solve(String expression) throws IllegalArgumentException {
        int precision = 0;        //Ergebnis mit oder ohne Nachkommaanteil

        //Division durch Null
        if (expression.matches(".*[\\dπe]/0[^\\d\\.].*|.*[\\dπe]/0$")) {
            throw new IllegalArgumentException(DIV_BY_ZERO);
        }

        // Bestimmte Kombinationen ersetzen zur leichteren Berechnung
        expression = expression.replaceFirst("^\\-", "0-").replaceFirst("^\\+", "0+")
                .replaceAll("\\(\\-", "(0-").replaceAll("\\(\\+", "(0+")
                .replaceAll("([\\dπe])\\(", "$1*(")
                .replaceAll("(\\d)π", "$1*π")
                .replaceAll("(\\d)e", "$1*e");

        if (!validate(expression)) { // Testen auf mögliche Fehler
            throw new IllegalArgumentException(ERROR);
        }

        //π und e einsetzen
        expression = expression.replaceAll("π", String.valueOf(Math.PI))
                .replaceAll("e", String.valueOf(Math.E));

        if (expression.matches(".*\\..*")) { // Präsenz von Kommas überprüfen
            precision = 1;
        }

        // Aufsplitten des Ausdrucks
        String[] tokens = expression.split("(?<=[\\(\\)\\+\\-*\\/\\^%])|(?=[\\(\\)\\+\\-*\\/\\^%])");

        if (!parenthesisChecker(tokens)) { // Überprüfung der Klammern
            throw new IllegalArgumentException(PARENTHESIS);
        }

        String[] RPN = convert(tokens); // String in Tokens in RPN-Notation
        // aufteilen
        Stack<String> stack = new Stack<String>();

        for (String token : RPN) {
            if (!isOperator(token)) { // Operanden werden auf Stack gepuscht
                stack.push(token);
            } else { // Sobald Operator auftaucht werden 2 Operanden vom Stack
                // genommen und das Ergebnis auf den Stack gepusht
                double operand2 = Double.parseDouble(stack.pop());
                double operand1 = Double.parseDouble(stack.pop());

                double result = token.compareTo("+") == 0 ? operand1 + operand2 :
                        token.compareTo("-") == 0 ? operand1 - operand2 :
                                token.compareTo("*") == 0 ? operand1 * operand2 :
                                        token.compareTo("/") == 0 ? operand1 / operand2 :
                                                token.compareTo("^") == 0 ? Math.pow(operand1, operand2) :
                                                        operand1 % operand2;

                stack.push(String.valueOf(result));    //Falls in Berechnung Kommazahl rauskommt
                if (precision == 0) {                //wird precision auf 1 gesetzt
                    if (result - (long) result != 0.0) {
                        precision = 1;
                    }
                }
            }
        }
        String fresult = stack.pop();
        //Abschneiden des Nachkommaanteils falls precision = 0
        if (precision == 0 && !fresult.matches(".*E.*")) {
            return fresult.replaceFirst("\\.\\d+", "");
        }
        return fresult;
    }
}
