package compilador;

public class Parser {
    private Token currentToken;
    private boolean error = false;
    
    private void accept (byte expectedTokenKind) {
        //System.out.println(currentToken.spelling);
        if(expectedTokenKind != Token.EOT) {
            if(expectedTokenKind != currentToken.kind) 
                Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: '" + Token.spellings[expectedTokenKind-1] + "' esperado mas '" + Token.spellings[currentToken.kind-1] + "' encontrado");
            else currentToken = Compilador.scanner.scan();
        }
    }
    
    private void acceptIt() {
        //System.out.println(currentToken.spelling);
        currentToken = Compilador.scanner.scan();
    }
    
    public void parse() {
        currentToken = Compilador.scanner.scan();
        parsePrograma();
        
        if(currentToken.kind != Token.EOT) {
            Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: End of text esperado, mas '" + Token.spellings[currentToken.kind-1] + "' encontrado");
        }
    }
    
    private void parsePrograma(){
        accept(Token.PROGRAM);
        accept(Token.ID);
        accept(Token.SEMICOLON);
        parseCorpo();
        accept(Token.EOT);
    }
    
    private void parseCorpo() {
        
        while (currentToken.kind == Token.VAR || currentToken.kind == Token.FUNCTION) {
            parseDeclaracao();
            accept(Token.SEMICOLON);
        }
        
        accept(Token.BEGIN);       
        
        while (currentToken.kind == Token.IF || currentToken.kind == Token.WHILE || currentToken.kind == Token.BEGIN || currentToken.kind == Token.ID) {
            parseComando();
            accept(Token.SEMICOLON);
        }
        
        accept(Token.END);
    }
    
    private void parseDeclaracao(){
        
        switch(currentToken.kind) {
        
            case Token.VAR: 
                acceptIt();
                accept(Token.ID);
                            
                while(currentToken.kind == Token.COMMA) {
                    acceptIt();
                    accept(Token.ID);
                }
                            
                accept(Token.COLON);
                parseTipo();
                break;
                
            case Token.FUNCTION: 
                acceptIt();
                accept(Token.ID);
                accept(Token.LPAREN);
                            
                switch(currentToken.kind) {
                    case Token.VAR: case Token.ID:
                        parseParametros();
                        
                        while(currentToken.kind == Token.SEMICOLON) {
                            acceptIt();
                            parseParametros();
                        }
                        accept(Token.RPAREN);
                        break;
                        
                    case Token.RPAREN: 
                        acceptIt();
                        break;
                        
                    default:
                        Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Função mal formulada! Símbolo encontrado: '" + Token.spellings[currentToken.kind-1] + "'");
                }
                
                accept(Token.COLON);
                accept(Token.TIPO_SIMPLES);
                accept(Token.SEMICOLON);
                parseCorpo();
                break;
                
            case Token.PROCEDURE: 
                acceptIt();
                accept(Token.ID);
                accept(Token.LPAREN);
                            
                switch(currentToken.kind) {
                    case Token.VAR: case Token.ID:
                        parseParametros();
                        
                        while(currentToken.kind == Token.SEMICOLON) {
                            acceptIt();
                            parseParametros();
                        }
                       
                        accept(Token.RPAREN);
                        break;
                    
                    case Token.RPAREN: 
                        acceptIt();
                        break;
                    
                    default:
                        Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Procedure mal formulada! Símbolo encontrado: '" + Token.spellings[currentToken.kind-1] + "'");
                }
                
                accept(Token.SEMICOLON);
                parseCorpo();
                break;
                
            default:
                Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Declaração mal formulada! Símbolo encontrado: '" + Token.spellings[currentToken.kind-1] + "'");
        }
    }
    
    private void parseComando() {
        
        switch(currentToken.kind) {
            
            case Token.IF: 
                acceptIt();
                parseExpressao();
                accept(Token.THEN);
                parseComando();
            
                if (currentToken.kind == Token.ELSE){
                    accept (Token.ELSE);
                    parseComando();
                }
                break;
       
            case Token.WHILE:   
                acceptIt();
                parseExpressao();
                accept(Token.DO);
                parseComando();
                break;
       
            case Token.BEGIN: 
                acceptIt();
                while (currentToken.kind == Token.IF || currentToken.kind == Token.WHILE ||currentToken.kind == Token.BEGIN ||currentToken.kind == Token.ID) {
                    parseComando();
                    accept(Token.SEMICOLON);
                }
                accept(Token.END);
                break;
        
            case Token.ID:
                acceptIt();
                
                switch(currentToken.kind) {
                    case Token.BECOMES:
                        acceptIt();
                        parseExpressao();
                        break;

                    case Token.LBRACE:
                        while( currentToken.kind == Token.LBRACE) {
                            acceptIt();
                            parseExpressao();
                            accept(Token.RBRACE);
                        }
                        accept(Token.BECOMES);
                        parseExpressao();
                        break;

                    case Token.LPAREN:
                        acceptIt();

                        if( currentToken.kind == Token.RPAREN )
                            acceptIt();
                        else if ( currentToken.kind == Token.ID ) {
                            parseExpressao();

                            while( currentToken.kind == Token.COMMA){
                                acceptIt();
                                parseExpressao();
                            }
                            accept(Token.RPAREN);
                        }
                        else {
                            Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Chamada de procedimento mal formulada! Símbolo encontrado: '" + Token.spellings[currentToken.kind-1] + "'");
                        }
                        break;
                        
                    default: Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Símbolo encontrado: '" + Token.spellings[currentToken.kind-1] + "'");
                }
                break;
        
            default: Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Comando mal formulado! Símbolo encontrado: '" + Token.spellings[currentToken.kind-1]);
        }
    }
    
