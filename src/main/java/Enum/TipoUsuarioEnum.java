package Enum;

public enum TipoUsuarioEnum 
{
    CLIENTE(1),
    CAIXA(2),
    GERENTE(3);

    private final int tipoUsuario;

    TipoUsuarioEnum(int tipoUsuario)
    {
        this.tipoUsuario = tipoUsuario;
    }

    public int getTipoUsuario() 
    {
        return tipoUsuario;
    } 
}
