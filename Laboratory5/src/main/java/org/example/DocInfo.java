package org.example;

import java.util.Objects;

public class DocInfo {

    private String title,
            author,
            publishmentDate,
            price,
            volume,
            publisher;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishmentDate() {
        return publishmentDate;
    }

    public void setPublishmentDate(String publishmentDate) {
        this.publishmentDate = publishmentDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "DocInfo{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishmentDate='" + publishmentDate + '\'' +
                ", price='" + price + '\'' +
                ", volume='" + volume + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocInfo docInfo = (DocInfo) o;
        return Objects.equals(title, docInfo.title) && Objects.equals(author, docInfo.author) && Objects.equals(publishmentDate, docInfo.publishmentDate) && Objects.equals(price, docInfo.price) && Objects.equals(volume, docInfo.volume) && Objects.equals(publisher, docInfo.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, publishmentDate, price, volume, publisher);
    }
}
