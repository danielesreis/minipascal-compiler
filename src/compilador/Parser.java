package compilador;
import compilador.ast.*;

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
        Programa pAST;
        currentToken = Compilador.scanner.scan();
        pAST = parsePrograma();
        
        if(currentToken.kind != Token.EOT) {
            Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: End of text esperado, mas '" + Token.spellings[currentToken.kind-1] + "' encontrado");
        }
    }
    
    private Programa parsePrograma(){
        Programa pAST;
        accept(Token.PROGRAM);
        Identifier iAST = new Identifier(currentToken.spelling);
        accept(Token.ID);
        accept(Token.SEMICOLON);
        Corpo cAST = parseCorpo();
        pAST = new Programa(iAST, cAST);
        accept(Token.EOT);
        return pAST;
    }
    
    private Comando parseComando() {
        Comando cAST = null, cAST2 = null, cAST3;
        Expressao eAST1 = null, eAST2, eAST3;
        switch(currentToken.kind) {
            
            case Token.IF:
                acceptIt();
                eAST1 = parseExpressao();
                accept(Token.THEN);
                cAST2 = parseComando();
            
                if (currentToken.kind == Token.ELSE){
                    accept (Token.ELSE);
                    cAST3 = parseComando();
                    cAST2 = new ComandoSequencial(cAST2, cAST3);
                }
                cAST = new ComandoIf(eAST1, cAST2);
                break;
       
            case Token.WHILE:
                acceptIt();
                eAST1 = parseExpressao();
                accept(Token.DO);
                cAST2 = parseComando();
                cAST = new ComandoWhile(eAST1, cAST2);
                break;
                
            case Token.BEGIN: 
                acceptIt();
                
                if (currentToken.kind == Token.IF || currentToken.kind == Token.WHILE ||currentToken.kind == Token.BEGIN ||currentToken.kind == Token.ID) {
                    cAST = parseComando();
                    accept(Token.SEMICOLON);
                }
                
                while (currentToken.kind == Token.IF || currentToken.kind == Token.WHILE ||currentToken.kind == Token.BEGIN ||currentToken.kind == Token.ID) {
                    cAST3 = parseComando();
                    cAST = new ComandoSequencial(cAST, cAST3);
                    accept(Token.SEMICOLON);
                }
                accept(Token.END);
                break;
        
            case Token.ID:
                Identifier I = new Identifier(currentToken.spelling);
                acceptIt();
                
                switch(currentToken.kind) {
                    case Token.BECOMES:
                        acceptIt();
                        eAST1 = parseExpressao();
                        cAST = new ComandoAtribuicao(I, eAST1);
                        break;

                    case Token.LBRACE:
                        acceptIt();
                        eAST1 = parseExpressao();
                        
                        while(currentToken.kind == Token.LBRACE) {
                            acceptIt();
                            eAST2 = parseExpressao();
                            eAST1 = new ExpressaoSequencial(eAST1, eAST2);
                            accept(Token.RBRACE);
                        }
                        accept(Token.BECOMES);
                        eAST3 = parseExpressao();
                        cAST = new ComandoAtribuicaoIndexada(I, eAST1, eAST3);
                        break;

                    case Token.LPAREN:
                        acceptIt();

                        if(currentToken.kind == Token.RPAREN) {
                            acceptIt();
                            cAST = new ComandoChamadaProcedimentoSemArgs(I);
                        }
                        
                        else if (currentToken.kind == Token.ID) {
                            eAST1 = parseExpressao();

                            while(currentToken.kind == Token.COMMA){
                                acceptIt();
                                eAST2 = parseExpressao();
                                eAST1 = new ExpressaoSequencial(eAST1, eAST2);
                            }
                            accept(Token.RPAREN);
                            cAST = new ComandoChamadaProcedimento(I, eAST1);
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
        return cAST;
    }
    
    private Corpo parseCorpo() {
        Corpo cAST;
        Declaracao dAST1 = null, dAST2;
        Comando comAST1 = null, comAST2;
        
        if (currentToken.kind == Token.VAR || currentToken.kind == Token.FUNCTION || currentToken.kind == Token.PROCEDURE) {
            dAST1 = parseDeclaracao();
            accept(Token.SEMICOLON);
            
            while (currentToken.kind == Token.VAR || currentToken.kind == Token.FUNCTION || currentToken.kind == Token.PROCEDURE) {
                dAST2 = parseDeclaracao();
                dAST1 = new DeclaracaoSequencial(dAST1, dAST2);
                accept(Token.SEMICOLON);
            }
        }
        
        accept(Token.BEGIN);       
        
        if (currentToken.kind == Token.IF || currentToken.kind == Token.WHILE || currentToken.kind == Token.BEGIN || currentToken.kind == Token.ID) {
            comAST1 = parseComando();
            accept(Token.SEMICOLON);
            
            while (currentToken.kind == Token.IF || currentToken.kind == Token.WHILE || currentToken.kind == Token.BEGIN || currentToken.kind == Token.ID) {
                comAST2 = parseComando();
                comAST1 = new ComandoSequencial(comAST1, comAST2);
                accept(Token.SEMICOLON);
            }
        }
        
        if (dAST1 == null) {
            if (comAST1 == null) cAST = null;
            else cAST = new CorpoSemDeclaracao(comAST1);
        }
        else {
            if (comAST1 == null) cAST = new CorpoSemComando(dAST1);
            else cAST = new CorpoComDeclaracaoComando(dAST1, comAST1);
        }
        
        accept(Token.END);
        return cAST;
    }
    
    
    private Declaracao parseDeclaracao(){
        Declaracao decAST = null;
        Corpo corpoAST;
        Identifier id1AST, id2AST;
        Parametro par1AST, par2AST = null;
        Tipo tAST;
        TipoSimples TsAST;
        
        
        switch(currentToken.kind) {
        
            case Token.VAR: 
                acceptIt();
                id1AST = new Identifier(currentToken.spelling);
                accept(Token.ID);
                            
                while(currentToken.kind == Token.COMMA) {
                    acceptIt();
                    id2AST = new Identifier(currentToken.spelling);
                    id1AST = new IdentifierSequencial(id1AST, id2AST);
                    accept(Token.ID);
                }
                            
                accept(Token.COLON);
                tAST = parseTipo();
                decAST = new DeclaracaoVariavel(id1AST, tAST);
                break;
                
            case Token.FUNCTION:
           
                acceptIt();
                id1AST = new Identifier(currentToken.spelling);
                accept(Token.ID);
                accept(Token.LPAREN);
                            
                switch(currentToken.kind) {
                    case Token.VAR: case Token.ID:
                        par1AST = parseParametros();
                        
                        while(currentToken.kind == Token.SEMICOLON) {
                            acceptIt();
                            par2AST = parseParametros();
                            par1AST = new ParametroSequencial(par1AST, par2AST);
                            
                        }
                        accept(Token.RPAREN);
                        accept(Token.COLON);
                        TsAST = new TipoSimples(currentToken.spelling);                       
                        accept(Token.TIPO_SIMPLES);
                        accept(Token.SEMICOLON);
                        corpoAST = parseCorpo();
                        decAST = new DeclaracaoFuncao(id1AST, par1AST, TsAST, corpoAST);                        
                        break;
                        
                    case Token.RPAREN: 
                        acceptIt();
                        accept(Token.COLON);
                        TsAST = new TipoSimples(currentToken.spelling);
                        accept(Token.TIPO_SIMPLES);
                        accept(Token.SEMICOLON);
                        corpoAST = parseCorpo();
                        decAST = new DeclaracaoFuncaoSemArgs(id1AST, TsAST, corpoAST);
                        break;
                        
                    default:
                        Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Função mal formulada! Símbolo encontrado: '" + Token.spellings[currentToken.kind-1] + "'");
                }
                break;
                
            case Token.PROCEDURE: 
                acceptIt();
                id1AST = new Identifier(currentToken.spelling);
                accept(Token.ID);
                accept(Token.LPAREN);
                            
                switch(currentToken.kind) {
                    case Token.VAR: case Token.ID:
                        par1AST = parseParametros();
                        
                        while(currentToken.kind == Token.SEMICOLON) {
                            acceptIt();
                            par2AST = parseParametros();
                            par1AST = new ParametroSequencial(par1AST, par2AST);
                        }
                       
                        accept(Token.RPAREN);
                        accept(Token.SEMICOLON);
                        corpoAST = parseCorpo();
                        decAST = new DeclaracaoProcedure(id1AST, par1AST, corpoAST);
                        break;
                    
                    case Token.RPAREN: 
                        acceptIt();
                        accept(Token.SEMICOLON);
                        corpoAST = parseCorpo();
                        decAST = new DeclaracaoProcedureSemArgs(id1AST, corpoAST);
                        break;
                    
                    default:
                        Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Procedure mal formulada! Símbolo encontrado: '" + Token.spellings[currentToken.kind-1] + "'");
                }
                break;
                
            default:
                Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Declaração mal formulada! Símbolo encontrado: '" + Token.spellings[currentToken.kind-1] + "'");
        }
        return decAST;
    }

    private Expressao parseExpressao() {
        Expressao eAST;
        OpRel orAST;
        ExpressaoSimples esAST1, esAST2;
        
        esAST1 = parseExpressaoSimples();
        eAST = esAST1;
        
        if (currentToken.kind == Token.OP_REL){
            orAST = new OpRel(currentToken.spelling);
            acceptIt();
            esAST2 = parseExpressaoSimples();
            eAST = new ExpressaoBinaria(esAST1, esAST2, orAST);
        }
        return eAST;
   }

    /*falta essa classe*/
    private ExpressaoSimples parseExpressaoSimples(){
        
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
    
    private void parseTipo() {
        Tipo tAST;
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
}
