package com.onito.task.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import com.onito.task.dto.DataRequest;
import com.onito.task.dto.LongDurationMovies;
import com.onito.task.dto.TopRatedMovies;
import com.onito.task.dto.genere;
import com.onito.task.entity.Movies;
import com.onito.task.entity.Ratings;

import ch.qos.logback.core.model.Model;


@Service
public class MoviesService {
	 @Autowired
	 private JdbcTemplate jdbcTemplate;

    
	 public List<LongDurationMovies> getLongestDurationMovies() {
    	  String sql = "SELECT tconst, primaryTitle, runtimeMinutes, genres FROM movies ORDER BY runtimeMinutes DESC LIMIT 10";
    	  return jdbcTemplate.query(sql, (rs, rowNum) -> {
              LongDurationMovies ldm=new LongDurationMovies();
              
              ldm.setTconst(rs.getString(1));
              ldm.setPrimaryTitle(rs.getString(2));
              ldm.setRuntimeMinutes(rs.getInt(3));
              ldm.setGenres(rs.getString(4));
              return ldm;
          });
    }
	 
	 public String addNewMovie(DataRequest dataRequest) {
		 Movies movie = dataRequest.getMovies();
	     Ratings rating = dataRequest.getRatings();
	     
	     String insertMovieSql = "INSERT INTO movies (tconst, titleType, primaryTitle, runtimeMinutes, genres) VALUES (?, ?, ?, ?, ?)";
	        jdbcTemplate.update(
	                insertMovieSql,
	                movie.getTconst(),
	                movie.getTitleType(),
	                movie.getPrimaryTitle(),
	                movie.getRuntimeMinutes(),
	                movie.getGenres()
	        );

	        // Add data to ratings table
	        String insertRatingSql = "INSERT INTO ratings (tconst, averageRating, numVotes) VALUES (?, ?, ?)";
	        jdbcTemplate.update(
	                insertRatingSql,
	                rating.getTconst(),
	                rating.getAverageRating(),
	                rating.getNumVotes()
	        );
	     
	        return "success";
	 }
	 public List<TopRatedMovies> getTopRatedMovies(){
	
		 String sql = "SELECT m.tconst, m.primaryTitle, m.genres, r.averageRating " +
	                "FROM movies m " +
	                "JOIN ratings r ON m.tconst = r.tconst " +
	                "WHERE r.averageRating > 6.0 " +
	                "ORDER BY r.averageRating DESC";
		 return jdbcTemplate.query(sql, (rs, rowNum) -> {
			 TopRatedMovies trm=new TopRatedMovies();
			 trm.setTconst(rs.getString(1));
			 trm.setPrimaryTitle(rs.getString(2));
			 trm.setGenres(rs.getString(3));
			 trm.setAverageRating(rs.getDouble(4));
             return trm;
		    });
		 
	 }
	
	    public String updateRuntimeMinutes() {
	        String query = "UPDATE movies " +
	                "SET runtimeMinutes = " +
	                "CASE " +
	                "WHEN genres = 'Documentary' THEN runtimeMinutes + 15 " +
	                "WHEN genres = 'Animation' THEN runtimeMinutes + 30 " +
	                "ELSE runtimeMinutes + 45 " +
	                "END";

	        jdbcTemplate.update(query);

	        return "Runtime minutes updated successfully.";
	    }

	    public String getGenreMoviesWithSubtotals(org.springframework.ui.Model model) {
	        String sqlQuery ="SELECT\r\n"
	        		+ "    CASE\r\n"
	        		+ "        WHEN primaryTitle IS NULL THEN ''\r\n"
	        		+ "        ELSE Genre\r\n"
	        		+ "    END AS Genre,\r\n"
	        		+ "    IFNULL(primaryTitle,'Total') as primaryTitle,\r\n"
	        		+ "    numVotes\r\n"
	        		+ "FROM (\r\n"
	        		+ "    SELECT\r\n"
	        		+ "        Genre,\r\n"
	        		+ "        primaryTitle,\r\n"
	        		+ "        numVotes,\r\n"
	        		+ "        CASE\r\n"
	        		+ "            WHEN Genre IS NULL THEN 'TOTAL'\r\n"
	        		+ "            ELSE Genre\r\n"
	        		+ "        END AS GenreGroup,\r\n"
	        		+ "        CASE\r\n"
	        		+ "            WHEN primaryTitle IS NULL THEN 1\r\n"
	        		+ "            ELSE 0\r\n"
	        		+ "        END AS TitleOrder\r\n"
	        		+ "    FROM (\r\n"
	        		+ "        SELECT\r\n"
	        		+ "            m.genres AS Genre,\r\n"
	        		+ "            m.primaryTitle,\r\n"
	        		+ "            SUM(r.numVotes) AS numVotes\r\n"
	        		+ "        FROM\r\n"
	        		+ "            movies m\r\n"
	        		+ "        JOIN\r\n"
	        		+ "            ratings r ON m.tconst = r.tconst\r\n"
	        		+ "        GROUP BY\r\n"
	        		+ "            m.genres,\r\n"
	        		+ "            m.primaryTitle\r\n"
	        		+ "        WITH ROLLUP\r\n"
	        		+ "    ) AS subquery\r\n"
	        		+ ") AS subquery2\r\n"
	        		+ "ORDER BY\r\n"
	        		+ "    GenreGroup ASC,\r\n"
	        		+ "    TitleOrder ASC,\r\n"
	        		+ "    primaryTitle ASC";
	        
	        StringBuilder htmlTable = new StringBuilder();
	        htmlTable.append("<table>");
	        htmlTable.append("<tr><th>Genre</th><th>primaryTitle</th><th>numVotes</th></tr>");

	        jdbcTemplate.query(sqlQuery, rs -> {
	            String genre = rs.getString("Genre");
	            String primaryTitle = rs.getString("primaryTitle");
	            int numVotes = rs.getInt("numVotes");

	            htmlTable.append("<tr>");
	            htmlTable.append("<td>").append(genre).append("</td>");
	            htmlTable.append("<td>").append(primaryTitle).append("</td>");
	            htmlTable.append("<td>").append(numVotes).append("</td>");
	            htmlTable.append("</tr>");
	        });

	        htmlTable.append("</table>");

	        return htmlTable.toString();
	    
	    }
	 
}
