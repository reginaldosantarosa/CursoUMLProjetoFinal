package package_com.reginaldo.cursoms.domain.enums;

public enum TipoCliente {
    
    PESSOA_FISICA(1,"PESSOA FÍSICA"),
    PESSOA_JURIDICA(2,"PESSOA JURÍDICA");
    
    private int cod;
    private String discricao;

    private TipoCliente(int cod, String discricao) {
        this.cod = cod;
        this.discricao = discricao;
    }
  

    public int getCod() {
        return cod;
    }

    public String getDiscricao() {
        return discricao;
    }
    
    
    public static TipoCliente toEnum(Integer cod){
        if (cod==null){
            return null;       
        }
    
        for (TipoCliente x :TipoCliente.values()){
            if (cod.equals(x.getCod())){
                return x;
            }        
        }    
        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
