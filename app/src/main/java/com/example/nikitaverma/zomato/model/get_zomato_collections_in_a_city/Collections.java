package com.example.nikitaverma.zomato.model.get_zomato_collections_in_a_city;

import java.io.Serializable;

public class Collections implements Serializable
{
    private Collection collection;

    public Collection getCollection ()
    {
        return collection;
    }

    public void setCollection (Collection collection)
    {
        this.collection = collection;
    }

}
