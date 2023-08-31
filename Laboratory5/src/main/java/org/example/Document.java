package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Document {

    List<String> tagList = new ArrayList<>();
    public int id;
    private String path, documentType;
    private DocInfo docInfo;

    public Document() {
        docInfo = new DocInfo();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public DocInfo getDocInfo() {
        return docInfo;
    }

    public void setDocInfo(DocInfo docInfo) {
        this.docInfo = docInfo;
    }

    @Override
    public String toString() {
        return "Document{" +
                "path='" + path + '\'' +
                ", documentType='" + documentType + '\'' +
                ", docInfo=" + docInfo +
                '}';
    }

    public void setParameter(String str, int index) {

        switch (index)
        {
            case 0:
                setPath(str);
                break;
            case 1:
                setDocumentType(str);
                break;
            case 2:
                docInfo.setTitle(str);
                break;
            case 3:
                docInfo.setAuthor(str);
                break;
            case 4:
                docInfo.setPublishmentDate(str);
                break;
            case 5:
                docInfo.setPrice(str);
                break;
            case 6:
                docInfo.setVolume(str);
                break;
            case 7:
                docInfo.setPublisher(str);
                break;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(path, document.path) && Objects.equals(documentType, document.documentType) && Objects.equals(docInfo, document.docInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, documentType, docInfo);
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
