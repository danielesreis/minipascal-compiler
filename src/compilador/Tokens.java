package compilador;

public class Tokens {
    public byte kind;
    public String spelling;
    
    public Tokens (byte kind, String spelling){
            this.kind = kind;
            this.spelling = spelling;
            if (kind==ID)
                for (byte k = WHILE; k<=OF; k++ )
                    if (spelling.equals(spellings[k])){
                        this.kind = k;
                        break;
                    }
    }

    public final static byte
        BECOMES = 1,
        SEMICOLON = 2,
        COLON = 3,
        COMMA = 4,
        LPAREN = 5,
        RPAREN = 6,
        LBRACE = 7,
        RBRACE = 8,
        DOT = 9,
        BOOL_LIT = 10,
        OUTROS = 11,
        OP_REL = 12,
        OP_MUL = 13,
        OP_AD = 14,
        VAZIO = 15,
        ID = 16,
        TIPO_SIMPLES = 17, 
        VAR = 18, 
        IN_LIT = 19, 
        FLOAT_LIT =20, 
        PROGRAM = 21,
        WHILE = 22,
        DO = 23,
        IF = 24,
        THEN = 25, 
        ELSE = 26, 
        BEGIN = 27, 
        END = 28, 
        ARRAY =29,
        OF = 30;
    
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
        "bool-lit",
        "outros",
        "op-rel",
        "op-mul",
        "op-ad",
        "vazio",
        "<id>", 
        "tipo-simples",
        "var",
        "int-lit",
        "float-lit",
        "program",
        "while",
        "do",
        "if",
        "then",
        "else",
        "begin",
        "end",
        "array", 
        "of"
    };    
}