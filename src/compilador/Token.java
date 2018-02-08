package compilador;

public class Token {
    public byte kind;
    public String spelling;
    
    public Token (byte kind, String spelling) {
            this.kind = kind;
            this.spelling = spelling;
            
            if (kind == ID) {
                for (byte k = PROGRAM; k<=PROCEDURE; k++ ) {
                    if (spelling.toUpperCase().equals(spellings[k-1].toUpperCase())) {
                        //System.out.println(spellings[k-1]);
                        this.kind = k;
                        break;
                    }
                }
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
        OP_REL          = 12,
        OP_MUL          = 13,
        OP_AD           = 14,
        ERROR           = 15,
        ID              = 16,
        TIPO_SIMPLES    = 17,  
        INT_LIT         = 18, 
        FLOAT_LIT       = 19, 
        PROGRAM         = 20,
        VAR             = 21,
        WHILE           = 22,
        DO              = 23,
        IF              = 24,
        THEN            = 25, 
        ELSE            = 26, 
        BEGIN           = 27, 
        END             = 28, 
        ARRAY           = 29,
        OF              = 30,
        FUNCTION        = 31,
        PROCEDURE       = 32,
        EOT             = 33;
    
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
        "op-rel",
        "op-mul",
        "op-ad",
        "error",
        "id", 
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
        "."
    };    
}