package com.example.examenmuozyabi.classs;

import java.util.List;

public class ARTICULO {
    private String section;
    private String publication_id;
    private String title;
    private String doi;
    private String abstractText;
    private String date_published;
    private String submission_id;
    private String section_id;
    private String seq;
    private List<Galley> galeys;
    private List<KEYW> keywords;
    private List<AUTOR> authors;

    // Constructor, getters, and setters
    public ARTICULO(String section, String publication_id, String title, String doi, String abstractText, String date_published, String submission_id, String section_id, String seq, List<Galley> galeys, List<Keyword> keywords, List<Author> authors) {
        this.section = section;
        this.publication_id = publication_id;
        this.title = title;
        this.doi = doi;
        this.abstractText = abstractText;
        this.date_published = date_published;
        this.submission_id = submission_id;
        this.section_id = section_id;
        this.seq = seq;
        this.galeys = galeys;
        this.keywords = keywords;
        this.authors = authors;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPublicationId() {
        return publication_id;
    }

    public void setPublicationId(String publication_id) {
        this.publication_id = publication_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public String getDatePublished() {
        return date_published;
    }

    public void setDatePublished(String date_published) {
        this.date_published = date_published;
    }

    public String getSubmissionId() {
        return submission_id;
    }

    public void setSubmissionId(String submission_id) {
        this.submission_id = submission_id;
    }

    public String getSectionId() {
        return section_id;
    }

    public void setSectionId(String section_id) {
        this.section_id = section_id;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public List<Galley> getGaleys() {
        return galeys;
    }

    public void setGaleys(List<Galley> galeys) {
        this.galeys = galeys;
    }

    public List<KEYW> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<KEYW> keywords) {
        this.keywords = keywords;
    }

    public List<AUTOR> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AUTOR> authors) {
        this.authors = authors;
    }
}

