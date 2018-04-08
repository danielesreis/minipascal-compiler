package compilador;
import compilador.ast.Declaracao;

public final class IdentificationTable {
    IdEntry latest;
    int level;
    
    public IdentificationTable() {
        level = 0;
        latest = null;
    }
    
    public void openScope(){
        level++;
    }
    
    /////////////////////////////////
    public void closeScope() {
        
    }
    
    //////////////////////////////////
    public Declaracao retrieve(String id) {
        return null;
    }
    
    /////////////////////////////////////
    public void enter(String id, Declaracao declaracao) {
        
    }
}
