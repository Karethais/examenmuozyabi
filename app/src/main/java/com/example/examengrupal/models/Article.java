package com.example.examengrupal.models;

public class Article {
    private String publicationId;
    private String title;
    private String doi;
    private String abstractText;
    private String datePublished;
    private String pdfUrl;
    private String htmlUrl;

    public Article(String publicationId, String title, String doi, String abstractText, String datePublished, String pdfUrl, String htmlUrl) {
        this.publicationId = publicationId;
        this.title = title;
        this.doi = doi;
        this.abstractText = abstractText;
        this.datePublished = datePublished;
        this.pdfUrl = pdfUrl;
        this.htmlUrl = htmlUrl;
    }

    public String getPublicationId() { return publicationId; }
    public void setPublicationId(String publicationId) { this.publicationId = publicationId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDoi() { return doi; }
    public void setDoi(String doi) { this.doi = doi; }
    public String getAbstractText() { return abstractText; }
    public void setAbstractText(String abstractText) { this.abstractText = abstractText; }
    public String getDatePublished() { return datePublished; }
    public void setDatePublished(String datePublished) { this.datePublished = datePublished; }
    public String getPdfUrl() { return pdfUrl; }
    public void setPdfUrl(String pdfUrl) { this.pdfUrl = pdfUrl; }
    public String getHtmlUrl() { return htmlUrl; }
    public void setHtmlUrl(String htmlUrl) { this.htmlUrl = htmlUrl; }
}
