import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.*;

public class Interpreter {
	// Konstanten für Links- bzw. Rechtsassoziativität der Operatoren
	private static final int LEFT_ASSOC = 0;
	private static final int RIGHT_ASSOC = 1;

	// Operatoren mit jeweiliger Priorität und Assoziativität
	private static final Map<String, int[]> operators = new HashMap<String, int[]>();

	static {
		operators.put("+", new int[] { 0, LEFT_ASSOC });
		operators.put("-", new int[] { 0, LEFT_ASSOC });
		operators.put("*", new int[] { 5, LEFT_ASSOC });
		operators.put("/", new int[] { 5, LEFT_ASSOC });
		operators.put("%", new int[] { 5, LEFT_ASSOC });
		operators.put("^", new int[] { 10, RIGHT_ASSOC });
	}

	// Prüft ob token ein Operator ist
	private static boolean isOperator(String token) {
		return operators.containsKey(token);
	}

	// Prüft ob token Assoziativität type besitzt
	private static boolean isAssociative(String token, int type)
			throws IllegalArgumentException {
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
	private static final int cmpPrecedence(String token1, String token2)
			throws IllegalArgumentException {
		if (!isOperator(token1) || !isOperator(token2)) {
			throw new IllegalArgumentException("Invalid tokens: " + token1
					+ " " + token2);
		}
		return operators.get(token1)[0] - operators.get(token2)[0];
	}

	//Konvertiert Stringarray in Infix-Notation zu RPN-Notation
	public static String[] convert(String[] inputTokens) {
		ArrayList<String> out = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		// Lies alle Tokens aus inputTokens
		for (String token : inputTokens) {
			// Wenn Token = Operator
			if (isOperator(token)) {
				// Wenn schon ein Opertator auf dem Stack liegt
				while (!stack.empty() && isOperator(stack.peek())) {
					/* Wenn token linksassoziativ ist und niedrigere bzw.
					gleiche Priorität wie Operator auf Stack hat
					Oder token rechtsassoziativ ist und niedrigere Priorität
					als Operator auf Stack hat */
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
				//Entferne "(" vom Stack
				stack.pop();
			} else {
				//sonst gebe Zahl an out
				out.add(token);
			}
		}
		//restliche Operatoren vom Stack nehmen und and out geben
		while (!stack.empty()) {
			out.add(stack.pop());
		}
		String[] output = new String[out.size()];
		return out.toArray(output);
	}
	
	//Trennt String zurzeit noch durch Leerzeichen und löst Term
	public static double solve(String term){
		String[] tokens = term.split(" ");
		if(!parentChecker(tokens)){ //Überprüfung der Klammern
			return -1.0;
		}
		String[] RPN = convert(tokens); // String in Tokens in RPN-Notation aufteilen
		Stack<String> stack = new Stack<String>();
		
		for (String str : RPN) {
			System.out.print(str + " ");
		}
		
		for (String str : RPN) {
			if(!isOperator(str)){
				stack.push(str);
			}
			else{
				double operand2 = Double.parseDouble(stack.pop());
				double operand1 = Double.parseDouble(stack.pop());
				
				double result = str.compareTo("+") == 0 ? operand1 + operand2 :   
                    			str.compareTo("-") == 0 ? operand1 - operand2 :  
                    			str.compareTo("*") == 0 ? operand1 * operand2 :  
                    			str.compareTo("/") == 0 ? operand1 / operand2 :
                    			operand1 % operand2;
				
				stack.push(String.valueOf(result));
			}
		}
		return Double.valueOf(stack.pop());
	}

	//Überprüfung der Klammern
	public static boolean parentChecker(String[] term){
		Stack<String> stack = new Stack<String>();
		
		for (String token : term) {
			if(token.equals("(")){
				stack.push(token);
			}
			else if(token.equals(")")){
				if(!stack.isEmpty() && stack.peek().equals("(")){
					stack.pop();
				}
				else{
					return false;
				}
			}
		}
		return true;
	}
	//Noch nicht fertig!
	private static String[] filter(String term){
		ArrayList<String> matches = new ArrayList<String>();
		Pattern pattern = Pattern.compile("(\\d+\\.)?\\d+|[?:^\\(][\\+\\-\\*/]|\\b[\\+\\-\\*/]|[?:\\(]\\-(\\d+\\.)?\\d+[?:\\)]");
		//Pattern pattern = Pattern.compile("(\\d+\\.)?\\d+|(?:[\\)])[\\+\\-\\*/]|[?:\\(]\\-(\\d+\\.)?\\d+[?:\\)]");
		//Pattern pattern = Pattern.compile("(\\d+\\.\\d+)|(\\d+)|([\\+\\-\\*/\\(\\)])");
		Matcher matcher = pattern.matcher(term);
		
		while(matcher.find()){
			matches.add(matcher.group());
		}
		String[] out = new String[matches.size()];
		return matches.toArray(out);
	}
	
	public static void main(String[] args) {
		
		//Tests
		String term1 = "( 3 * 2.5 ) + ( 7 - ( 8 / 2 ) )";
		String term2 = "1 + ( 3 * 3 )";
		String term3 = "( ( 9 + 8 ) * 7 + ( ( ( ( 6 / 2 ) - 5 ) * 4 ) + 3 ) - 1 )";
		String term4 = "10 % 4";
		String term5 = "(12.345+678.90)*0.34-2+432+(-3)";
		
		double r = solve(term1);
		System.out.println("= " + r);
		r = solve(term2);
		System.out.println("= " + r);
		r = solve(term3);
		System.out.println("= " + r); 
		r = solve(term4);
		System.out.println("= " + r);
		
		String[] tokens = filter(term5);
		for (String token : tokens) {
			System.out.println(token);
		}
	}
}

