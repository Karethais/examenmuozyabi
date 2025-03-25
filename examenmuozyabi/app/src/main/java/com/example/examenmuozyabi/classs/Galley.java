package com.example.examenmuozyabi.classs;

public class Galley {
    private String galley_id;
    private String label;
    private String file_id;
    private String UrlViewGalley;

    // Constructor, getters, and setters

    public Galley(String galley_id, String label, String file_id, String UrlViewGalley) {
        this.galley_id = galley_id;
        this.label = label;
        this.file_id = file_id;
        this.UrlViewGalley = UrlViewGalley;
    }

    public String getGalleyId() {
        return galley_id;
    }

    public void setGalleyId(String galley_id) {
        this.galley_id = galley_id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFileId() {
        return file_id;
    }

    public void setFileId(String file_id) {
        this.file_id = file_id;
    }

    public String getUrlViewGalley() {
        return UrlViewGalley;
    }

    public void setUrlViewGalley(String UrlViewGalley) {
        this.UrlViewGalley = UrlViewGalley;
    }
}
