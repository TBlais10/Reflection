package careerdevs.reflection.Controllers;

import careerdevs.reflection.Models.Avatar;
import careerdevs.reflection.Repositories.AvatarRepository;
import careerdevs.reflection.Repositories.ProfileRepository;
import careerdevs.reflection.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/profile")
public class ProfileController {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AvatarRepository avatarRepository;

//    @Autowired
//    private UserService
}
