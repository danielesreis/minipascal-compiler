package compilador;

public class Scanner {
	private char currentChar;
	private byte currentKind;
	private StringBuffer currentSpelling;
        
	private void take(char expectedChar) {
		if (currentChar == expectedChar) {
			currentSpelling.append(currentChar);
			//currentChar = next entry;
		}
		else {
			//throw exception
		}
	}
	private void takeIt() {
		currentSpelling.append(currentChar);
		//currentChar = next entry;
	}
	private boolean isDigit(char c) {
		if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') return true;
                    
		return false;
	}
	private boolean isLetter(char c) {
		if (c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f' || c == 'g' || c == 'h' || c == 'i' || c == 'j' || c == 'k' || c == 'l' || c == 'm' || c == 'n' || c == 'o' || c == 'p' || c == 'q' || c == 'r' || c == 's' || c == 't' || c == 'u' || c == 'v' || 
			c == 'w' || c == 'x' || c == 'y' || c == 'z' || c == 'A' || c == 'B' || c == 'C' || c == 'D' || c == 'E' || c == 'F' || c == 'G' || c == 'H' || c == 'I' || c == 'J' || c == 'K' || c == 'L' || c == 'M' || c == 'N' || c == 'O' || c == 'P' || c == 'Q' || c == 'R' || 
			c == 'S' || c == 'T' || c == 'U' || c == 'V' || c == 'W' || c == 'X' || c == 'Y' || c == 'Z') return true;
		return false;
	}
	private boolean isSymbol(char c) {
		if (c == '!' || c == '@' || c == '#' || c == '$' || c == '%' || c == '¨' || c == '&' || c == '*' || c == '(' || c == ')' || c == '-' || c == '_' || c == '+' || c == '=' || c == '§' || c == '¬' || c == '¢' || c == '£' || c == '³' || 
			c == '²' || c == '¹' || c == '{' || c == '}' || c == '[' || c == ']' || c == 'ª' || c == 'º' || c == '?' || c == '/' || c == '°' || c == ';' || c == ':' || c == ',' || c == '.' || c == '\\' || c == '|' || c == 'ç' || c == '~' || 
			c == '^' || c == '"') return true;
		return false;	
	}
	private boolean isGraphic(char c) {
		if (isDigit(c) || isLetter(c) || isSymbol(c) || c == ' ') return true;
		return false;
	}
	private void scanSeparator() {
		switch(currentChar) {
			case '#': 
				takeIt();
				while(isGraphic(currentChar)) takeIt();
				take('\n');
				break;
			case ' ': takeIt(); break;
			case '\n': takeIt(); break;
			default: //throw error
		}
	}
	private byte scanToken() {
		switch(currentChar) {
			//case letra: tipo-simples, or, and, palavra reservada, bool-lit
			//case digito: float-lit
                        case ':': 
                                takeIt();
                                if (currentChar == '=') {
                                    takeIt();
                                    return Token.BECOMES;
                                }
                                return Token.COLON;
                        case '.': 
                                takeIt();
                                if (currentChar == '.') {
                                    takeIt();
                                    return Token.DOTDOT;
                                }
                                return Token.DOT;
                        case ';': takeIt(); return Token.SEMICOLON;
                        case ',': takeIt(); return Token.COMMA;
                        case '(': takeIt(); return Token.LPAREN;
                        case ')': takeIt(); return Token.RPAREN;
                        case '[': takeIt(); return Token.LBRACE;
                        case ']': takeIt(); return Token.RBRACE;
			case '>': case '<': case '=':  takeIt(); return Token.OP_REL;
			case '+': case '-': takeIt(); return Token.OP_AD; 
			case '*': case '/': takeIt(); return Token.OP_MUL;
                        case '\000': takeIt(); return Token.EOT;
                        default: takeIt(); return Token.ERROR;
		}
	}
}


