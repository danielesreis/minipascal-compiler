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
       
    public void closeScope() {
        IdEntry entry, local;
        entry = this.latest;
        
        while (entry.level == this.level) {
            local = entry;
            entry = local.previous;
        }
        this.level--;
        this.latest = entry;
    }
    
    public Declaracao retrieve(String id) {
        IdEntry entry;
        Declaracao declaracao = null;
        boolean present = false, searching = true;
        entry = this.latest;
        
        while (searching) {
        if (entry == null)
            searching = false;
        else if (entry.id.equals(id)) {
            present = true;
            searching = false;
            declaracao = entry.declaracao;
        } 
            else entry = entry.previous;
        }
        return declaracao;
    }
    
    public void enter(String id, Declaracao declaracao) {
        IdEntry entry = this.latest;
        boolean present = false, searching = true;

        while (searching) {
            if (entry == null || entry.level < this.level)
                searching = false;
            else if (entry.id.equals(id)) {
                present = true;
                searching = false;
            } 
            else entry = entry.previous;
        }

        declaracao.duplicated = present;
        
        entry = new IdEntry(id, declaracao, this.level, this.latest);
        this.latest = entry;
    }
}
