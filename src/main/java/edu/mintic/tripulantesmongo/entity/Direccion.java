package edu.mintic.tripulantesmongo.entity;

public class Direccion {
    private String calle;
    private String numero;
    private String complemento;
    
    public Direccion(String calle, String numero, String complemento) {
        this.calle = calle;
        this.numero = numero;
        this.complemento = complemento;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    
}
