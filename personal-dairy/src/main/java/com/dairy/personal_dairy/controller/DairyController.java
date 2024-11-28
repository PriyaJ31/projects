package com.dairy.personal_dairy.controller;

import com.dairy.personal_dairy.model.DairyEntry;
import com.dairy.personal_dairy.repos.DairyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diary")
@CrossOrigin(origins = "http://localhost:3000") 
public class DairyController {

    @Autowired
    private DairyRepository repository;

    // Create a new diary entry
    @PostMapping
    public DairyEntry createEntry(@RequestBody DairyEntry entry) {
        entry.setDate(LocalDateTime.now()); // Set current date and time
        return repository.save(entry);
    }

    // Get a single diary entry by ID
    @GetMapping("/{id}")
    public DairyEntry getEntry(@PathVariable String id) {
        Optional<DairyEntry> entry = repository.findById(id);
        return entry.orElseThrow(() -> new RuntimeException("Entry not found with id: " + id));
    }

    // Get all diary entries
    @GetMapping
    public List<DairyEntry> getAllEntries() {
        return repository.findAll();
    }

    // Delete a diary entry by ID
    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable String id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Entry not found with id: " + id);
        }
        repository.deleteById(id);
    }

    // Update an existing diary entry by ID
    @PutMapping("/{id}")
    public DairyEntry updateEntry(@PathVariable String id, @RequestBody DairyEntry entry) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Entry not found with id: " + id);
        }
        entry.setId(id);
        entry.setDate(LocalDateTime.now()); // Update date to current
        return repository.save(entry);
    }
}
