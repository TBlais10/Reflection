package careerdevs.reflection.Repositories;

import careerdevs.reflection.Models.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
}
