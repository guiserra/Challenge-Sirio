package com.example.guilhermeserra.challlengesirio.model;

public class Usuario {
    String usuarioId;
    String usuarioName;
    String usuarioGenre;

    public Usuario(){
    }

    public Usuario(String usuarioId, String usuarioName, String usuarioGenre) {
        this.usuarioId = usuarioId;
        this.usuarioName = usuarioName;
        this.usuarioGenre = usuarioGenre;
    }


    public String getUsuarioId() {
        return usuarioId;
    }
    public String getUsuarioName() {
        return usuarioName;
    }
    public String getUsuarioGenre() {
        return usuarioGenre;
    }
    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
    public void setUsuarioName(String usuarioName) {
        this.usuarioName = usuarioName;
    }
    public void setUsuarioGenre(String usuarioGenre) {
        this.usuarioGenre = usuarioGenre;
    }
}
