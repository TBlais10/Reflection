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

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/Entries")
public class JournalEntryController {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserService userService;

    //Get all
    @GetMapping
    public @ResponseBody List<JournalEntry> getJournalEntries(){
        return journalEntryRepository.findAll();
    }

    //Get one
    @GetMapping("/{id}")
    public JournalEntry getById(@PathVariable Long id){
        return journalEntryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //Post New Entry
    @PostMapping
    public ResponseEntity<JournalEntry> createOne(@RequestBody JournalEntry entry){
        User currentUser = userService.getCurrentUser();

        if (currentUser == null){
            return null;
        }

        Profile currentProfile = profileRepository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        //entry.set

        return new ResponseEntity<>(journalEntryRepository.save(entry), HttpStatus.CREATED);
    }

    //Put update entry


    //delete entry
}
