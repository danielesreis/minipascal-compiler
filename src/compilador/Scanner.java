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
			c == 'w' || c == 'x' || c == 'y' || c == 'z' ) return true;
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
        
        private Token scan() {
            byte kind;
            StringBuffer spelling;
            
            while(currentChar == '#' || currentChar == ' ' || currentChar == '\n')
                scanSeparator();
            
            spelling = new StringBuffer("");
            kind = scanToken();
            
            return new Token(kind, spelling.toString());
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
            StringBuffer palavra = new StringBuffer("");
            
		switch(currentChar) {
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
                                else if (isDigit(currentChar)) {
                                    takeIt();
                                    return Token.FLOAT_LIT;
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
                        case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': case 'h': case 'i': 
                        case 'j': case 'k': case 'l': case 'm': case 'n': case 'o': case 'p': case 'q': case 'r': 
                        case 's': case 't': case 'u': case 'v': case 'w': case 'x': case 'y': case 'z':
                            
                            while (isLetter(currentChar) || isDigit(currentChar)){
                                palavra.append(currentChar);
                                takeIt();
                            }
                            
                            if (palavra.toString().equals("true") || palavra.toString().equals("false")) return Token.BOOL_LIT;
                            if (palavra.toString().equals("integer") || palavra.toString().equals("real") || palavra.toString().equals("boolean")) return Token.TIPO_SIMPLES;
                            if (palavra.toString().equals("and")) return Token.OP_MUL;
                            if (palavra.toString().equals("or")) return Token.OP_AD;
                            return Token.ID;
                            
                        case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8':
                        case '9': 
                            while (isDigit(currentChar)) takeIt(); 
                            
                            if (currentChar == '.') {
                                takeIt();
                                return Token.FLOAT_LIT;
                            }
                            
                            return Token.INT_LIT;
                            
                        default: takeIt(); return Token.ERROR;
		}
	}
}



