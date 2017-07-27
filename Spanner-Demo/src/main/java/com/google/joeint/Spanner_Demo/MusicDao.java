package com.google.joeint.Spanner_Demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.KeySet;
import com.google.cloud.spanner.Mutation;
import com.google.cloud.spanner.ResultSet;
import com.google.cloud.spanner.Statement;

/**
 * Music dao to handle CRUD operations
 * 
 * @author joeint
 *
 */
public class MusicDao {

	private final DatabaseClient dbClient;
	
	public MusicDao(DatabaseClient dbClient) {
		this.dbClient = dbClient;
	}
	
	/**
	 * Inserts all singer and album data
	 * 
	 * @param dbClient
	 */
	public void insertData(List<Singer> singers, List<Album> albums) {
	    List<Mutation> mutations = new ArrayList<>();
	    
	    for (Singer singer: singers) {
	    	mutations.add(
	    		Mutation.newInsertBuilder("Singers")
	    		.set("SingerId").to(singer.getSingerId())
	    		.set("FirstName").to(singer.getFirstName())
	            .set("LastName").to(singer.getLastName())
	            .build());	    	
	    }
	    for (Album album: albums) {
	    	mutations.add(
	    		Mutation.newInsertBuilder("Albums")
	    		.set("SingerId").to(album.getSingerId())
	    		.set("AlbumId").to(album.getAlbumId())
	            .set("AlbumTitle").to(album.getAlbumTitle())
	            .build());	 
	    }

	    dbClient.write(mutations);
	}
	
	/**
	 * Reads all album data
	 * 
	 * @param dbClient
	 */
	public void readAllAlbumData() {
	    ResultSet resultSet =
	        dbClient
	            .singleUse()
	            .read("Albums",
	                KeySet.all(),
	                Arrays.asList("SingerId", "AlbumId", "AlbumTitle"));
	    while (resultSet.next()) {
	      System.out.printf(
	          "%d %d %s\n", resultSet.getLong(0), resultSet.getLong(1), resultSet.getString(2));
	    }
	}
	
	/**
	 * Updates the album data with a marketing budget
	 * 
	 */
	public void updateAlbumData() {
		List<Mutation> mutations =
			Arrays.asList(
				Mutation.newUpdateBuilder("Albums")
					.set("SingerId").to(1)
					.set("AlbumId").to(1)
					.set("MarketingBudget").to(100000)
					.build(),
				
				Mutation.newUpdateBuilder("Albums")
					.set("SingerId").to(2)
					.set("AlbumId").to(2)
					.set("MarketingBudget").to(500000)
					.build()
			);
		// This writes all the mutations to Cloud Spanner atomically.
		dbClient.write(mutations);
	}
	
	/**
	 * Queries for all album data
	 * 
	 */
	public void queryAlbumData() {
		ResultSet resultSet = dbClient.singleUse().
				executeQuery(
						Statement.of("SELECT SingerId, AlbumId, MarketingBudget from Albums")
				);
		while (resultSet.next()) {
			System.out.printf(
					"%d %d %s\n",
					resultSet.getLong("SingerId"),
					resultSet.getLong("AlbumId"),
					// We check that the value is non null. ResultSet getters can only be used to retrieve
					// non null values.
					resultSet.isNull("MarketingBudget") ? "NULL" : resultSet.getLong("MarketingBudget"));;
		}
	}
	
	/**
	 * Truncates all of the data
	 * 
	 */
	public void truncateData() {
		List<Mutation> mutations =
				Arrays.asList(
						Mutation.delete("Albums", KeySet.all()),
						Mutation.delete("Singers", KeySet.all())
				);
		dbClient.write(mutations);
	}
	
}
