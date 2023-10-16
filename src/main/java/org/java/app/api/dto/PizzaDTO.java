package org.java.app.api.dto;

import java.math.BigDecimal;

public class PizzaDTO {

    private int id;
    private String nome;
    private String descrizione;
    private String foto;
    private BigDecimal prezzo;

    public PizzaDTO() { }
    public PizzaDTO(String nome) {
		
		setNome(nome);
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return "id: " + getId()
        		+ "\nome: " + getNome() 
                + "\ndescrizione: " + getDescrizione() 
                + "\nfoto: " + getFoto()
                + "\nprezzo: " + getPrezzo();
    }
}
