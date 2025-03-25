package com.example.examenmuozyabi.classs;

public class AUTOR {
    private String nombres;
    private String filiacion;
    private String email;

    // Constructor, getters, and setters

    public AUTOR(String nombres, String filiacion, String email) {
        this.nombres = nombres;
        this.filiacion = filiacion;
        this.email = email;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getFiliacion() {
        return filiacion;
    }

    public void setFiliacion(String filiacion) {
        this.filiacion = filiacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getters and setters
}
