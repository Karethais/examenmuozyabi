package com.example.examengrupal.models;

public class Volumen {
    private String issueId;
    private String volume;
    private String number;
    private String year;
    private String title;
    private String doi;
    private String cover;

    public Volumen(String issueId, String volume, String number, String year, String title, String doi, String cover) {
        this.issueId = issueId;
        this.volume = volume;
        this.number = number;
        this.year = year;
        this.title = title;
        this.doi = doi;
        this.cover = cover;
    }

    public String getIssueId() { return issueId; }
    public void setIssueId(String issueId) { this.issueId = issueId; }
    public String getVolume() { return volume; }
    public void setVolume(String volume) { this.volume = volume; }
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDoi() { return doi; }
    public void setDoi(String doi) { this.doi = doi; }
    public String getCover() { return cover; }
    public void setCover(String cover) { this.cover = cover; }
}
