package com.google.joeint.Spanner_Demo;

public class Album {

	private final long singerId;
	private final long albumId;
	private final String albumTitle;
	private final long marketingBudget;

	public Album(long singerId, long albumId, String albumTitle, long marketingBudget) {
		this.singerId = singerId;
		this.albumId = albumId;
		this.albumTitle = albumTitle;
		this.marketingBudget = marketingBudget;
	}

	public long getSingerId() {
		return singerId;
	}

	public long getAlbumId() {
		return albumId;
	}

	public String getAlbumTitle() {
		return albumTitle;
	}

	public long getMarketingBudget() {
		return marketingBudget;
	}
}
