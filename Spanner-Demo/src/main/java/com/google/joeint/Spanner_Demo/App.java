package com.google.joeint.Spanner_Demo;

import java.util.Arrays;
import java.util.List;

import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.DatabaseId;
import com.google.cloud.spanner.Spanner;
import com.google.cloud.spanner.SpannerOptions;

/**
 * A quick start code for Cloud Spanner. It demonstrates how to setup the Cloud Spanner client and
 * execute a simple query using it against an existing database.
 */
public class App {
  public static void main(String[] args) throws Exception {   
    // Instantiates a client
    SpannerOptions options = SpannerOptions.newBuilder().build();
    Spanner spanner = options.getService();

	// Test data    	
	final List<Singer> SINGERS =
		Arrays.asList(
			new Singer(1, "Marc", "Richards"),
			new Singer(2, "Catalina", "Smith"),
			new Singer(3, "Alice", "Trentor"),
			new Singer(4, "Lea", "Martin"),
			new Singer(5, "David", "Lomond"));

	final List<Album> ALBUMS =
		Arrays.asList(
			new Album(1, 1, "Total Junk", 0),
			new Album(1, 2, "Go, Go, Go", 0),
			new Album(2, 1, "Green", 0),
			new Album(2, 2, "Forever Hold Your Peace", 0),
			new Album(2, 3, "Terrified", 0));    	

    try {    	
      // Creates a database client
      DatabaseClient dbClient = spanner.getDatabaseClient(DatabaseId.of(
          options.getProjectId(), "demo-music", "music"));
      
      MusicDao dao = new MusicDao(dbClient);
      dao.insertData(SINGERS, ALBUMS);
      dao.readAllAlbumData();
      dao.updateAlbumData();
      dao.queryAlbumData();
      dao.truncateData();
      
    } finally {
      spanner.close();
    }
  }
}