package com.cse281.resource;


import com.cse281.model.Item;
import com.cse281.service.ItemService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
public class ItemResource {

	@Autowired
	private ItemService service;

	
	@GetMapping("/items")
	public List<Item> getAllItems() {
		return service.findAll();
	}

	@GetMapping("/items/{id}")
	public Item getItem(@PathVariable int id) {
		return service.findById(id);
	}

	@DeleteMapping("/instructors/{username}/items/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable String username, @PathVariable int id) {

		Item item = service.deleteById(id);

		if (item != null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/instructors/{username}/items/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable String username, @PathVariable int id,
			@RequestBody Item item) {

		Item itemUpdated = service.save(item);

		return new ResponseEntity<Item>(item, HttpStatus.OK);
	}

	@PostMapping("/instructors/{username}/items")
	public ResponseEntity<Void> createItem(@PathVariable String username, @RequestBody Item item) {

		Item createdItem = service.save(item);

		// Location
		// Get current resource url
		/// {id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdItem.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

}