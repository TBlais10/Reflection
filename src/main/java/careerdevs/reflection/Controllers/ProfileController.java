package careerdevs.reflection.Controllers;

import careerdevs.reflection.Auth.User;
import careerdevs.reflection.Models.Profile;
import careerdevs.reflection.Repositories.AvatarRepository;
import careerdevs.reflection.Repositories.ProfileRepository;
import careerdevs.reflection.Repositories.UserRepository;
import careerdevs.reflection.Security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/profile")
public class ProfileController {
    @Autowired
    private ProfileRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AvatarRepository avatarRepository;

    @Autowired
    private UserService userService;

    //Get all
    @GetMapping
    public @ResponseBody List<Profile> getAll(){
        return repository.findAll();
    }

    //Get one
    @GetMapping("/{id}")
    public Profile getDeveloperById(@PathVariable Long id){
        User currentUser = userService.getCurrentUser();

        if (currentUser == null){
            return null;
        }

       return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/self")
    public @ResponseBody Profile getSelf(){
        User currentUser = userService.getCurrentUser();

        if (currentUser == null){
            return null;
        }
        return repository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //Searches for User By username
    @GetMapping("/{username}")
    public @ResponseBody User findByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //Post New Profile
    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile newProfile){
        User currentUser = userService.getCurrentUser();

        if (currentUser == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        newProfile.setUser(currentUser);

        return new ResponseEntity<>(repository.save(newProfile),HttpStatus.CREATED);
    }

    //Put update profile
    @PatchMapping
    public @ResponseBody Profile updateProfile(@RequestBody Profile updates){
        User currentUser = userService.getCurrentUser();

        if (currentUser == null){
            return null;
        }

        Profile profile = repository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getFname() != null) profile.setFname(updates.getFname());
        if (updates.getLname() != null) profile.setLname(updates.getLname());
        if (updates.getGender() != null) profile.setGender((updates.getGender()));
        if (updates.getTown() != null) profile.setTown(updates.getTown());
        if (updates.getState() != null) profile.setState(updates.getState());

        return repository.save(profile);
    }

    //delete profile
    @DeleteMapping
    public ResponseEntity<String> destroyProfile(){
        User currentUser = userService.getCurrentUser();

        if (currentUser == null){
            return null;
        }

        repository.deleteByUser_id(currentUser.getId());
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
