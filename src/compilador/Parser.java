package compilador;
import compilador.ast.*;

public class Parser {
    private Token currentToken;
    private boolean error = false;
    private static Scanner scanner;
    
    public Parser(char firstChar) {
        scanner = new Scanner(firstChar);
    }
    
    private void accept (byte expectedTokenKind) {
        if(expectedTokenKind != Token.EOT) {
            if(expectedTokenKind != currentToken.kind) 
            {
                int line = (Compilador.currentLine == 1) ? 1 : Compilador.currentLine-1;
                if (currentToken.kind == Token.ERROR)
                    Compilador.compilerFrame.setOutputText("ERRO LÉXICO: '" + Token.spellings[expectedTokenKind-1] + "' esperado mas caractere inválido encontrado (Linha " + line + ")");
                else
                    if(Compilador.step > 0)
                        Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: '" + Token.spellings[expectedTokenKind-1] + "' esperado mas '" + 
                        Token.spellings[currentToken.kind-1] + "' encontrado (Linha " + line + ")");
            }
            else currentToken = scanner.scan();
        }
    }
    
    private void acceptIt() {
        //System.out.println(currentToken.spelling);
        currentToken = scanner.scan();
    }
    
    public Programa parse() {
        Programa pAST;
        currentToken = scanner.scan();
        
        pAST = parsePrograma();
        if(currentToken.kind != Token.EOT) {
            int line = (Compilador.currentLine == 1) ? 1 : Compilador.currentLine-1;
            if (currentToken.kind != Token.ERROR)
                    if(Compilador.step > 0)
                        Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: End of text esperado, mas '" + Token.spellings[currentToken.kind-1] + 
                    "' encontrado (Linha " + line + ")");
        }
        return pAST;
    }
    
    private Programa parsePrograma(){
        Programa pAST;
        accept(Token.PROGRAM);
        Identifier iAST = new IdentifierSimples(currentToken.spelling);
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
        int line = (Compilador.currentLine == 1) ? 1 : Compilador.currentLine-1;
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
                cAST = new ComandoBegin(cAST);
                break;
        
            case Token.ID:
                Identifier I = new IdentifierSimples(currentToken.spelling);
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
                        accept(Token.RBRACE);
                        
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
                        
                        else if (currentToken.kind == Token.ID || currentToken.kind == Token.LITERAL || currentToken.kind == Token.LPAREN) {
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
                            if(Compilador.step > 0)
                                Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Chamada de procedimento mal formulada! Símbolo encontrado: '" + 
                                Token.spellings[currentToken.kind-1] + "' (Linha " + line + ")");
                        }
                        break;
                        
                            
                    default:    
                                if(Compilador.step > 0)
                                    Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Símbolo encontrado: '" + 
                                    Token.spellings[currentToken.kind-1] + "' (Linha " + line + ")");
                }
                break;
        
            default: 
                    if(Compilador.step > 0)
                        Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Comando mal formulado! Símbolo encontrado: '" + 
                Token.spellings[currentToken.kind-1] + "' (Linha ] " + line + ")");
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
                id1AST = new IdentifierSimples(currentToken.spelling);
                accept(Token.ID);
                            
                while(currentToken.kind == Token.COMMA) {
                    acceptIt();
                    id2AST = new IdentifierSimples(currentToken.spelling);
                    id1AST = new IdentifierSequencial(id1AST, id2AST);
                    accept(Token.ID);
                }
                            
                accept(Token.COLON);
                tAST = parseTipo();
                decAST = new DeclaracaoVariavel(id1AST, tAST);
                break;
                
            case Token.FUNCTION:
           
                acceptIt();
                id1AST = new IdentifierSimples(currentToken.spelling);
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
                        TsAST = new TipoSimples(currentToken.spelling, currentToken.kind);                       
                        accept(Token.TIPO_SIMPLES);
                        accept(Token.SEMICOLON);
                        corpoAST = parseCorpo();
                        decAST = new DeclaracaoFuncao(id1AST, par1AST, TsAST, corpoAST);                        
                        break;
                        
                    case Token.RPAREN: 
                        acceptIt();
                        accept(Token.COLON);
                        TsAST = new TipoSimples(currentToken.spelling, currentToken.kind);
                        accept(Token.TIPO_SIMPLES);
                        accept(Token.SEMICOLON);
                        corpoAST = parseCorpo();
                        decAST = new DeclaracaoFuncaoSemArgs(id1AST, TsAST, corpoAST);
                        break;
                        
                    default:
                        int line = (Compilador.currentLine == 1) ? 1 : Compilador.currentLine-1;
                            if(Compilador.step > 0)
                                Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Função mal formulada! Símbolo encontrado: '" + 
                                Token.spellings[currentToken.kind-1] + "' (Linha " + line + ")");
                }
                break;
                
            case Token.PROCEDURE: 
                acceptIt();
                id1AST = new IdentifierSimples(currentToken.spelling);
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
                        int line = (Compilador.currentLine == 1) ? 1 : Compilador.currentLine-1;
                            if(Compilador.step > 0)
                                Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Procedure mal formulada! Símbolo encontrado: '" + 
                                Token.spellings[currentToken.kind-1] + "' (Linha " + line + ")");
                }
                break;
                
            default:
                int line = (Compilador.currentLine == 1) ? 1 : Compilador.currentLine-1;
                    if(Compilador.step > 0)
                        Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Declaração mal formulada! Símbolo encontrado: '" + 
                        Token.spellings[currentToken.kind-1] + "' (Linha " + line + ")");
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

    private ExpressaoSimples parseExpressaoSimples(){
        ExpressaoSimples EsAST;
        Fator Fator1AST, Fator2AST, Fator3AST, Fator4AST;
        OpMul opMulAST;
        OpAd opAdAST;
        Fator1AST = parseFator();
        
        while(currentToken.kind == Token.OP_MUL){
            opMulAST = new OpMul(currentToken.spelling);
            acceptIt();
            Fator2AST = parseFator();
            Fator1AST = new FatorMulSequencial(Fator1AST, opMulAST, Fator2AST);
        }
        
        while(currentToken.kind == Token.OP_AD){
            opAdAST = new OpAd(currentToken.spelling);
            acceptIt();
            Fator3AST = parseFator();
            Fator1AST = new FatorAdSequencial(Fator1AST, opAdAST, Fator3AST);
            
            while(currentToken.kind == Token.OP_MUL){
                opMulAST = new OpMul(currentToken.spelling);
                acceptIt();
                Fator4AST = parseFator();
                Fator1AST = new FatorMulSequencial(Fator1AST, opMulAST, Fator4AST);
            }
        }
        EsAST = new ExpressaoSimples(Fator1AST);
        return EsAST;
    }

    private Fator parseFator() {
        Fator fAST = null;
        Expressao eAST = null;
        int line = (Compilador.currentLine == 1) ? 1 : Compilador.currentLine-1;
        switch(currentToken.kind) {
           
            case Token.ID:
                Expressao eAST1 = null, eAST2;
                Identifier iAST = new IdentifierSimples(currentToken.spelling);
                Variavel vAST = new VariavelId(iAST);
                acceptIt();
                
                if(currentToken.kind == Token.LBRACE) {
                    acceptIt();
                    eAST1 = parseExpressao();
                    accept(Token.RBRACE);
                    
                    while(currentToken.kind == Token.LBRACE){
                        acceptIt();
                        eAST2 = parseExpressao(); 
                        eAST1 = new ExpressaoSequencial(eAST1, eAST2);
                        accept(Token.RBRACE);
                    }
                    vAST = new VariavelIndexada(iAST, eAST1);
                    fAST = vAST;
                }
                                
                else if(currentToken.kind == Token.LPAREN){
                  acceptIt();

                    if(currentToken.kind == Token.RPAREN) {
                        acceptIt();
                        fAST = new FatorChamadaFuncaoSemArgs(iAST);
                    }
                        
                    else if (currentToken.kind == Token.ID || currentToken.kind == Token.LITERAL || currentToken.kind == Token.LPAREN) {
                        eAST1 = parseExpressao();

                        while(currentToken.kind == Token.COMMA){
                            acceptIt(); 
                            eAST2 = parseExpressao();
                            eAST1 = new ExpressaoSequencial(eAST1, eAST2);
                        }
                        
                        accept(Token.RPAREN);
                        fAST = new FatorChamadaFuncao(iAST, eAST1);
                    }
                    else {
                            if(Compilador.step > 0)
                                Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Chamada de procedimento mal formulada! Símbolo encontrado: '" + 
                                Token.spellings[currentToken.kind-1] + "' (Linha " + line + ")");
                    }                    
                }
                
                else fAST = new FatorId(iAST);
                break;
            
            case Token.LITERAL:
                fAST = new Literal(currentToken.spelling);
                acceptIt();
                break;
                
            case Token.LPAREN:
                acceptIt();
                eAST = parseExpressao();
                fAST = new FatorExpressao(eAST);
                accept(Token.RPAREN);
                break;
            default: 
                if(Compilador.step > 0)
                    Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Fator mal formulado! Símbolo encontrado: '" + 
                    Token.spellings[currentToken.kind-1] + "' (Linha " + line + ")");
        }
        return fAST;
    }
    
    private Parametro parseParametros() {
        Parametro ParAST;
        Identifier id1AST = null, id2AST;
        TipoSimples TsAST;
        
        switch (currentToken.kind) {
            
            case Token.VAR:
                acceptIt();
                id1AST = new IdentifierSimples(currentToken.spelling);
                accept(Token.ID);
                break;
            
            case Token.ID:
                id1AST = new IdentifierSimples(currentToken.spelling);
                acceptIt();
                break;
            
            default: 
                int line = (Compilador.currentLine == 1) ? 1 : Compilador.currentLine-1;
                    if(Compilador.step > 0)
                        Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Parâmetros mal formulados! Símbolo encontrado: '" + 
                        Token.spellings[currentToken.kind-1] + "' (Linha " + line + ")");
        }
        
        while(currentToken.kind == Token.COMMA) {
            acceptIt();
            id2AST = new IdentifierSimples(currentToken.spelling);
            id1AST = new IdentifierSequencial(id1AST, id2AST);
            accept(Token.ID);
        }
        
        accept(Token.COLON);
        TsAST = new TipoSimples(currentToken.spelling, currentToken.kind);
        accept(Token.TIPO_SIMPLES);
        ParAST = new ParametroSimples(id1AST, TsAST);
        return ParAST;
    }

    private Tipo parseTipo() {
        Tipo tAST = null;
        int line = (Compilador.currentLine == 1) ? 1 : Compilador.currentLine-1;
        switch (currentToken.kind) {
            
            case Token.ARRAY:
                tAST = parseTipoAgregado();
                break;
            
            case Token.TIPO_SIMPLES:
                tAST = new TipoSimples(currentToken.spelling, currentToken.kind);
                acceptIt();
                break;
            
            default:
                    if(Compilador.step > 0)
                        Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Tipo mal formulado! Símbolo encontrado: '" + 
                        Token.spellings[currentToken.kind-1] + "' (Linha "  + line + ")");
        }
        return tAST;
    }
   
    private TipoAgregado parseTipoAgregado() {
        TipoAgregado taAST = null;
        int line = (Compilador.currentLine == 1) ? 1 : Compilador.currentLine-1;
        
        switch(currentToken.kind) {
            
            case Token.ARRAY:
                Literal L1, L2;
                Tipo T;
                double op1=0, op2=0;
                acceptIt();
                accept(Token.LBRACE);
                
                switch(currentToken.kind) {
                    case Token.LITERAL:
                        if (Scanner.isDigit(currentToken.spelling.charAt(0))) op1 = Double.parseDouble(currentToken.spelling);
                        L1 = new Literal(currentToken.spelling);
                        acceptIt();
                        
                        accept(Token.DOTDOT);
                        
                        if (Scanner.isDigit(currentToken.spelling.charAt(0))) op2 = Double.parseDouble(currentToken.spelling);
                        L2 = new Literal(currentToken.spelling);
                        accept(Token.LITERAL);
                        
                        accept(Token.RBRACE);
                        accept(Token.OF);
                        T = parseTipo();
                        
                        taAST = new TipoAgregado(L1, L2, T);
                        if (op1 > op2) 
                            if(Compilador.step > 0)
                                Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Índices inválidos!");
                        break;
                        
                    default: 
                        if(Compilador.step > 0)
                            Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Tipo agregado mal formulado! Símbolo encontrado: '" + 
                            Token.spellings[currentToken.kind-1] + "' (Linha " + line + ")");
                }
                break;
            default: 
                if(Compilador.step > 0)
                    Compilador.compilerFrame.setOutputText("ERRO SINTÁTICO: Tipo agregado mal formulado! Símbolo encontrado: '" + 
                    Token.spellings[currentToken.kind-1] + "' (Linha " + line + ")");
        }
        return taAST;
    }  
}