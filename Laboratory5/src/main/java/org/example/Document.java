package org.example;

public class Document {

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

}
