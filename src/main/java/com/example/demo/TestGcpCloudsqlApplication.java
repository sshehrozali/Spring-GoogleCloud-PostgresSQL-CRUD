package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Optional;

@SpringBootApplication
public class TestGcpCloudsqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestGcpCloudsqlApplication.class, args);
	}

}
@Entity
@Table(name = "photos")
class Photo {
	@Id
	private String id;
	private String uri;
	private String labels;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public Photo(String id, String uri, String labels) {
		this.id = id;
		this.uri = uri;
		this.labels = labels;
	}
	public Photo() {
	}
}
@Repository
interface PhotoRepository extends JpaRepository<Photo, String> {}
@RestController
class PhotoController {

	@Autowired
	private PhotoRepository photoRepository;

	@GetMapping("/")
	public String hello_spring() {
		return "Hello! I am from Spring Boot";
	}

	@PostMapping("/upload")
	public String upload(@RequestBody Photo photo) {
		if (photo.getId() != null && photo.getUri() != null && photo.getLabels() != null) {
			photoRepository.save(photo);
			return "Photo Uploaded Successfully!";
		} else {
			return "Not Accepted!";
		}
	}

	@GetMapping("/open/{id}")
	public ResponseEntity<Photo> view(@PathVariable String id) throws ResourceNotFoundException {
		Photo selectedPhoto = photoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sorry we couldn't find your Photo!"));
		return ResponseEntity.ok().body(selectedPhoto);
	}
}