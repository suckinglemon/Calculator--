package com.cmm.dev.calculator__;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {
	// Konstanten für Fehlermeldungen
	private static final String ERROR = "Ungültige Eingabe!";
	private static final String PARENTHESIS = "Klammerpaarung inkorrekt!";

	// Konstanten für Links- bzw. Rechtsassoziativität der Operatoren
	private static final int LEFT_ASSOC = 0;
	private static final int RIGHT_ASSOC = 1;

	// Operatoren mit jeweiliger Priorität und Assoziativität
	private static final Map<String, int[]> operators = new HashMap<String, int[]>();

	static {
		operators.put("+", new int[] { 0, LEFT_ASSOC });
		operators.put("-", new int[] { 0, LEFT_ASSOC });
		operators.put("*", new int[] { 1, LEFT_ASSOC });
		operators.put("/", new int[] { 1, LEFT_ASSOC });
		operators.put("%", new int[] { 1, LEFT_ASSOC });
		operators.put("^", new int[] { 2, RIGHT_ASSOC });
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
		if (operators.get(token)[1] == type) {
			return true;
		}
		return false;
	}

	// Vergleicht Prioritäten von token1 und token2
	// Ergebnis < 0 : token2 hat höhere Priorität
	// Ergebnis > 0 : token1 hat höhere Priorität
	private static final int cmpPrecedence(String token1, String token2) throws IllegalArgumentException {
		if (!isOperator(token1) || !isOperator(token2)) {
			throw new IllegalArgumentException("Invalid tokens: " + token1
					+ " " + token2);
		}
		return operators.get(token1)[0] - operators.get(token2)[0];
	}

	// Überprüfung der Klammern
	public static boolean parenthesisChecker(String[] expression) {
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
				// Wenn schon ein Opertator auf dem Stack liegt
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
				.compile("^[\\)\\*/\\^%\\.]|\\([\\+\\-\\*/\\^%\\.]*\\)|\\)\\.*\\(|[\\+\\-\\*/\\^%\\.][\\+\\-\\*/\\^%\\.]|\\d+\\.\\d\\.|[\\+\\-\\(\\*/\\^%\\.]$");
		Matcher matcher = pattern.matcher(expression);
		if (matcher.find()) {
			return false;
		}
		return true;
	}

	// Löst mathematischen Ausdruck
	public static String solve(String expression) {
		int precision = 0;
		if (!validate(expression)) { // Testen auf mögliche Fehler
			return ERROR;
		}

		// Bestimmte Kombinationen ersetzen zur leichteren Berechnung
		expression = expression.replaceFirst("^\\-", "0-")
				.replaceAll("\\(\\-", "(0-").replaceAll("\\(\\+", "(0+")
				.replaceAll("π", String.valueOf(Math.PI))
				.replaceAll("e", String.valueOf(Math.E));

		if (expression.matches(".*\\..*")) { // Präsenz von Kommas überprüfen
			precision = 1;
		}
		
		// Aufsplitten des Ausdrucks
		String[] tokens = expression.split("(?<=[\\(\\)\\+\\-*\\/\\^%])|(?=[\\(\\)\\+\\-*\\/\\^%])");

		if (!parenthesisChecker(tokens)) { // Überprüfung der Klammern
			return PARENTHESIS;
		}

		String[] RPN = convert(tokens); // String in Tokens in RPN-Notation
										// aufteilen
		Stack<String> stack = new Stack<String>();

		for (String str : RPN) {
			if (!isOperator(str)) { // Operanden werden auf Stack gepuscht
				stack.push(str);
			} else { // Sobald Operator auftaucht werden 2 Operanden vom Stack
						// genommen und das Ergebnis auf den Stack gepusht
				double operand2 = Double.parseDouble(stack.pop());
				double operand1 = Double.parseDouble(stack.pop());

				double result = str.compareTo("+") == 0 ? operand1 + operand2 :
								str.compareTo("-") == 0 ? operand1 - operand2 :
								str.compareTo("*") == 0 ? operand1 * operand2 :
								str.compareTo("/") == 0 ? operand1 / operand2 :
								str.compareTo("^") == 0 ? Math.pow(operand1, operand2) :
								operand1 % operand2;

				stack.push(String.valueOf(result));
				if (precision == 0) {
					if (result - (long) result != 0.0) {
						precision = 1;
					}
				}
			}
		}
		if (precision == 0) {
			return stack.pop().replaceAll("\\.\\d+", "");
		}
		return stack.pop();
	}
/*
	public static void main(String[] args) {

		// Tests
		String term1 = "(3*2.5)+(7-(8/2))";
		String term2 = "1+(3*3)";
		String term3 = "((9+8)*7+((((6/2)-5)*4)+3)-1)";
		String term4 = "10%4";
		String term5 = ".(-12.345+678.90)*0.34-2+432+(-3)";
		String term6 = "(-3*2)^3";
		String term7 = "2^(-8+14";

		System.out.println(solve(term1));

		System.out.println(solve(term2));

		System.out.println(solve(term3));

		System.out.println(solve(term4));

		System.out.println(solve(term5));

		System.out.println(solve(term6));
		
		System.out.println(solve(term7));
	}*/
}
