package compilador;

public class Tokens {
    public byte kind;
    public String spelling;
    
    public Tokens (byte kind, String spelling){
            this.kind = kind;
            this.spelling = spelling;
            if (kind==ID)
                for (byte k = VAR; k<=PROCEDURE; k++ )
                    if (spelling.equals(spellings[k])){
                        this.kind = k;
                        break;
                    }
    }

    public final static byte
        BECOMES         = 1,
        SEMICOLON       = 2,
        COLON           = 3,
        COMMA           = 4,
        LPAREN          = 5,
        RPAREN          = 6,
        LBRACE          = 7,
        RBRACE          = 8,
        DOT             = 9,
        DOTDOT          = 10,
        BOOL_LIT        = 11,
        OUTROS          = 12,
        OP_REL          = 13,
        OP_MUL          = 14,
        OP_AD           = 15,
        VAZIO           = 16,
        ID              = 17,
        TIPO_SIMPLES    = 18,  
        IN_LIT          = 19, 
        FLOAT_LIT       = 20, 
        PROGRAM         = 21,
        VAR             = 22,
        WHILE           = 23,
        DO              = 24,
        IF              = 25,
        THEN            = 26, 
        ELSE            = 27, 
        BEGIN           = 28, 
        END             = 29, 
        ARRAY           = 30,
        OF              = 31,
        FUNCTION        = 32,
        PROCEDURE       = 33,
        ERROR           = 34;
    
    public final static String[] spellings = {
        ":=",
        ";",
        ":",
        ",",
        "(",
        ")",
        "[",
        "]",
        ".",
        "..",
        "bool-lit",
        "outros",
        "op-rel",
        "op-mul",
        "op-ad",
        "vazio",
        "<id>", 
        "tipo-simples",
        "int-lit",
        "float-lit",
        "program",
        "var",
        "while",
        "do",
        "if",
        "then",
        "else",
        "begin",
        "end",
        "array", 
        "of",
        "function",
        "procedure",
        "error"
    };    
}