package com.example.nikitaverma.zomato.model.get_zomato_collections_in_a_city;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Base class for Zomato collection api response
 */
public class ZomatoCollection implements Serializable{

    @SerializedName("has_total")
    @Expose
    private String hasTotal;

    @SerializedName("share_url")
    @Expose
    private String shareUrl;

    @SerializedName("display_text")
    @Expose
    private String displayText;

    @SerializedName("has_more")
    @Expose
    private String hasMore;

    private List<Collections> collections;

    public String getHasTotal()
    {
        return hasTotal;
    }

    public void setHasTotal(String has_total)
    {
        this.hasTotal = has_total;
    }

    public String getShareUrl()
    {
        return shareUrl;
    }

    public void setShareUrl(String share_url)
    {
        this.shareUrl = share_url;
    }

    public String getDisplayText()
    {
        return displayText;
    }

    public void setDisplayText(String display_text)
    {
        this.displayText = display_text;
    }

    public String getHasMore()
    {
        return hasMore;
    }

    public void setHasMore(String has_more)
    {
        this.hasMore = has_more;
    }

    public List<Collections> getCollections ()
    {
        return collections;
    }

    public void setCollections (List<Collections> collections)
    {
        this.collections = collections;
    }

}
