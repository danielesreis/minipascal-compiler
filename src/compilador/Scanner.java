package compilador;

import java.io.IOException;

public class Scanner {
	private char currentChar;
	private StringBuffer currentSpelling;
        private boolean eotFlag;
        
        public Scanner(char currentChar) {
            this.currentChar = currentChar;
            this.eotFlag = false;
            currentSpelling = new StringBuffer("");
        }
        
	private void take(char expectedChar)  {
            if (currentChar == expectedChar) {
                currentSpelling.append(currentChar);
                Compilador.currentIndex = Compilador.currentIndex + 1;
                
                if (Compilador.currentIndex != Compilador.code.length()) 
                    currentChar = Compilador.code.charAt(Compilador.currentIndex);
                
                else this.eotFlag = true;     
            }
            else {
                Compilador.compilerFrame.setOutputText("Caractere inválido!");
            }
	}
        
	private void takeIt() {
            currentSpelling.append(currentChar);
            Compilador.currentIndex = Compilador.currentIndex + 1;
            
            if (Compilador.currentIndex != Compilador.code.length()) 
                    currentChar = Compilador.code.charAt(Compilador.currentIndex);
            
            else this.eotFlag = true;
	}
        
        public Token scan() {
            byte kind;
            
            while(currentChar == '#' || currentChar == ' ' || currentChar == '\n' || currentChar == '\t')
                scanSeparator();
            
            currentSpelling = new StringBuffer("");            
            kind = scanToken();
            return new Token(kind, currentSpelling.toString());
        }
        
	private void scanSeparator() {
		switch(currentChar) {
			case '#': 
				takeIt();
				while(isGraphic(currentChar)) takeIt();
				take('\n');
				break;
			case ' ': case '\t': case '\n': takeIt(); break;
			default: Compilador.compilerFrame.setOutputText("Separador inválido!");
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
                                
                                if (eotFlag) 
                                    return Token.EOT;
                                
                                else {
                                    if (currentChar == '.') {
                                        takeIt();
                                        return Token.DOTDOT;
                                    }
                                    else if (isDigit(currentChar)) {
                                        takeIt();
                                        return Token.FLOAT_LIT;
                                    }   
                                    return Token.DOT;
                                }
                                
                        case ';': takeIt(); return Token.SEMICOLON;
                        case ',': takeIt(); return Token.COMMA;
                        case '(': takeIt(); return Token.LPAREN;
                        case ')': takeIt(); return Token.RPAREN;
                        case '[': takeIt(); return Token.LBRACE;
                        case ']': takeIt(); return Token.RBRACE;
			case '>': case '<': case '=':  takeIt(); return Token.OP_REL;
			case '+': case '-': takeIt(); return Token.OP_AD; 
			case '*': case '/': takeIt(); return Token.OP_MUL;
                        case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': case 'h': case 'i': 
                        case 'j': case 'k': case 'l': case 'm': case 'n': case 'o': case 'p': case 'q': case 'r': 
                        case 's': case 't': case 'u': case 'v': case 'w': case 'x': case 'y': case 'z': case 'A': 
                        case 'B': case 'C': case 'D': case 'E': case 'F': case 'G': case 'H': case 'I': case 'J': 
                        case 'K': case 'L': case 'M': case 'N': case 'O': case 'P': case 'Q': case 'R': case 'S': 
                        case 'T': case 'U': case 'V': case 'W': case 'X': case 'Y': case 'Z':
                            
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
        
        private boolean isDigit(char c)  {
		if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') return true;
		return false;
	}
        
	private boolean isLetter(char c)  {
		if (c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f' || c == 'g' || c == 'h' || c == 'i' || c == 'j' || c == 'k' || c == 'l' || c == 'm' || c == 'n' 
                   || c == 'o' || c == 'p' || c == 'q' || c == 'r' || c == 's' || c == 't' || c == 'u' || c == 'v' || c == 'w' || c == 'x' || c == 'y' || c == 'z' || c == 'A' || c == 'B' 
                   || c == 'C' || c == 'D' || c == 'E' || c == 'F' || c == 'G' || c == 'H' || c == 'I' || c == 'J' || c == 'K' || c == 'L' || c == 'M' || c == 'N' || c == 'O' || c == 'P' 
                   || c == 'Q' || c == 'R' || c == 'S' || c == 'T' || c == 'U' || c == 'V' || c == 'W' || c == 'X' || c == 'Y' || c == 'Z' ) return true;
		return false;
	}
        
	private boolean isSymbol(char c)  {
		if (c == '!' || c == '@' || c == '#' || c == '$' || c == '%' || c == '¨' || c == '&' || c == '*' || c == '(' || c == ')' || c == '-' || c == '_' || c == '+' || c == '=' || c == '§' || c == '¬' || c == '¢' || c == '£' || c == '³' || 
			c == '²' || c == '¹' || c == '{' || c == '}' || c == '[' || c == ']' || c == 'ª' || c == 'º' || c == '?' || c == '/' || c == '°' || c == ';' || c == ':' || c == ',' || c == '.' || c == '\\' || c == '|' || c == 'ç' || c == '~' || 
			c == '^' || c == '"') return true;
		return false;	
	}
        
	private boolean isGraphic(char c)  {
		if (isDigit(c) || isLetter(c) || isSymbol(c) || c == ' ') return true;
		return false;
	}
}



