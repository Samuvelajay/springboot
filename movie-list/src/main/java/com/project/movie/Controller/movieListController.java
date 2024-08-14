package com.project.movie.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.movie.dataaccess.MovieListRepository;
import com.project.movie.model.Movie;



@Controller
@RequestMapping("/")
public class movieListController {
	@Autowired
	private MovieListRepository MovieListRepository;
	
	@RequestMapping(method=RequestMethod.GET, value="/movies/{actor}")
	public String getMovieListByActor(@PathVariable("actor") String name, Model model) {
		/*ArrayList<Movie>movieList=new ArrayList<Movie>();
		Movie movie=new Movie();
		movie.setActor("tom");
		movie.setDescription("mission impossible");
		movie.setName("m13");
		movieList.add(movie);*/
		List<Movie> movieList  = MovieListRepository.findMoviesByActor(name);
		model.addAttribute("movies",movieList);
		
		return "movielist";
	}
	@RequestMapping(method=RequestMethod.POST, value="/movies", consumes="application/json")
	public ResponseEntity<Object> addMovie(@RequestBody Movie movie) { 
		MovieListRepository.save(movie);
		return ResponseEntity.ok().build();

}
}
