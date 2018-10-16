package com.example.nikitaverma.zomato.model.get_zomato_collections_in_a_city;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Collection implements Serializable
{
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("collection_id")
    @Expose
    private String collectionId;

    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    @SerializedName("share_url")
    @Expose
    private String shareUrl;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("res_count")
    @Expose
    private String resCount;

    @SerializedName("url")
    @Expose
    private String url;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getCollectionId()
    {
        return collectionId;
    }

    public void setCollectionId(String collection_id)
    {
        this.collectionId = collection_id;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String image_url)
    {
        this.imageUrl = image_url;
    }

    public String getShareUrl()
    {
        return shareUrl;
    }

    public void setShareUrl(String share_url)
    {
        this.shareUrl = share_url;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getResCount()
    {
        return resCount;
    }

    public void setResCount(String res_count)
    {
        this.resCount = res_count;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

}
