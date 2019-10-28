
package com.learning.githubrepo.data;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GithubReposiotry {

    @SerializedName("total_count")
    @Expose
    private int totalCount;
    @SerializedName("incomplete_results")
    @Expose
    private boolean incompleteResults;
    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<>();

    public int getTotalCount() {
        return totalCount;
    }

    public List<Item> getItems() {
        return items;
    }

}
