package com.mario.librarybackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;
import java.util.Objects;

@Entity
public class Borrowing {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Reader reader;

    @ManyToOne
    private Book book;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkoutDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkinDate;

    public Borrowing() {
    }

    public Borrowing(Long id, Reader reader, Book book, Date checkoutDate, Date checkinDate) {
        this.id = id;
        this.reader = reader;
        this.book = book;
        this.checkoutDate = checkoutDate;
        this.checkinDate = checkinDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkout) {
        this.checkoutDate = checkout;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkin) {
        this.checkinDate = checkin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrowing borrowing = (Borrowing) o;
        return Objects.equals(id, borrowing.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Borrowing{" +
                "id=" + id +
                ", reader=" + reader +
                ", book=" + book +
                ", checkoutDate=" + checkoutDate +
                ", checkinDate=" + checkinDate +
                '}';
    }
}
