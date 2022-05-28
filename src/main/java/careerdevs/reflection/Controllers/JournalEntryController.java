package careerdevs.reflection.Controllers;

import careerdevs.reflection.Auth.User;
import careerdevs.reflection.Models.JournalEntry;
import careerdevs.reflection.Models.Profile;
import careerdevs.reflection.Repositories.JournalEntryRepository;
import careerdevs.reflection.Repositories.ProfileRepository;
import careerdevs.reflection.Security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/Entries")
public class JournalEntryController {

    @Autowired
    private JournalEntryRepository repository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserService userService;

    //Get all
    @GetMapping
    public @ResponseBody List<JournalEntry> getJournalEntries(){
        return repository.findAll();
    }

    //Get one
    @GetMapping("/{id}")
    public JournalEntry getById(@PathVariable Long id){
        User currentUser = userService.getCurrentUser();

        if (currentUser == null){
            return null;
        }

        return repository.findById(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //Post New Entry
    @PostMapping("/newEntry")
    public ResponseEntity<JournalEntry> createOne(@RequestBody JournalEntry entry){
        User currentUser = userService.getCurrentUser();

        if (currentUser == null){
            return null;
        }

        Profile currentProfile = profileRepository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        entry.setDate(LocalDate.now());
        currentProfile.getJournalEntries().add(entry);

        return new ResponseEntity<>(repository.save(entry), HttpStatus.CREATED);
    }

    //Put update entry
    @PatchMapping("/{id}")
    public @ResponseBody JournalEntry updateEntry(@PathVariable Long id, @RequestBody JournalEntry updates){
        User currentUser = userService.getCurrentUser();

        if (currentUser == null){
            return null;
        }

        JournalEntry entry = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        entry.setDate(LocalDate.now());
        if (updates.getTitle() != null) entry.setTitle(updates.getTitle());
        if (updates.getContent() != null) entry.setContent(updates.getTitle());

        return repository.save(entry);
    }

    //delete entry
    @DeleteMapping("/deleteEntry/{id}")
    public ResponseEntity<String> destroyProfile(@PathVariable Long id){
        User currentUser = userService.getCurrentUser();

        if (currentUser == null){
            return null;
        }
        String entryName = repository.getById(id).getTitle();

        repository.deleteById(id);
        return new ResponseEntity<>("Deleted entry " + entryName, HttpStatus.OK);
    }
}
