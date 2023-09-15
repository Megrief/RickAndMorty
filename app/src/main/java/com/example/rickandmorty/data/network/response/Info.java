package com.example.rickandmorty.data.network.response;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public final class Info {
    @SerializedName("count")
    int count;
    @SerializedName("pages")
    int pages;
    @SerializedName("next")
    String next;
    @SerializedName("previous")
    String previous;

    public Info(
            int count,
            int pages,
            String next,
            String previous
    ) {
        this.count = count;
        Log.e("AAA", "Count serialized successfully");
        this.pages = pages;
        Log.e("AAA", "Pages serialized successfully");
        this.next = next;
        Log.e("AAA", "Next serialized successfully");
        this.previous = previous;
        Log.e("AAA", "Previous serialized successfully");
    }

    public int getCount() {
        return count;
    }
    public int getPages() {
        return pages;
    }
    public String getNext() {
        return next;
    }
    public String getPrevious() {
        return previous;
    }
}
