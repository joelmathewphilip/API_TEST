package com.example.api_test;

public class DataClassImageLoad {

private String URL1,name,social_rank,publisher,source_url;
    DataClassImageLoad(String URL1,String name,String social_rank,String publisher,String source_url)
    {
        this.URL1=URL1;
        this.name=name;
        this.social_rank=social_rank;
        this.publisher=publisher;
        this.source_url=source_url;

    }

    public String getURL1() {
        return URL1;
    }

    public String getName() {
        return name;
    }
    public String getSocial_rank()
    {
        return social_rank;
    }
    public String getPublisher()
    {
        return publisher;
    }

    public String getSource_url()
    {
        return source_url;
    }

}