    private void parseFator() {
        
        switch(currentToken.kind) {
           
            case Token.ID:
                acceptIt();
                while(currentToken.kind == Token.LBRACE){
                   acceptIt();
                   parseExpressao(); 
                   accept(Token.RBRACE);
                }
                if(currentToken.kind == Token.LPAREN){
                   acceptIt();
                   parseExpressao(); 
                   while( currentToken.kind == Token.COMMA){
                       acceptIt();
                       parseExpressao();
                   }
                   accept(Token.RPAREN);
                }
                break;
            
            case Token.BOOL_LIT:
                acceptIt();
                break;
            
            case Token.INT_LIT:
                acceptIt();
                break;
            
            case Token.FLOAT_LIT:
                acceptIt();
                break;
            
            case Token.LPAREN:
                acceptIt();
                parseExpressao();
                accept(Token.RPAREN);
                break;
                
            default: Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Fator mal formulado! Símbolo encontrado: '" + Token.spellings[currentToken.kind-1] + "'");
        }        
    }
    
    private void parseTipo() {
        switch (currentToken.kind) {
            
            case Token.ARRAY:
                parseTipoAgregado();
                break;
            
            case Token.TIPO_SIMPLES:
                acceptIt();
                break;
            
            default:
                Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Tipo mal formulado! Símbolo encontrado: '" + Token.spellings[currentToken.kind-1] + "'");
        }
    }
    
    private void parseParametros() {
        switch (currentToken.kind) {
            
            case Token.VAR:
                acceptIt();
                accept(Token.ID);
                break;
            
            case Token.ID:
                acceptIt();
                break;
            
            default: 
                Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Parâmetros mal formulados! Símbolo encontrado: '" + Token.spellings[currentToken.kind-1] + "'");
        }
        
        while(currentToken.kind == Token.COMMA) {
            acceptIt();
            accept(Token.ID);
        }
        
        accept(Token.COLON);
        accept(Token.TIPO_SIMPLES);
    }
    
    private void parseTipoAgregado() {
        switch(currentToken.kind) {
            
            case Token.ARRAY:
                double op1=0, op2=0;
                acceptIt();
                accept(Token.LBRACE);
                
                if (currentToken.kind == Token.BOOL_LIT || currentToken.kind == Token.INT_LIT || currentToken.kind == Token.FLOAT_LIT) {
                    if(currentToken.kind != Token.BOOL_LIT) op1 = Double.parseDouble(currentToken.spelling);
                    acceptIt();
                }
                
                else {
                    Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Array mal formulada! Símbolo encontrado: '" + Token.spellings[currentToken.kind-1] + "'");
                }
                
                accept(Token.DOTDOT);
                
                if (currentToken.kind == Token.BOOL_LIT || currentToken.kind == Token.INT_LIT || currentToken.kind == Token.FLOAT_LIT) {
                    if(currentToken.kind != Token.BOOL_LIT) op2 = Double.parseDouble(currentToken.spelling);
                    acceptIt();
                }
                    
                else {
                    Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Array mal formulada! Símbolo encontrado: '" + Token.spellings[currentToken.kind-1] + "'");
                }
                
                if (op1 > op2) Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Índices inválidos!");
                
                accept(Token.RBRACE);
                accept(Token.OF);
                parseTipo();
                break;
            
            default:
                Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Tipo agregado mal formulado! Símbolo encontrado: '" + Token.spellings[currentToken.kind-1] + "'");
        }
    }
    
    private void parseExpressao() {
        
        parseExpressaoSimples();
        
        if (currentToken.kind == Token.OP_REL){
           acceptIt();
           parseExpressaoSimples();
        }
   }
   
   private void parseExpressaoSimples(){
        
       parseFator();
        
        while(currentToken.kind == Token.OP_MUL){
            acceptIt();
            parseFator();
        }
        
        while(currentToken.kind == Token.OP_AD){
            acceptIt();
            parseFator();
            while(currentToken.kind == Token.OP_MUL){
                acceptIt();
                parseFator();
            }
        }
    }
}
