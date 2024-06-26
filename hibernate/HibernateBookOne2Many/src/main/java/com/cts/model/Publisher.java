package com.cts.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Pub_tab1")
public class Publisher {
	 private int publisherId;
	    private String publisherName;
	    private Set<BookPub> books = new HashSet<BookPub>();
	        
	    @Id    
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column    (name="PUBLISHER_ID",unique=true)
	    
	    public int getPublisherId() {
	        return publisherId;	    }
	    public void setPublisherId(int publisherId) {
	        this.publisherId = publisherId;	    }
	    
	    @Column
	    public String getPublisherName() {
	        return publisherName;	    }    
	    
	    public void setPublisherName(String publisherName) {
	        this.publisherName = publisherName;	    }
	    
	    
	    @OneToMany(fetch = FetchType.LAZY)
	    //the value of mappedBy is the name of the association-mapping attribute on the owning side.
	    public Set<BookPub> getBooks() {
	        return books;	    }
	    
	    public void setBooks(Set<BookPub> books) {
	        this.books = books;	    }   }
















