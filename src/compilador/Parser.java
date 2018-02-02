package compilador;

public class Parser {
    private Token currentToken;
    
    private void accept (byte expectedTokenKind){
        
        if (expectedTokenKind == currentToken.kind) {
            currentToken = Compilador.scanner.scan();
        }
        else{
            //return error
        }
    }
    
    private void acceptIt(){
        currentToken = Compilador.scanner.scan();
    }
    
    public void parse()  {
        currentToken = Compilador.scanner.scan();
        parsePrograma();
        if(currentToken.kind != Token.EOT){
            //Erro sintático
        }        
    }
    
    private void parsePrograma(){
        accept(Token.PROGRAM);
        accept(Token.ID);
        accept(Token.SEMICOLON);
        parseCorpo();
        accept(Token.DOT);
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
    
    private void parseDeclaracao() {
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
                        //report error
                }
                
                accept(Token.COLON);
                accept(Token.TIPO_SIMPLES);
                accept(Token.SEMICOLON);
                parseCorpo();
                
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
                        //report error
                }
                
                accept(Token.SEMICOLON);
                parseCorpo();
                break;
                
            default:
                //report error
        }
    }
    
    private void parseComando() {
          switch(currentToken.kind){
        case Token.IF: 
           acceptIt();
           parseExpressao();
           accept(Token.THEN);
           parseComando();
           if (currentToken.kind == Token.ELSE){
            accept (Token.ELSE);
            parseComando();
           }
           else{
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
           switch(currentToken.kind){
               case Token.LBRACE:
                   while( currentToken.kind == Token.LBRACE){
                       acceptIt();
                       parseExpressao();
                       accept(Token.RBRACE);
                   }
                   accept(Token.BECOMES);
                   parseExpressao();
                break;

               case Token.LPAREN:
                   acceptIt();
                   parseExpressao();
                   while( currentToken.kind == Token.COMMA){
                       acceptIt();
                       parseExpressao();
                   }
                   accept(Token.RPAREN);
                break;
               default: //Erro Sintático
           }
        break;
        
        default: //Erro Sintático
    }
    }
    
    private void parseFator() {
        switch(currentToken.kind){
            case Token.ID:
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
                
            default: // Erro sintático
        }        
    }
    
    private void parseTipo() {
        switch (currentToken.kind) {
            case Token.ARRAY:
                parseTipoAgregado();
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
            default:
                //report error
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
                //report error
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
                acceptIt();
                accept(Token.LBRACE);
                
                if (currentToken.kind == Token.BOOL_LIT || currentToken.kind == Token.INT_LIT || currentToken.kind == Token.FLOAT_LIT)
                    acceptIt();
                else {
                    //return error
                }
                
                accept(Token.DOTDOT);
                
                if (currentToken.kind == Token.BOOL_LIT || currentToken.kind == Token.INT_LIT || currentToken.kind == Token.FLOAT_LIT)
                    acceptIt();
                else {
                    //return error
                }
                
                accept(Token.RBRACE);
                accept(Token.OF);
                parseTipo();
                break;
            default:
                //return error
        }
    }
    
    private void parseExpressao(){
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
