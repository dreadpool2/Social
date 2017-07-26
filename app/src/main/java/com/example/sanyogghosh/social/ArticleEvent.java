package com.example.sanyogghosh.social;

/**
 * Created by Sanyog Ghosh on 19-07-2017.
 */
public class ArticleEvent {

    public String name;
    public String head;
    public String text;
    public String date;
    public int upvotes;
    public int downvotes;
    public String status;
    public int favourite_count;
    public String image;


    public  ArticleEvent(){


    }

    public ArticleEvent(String name,String head, String text, String date , int upvotes , int downvotes ,String status,int favourite_count ,String image)
    {
        this.name=name;
        this.head=head;
        this.text=text;
        this.date=date;
        this.upvotes=upvotes;
        this.downvotes=downvotes;
        this.status=status;
        this.favourite_count=favourite_count;
        this.image=image;


    }

    /**
     * Get the name of the Android version
     */

    public String name() {
        return name;
    }
    public String head() {
        return head;
    }
    public String text() {
        return text;
    }
    public String  date() {
        return date;
    }
    public int upvotes() {
        return upvotes;
    }
    public int downvotes() {
        return downvotes;
    }
    public String status() {
        return status;
    }
    public String image() {
        return image;
    }
    public int favourite_count() {
        return favourite_count;
    }




}
