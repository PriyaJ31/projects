package com.dairy.personal_dairy.repos;

import com.dairy.personal_dairy.model.DairyEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DairyRepository extends MongoRepository<DairyEntry, String> {
    // Assuming the `tags` field exists in DairyEntry
    List<DairyEntry> findByTagsIn(List<String> tags);
}
