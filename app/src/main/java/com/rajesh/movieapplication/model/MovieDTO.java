
package com.rajesh.movieapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class MovieDTO {

    @SerializedName("page")
    private Long mPage;
    @SerializedName("results")
    private List<Movie> mMovies;
    @SerializedName("total_pages")
    private Long mTotalPages;
    @SerializedName("total_results")
    private Long mTotalResults;

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public List<Movie> getResults() {
        return mMovies;
    }

    public void setResults(List<Movie> movies) {
        mMovies = movies;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long total_pages) {
        mTotalPages = total_pages;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Long total_results) {
        mTotalResults = total_results;
    }

}
