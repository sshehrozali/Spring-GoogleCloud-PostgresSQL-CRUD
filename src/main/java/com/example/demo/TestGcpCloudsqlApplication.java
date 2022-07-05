package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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