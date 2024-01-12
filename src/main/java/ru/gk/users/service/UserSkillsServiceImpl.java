package ru.gk.users.service;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.gk.users.entity.Skills;
import ru.gk.users.entity.User;
import ru.gk.users.entity.UserSkills;
import ru.gk.users.repository.SkillsRepository;
import ru.gk.users.repository.UserSkillsRepository;

@Service
@Log4j2
@AllArgsConstructor
public class UserSkillsServiceImpl implements UserSkillsService {

    private final SkillsRepository skillsRepository;
    private final UserSkillsRepository userSkillsRepository;
    private final UserService userService;

    @Override
    public void addSkill(Long userID, String skill) {

        User user = userService.getUserById(userID);
        Skills newSkill =
                skillsRepository.getBySkillName(skill).orElseGet(
                        () -> {
                            Skills s = new Skills();
                            s.setSkillName(skill);
                            return s;
                        }
                );

        UserSkills userSkill = new UserSkills();
        userSkill.setUser(user);
        userSkill.setSkill(newSkill);
        skillsRepository.save(newSkill);
        userSkillsRepository.save(userSkill);
        log.info("skill {} added to {} ", userSkill.getUserSkillId(), user.getUserId());


    }

    @Override
    public void removeSkill(Long userID, Long userSkillId) {
        userSkillsRepository.findByUserUserIdAndSkillSkillId(userID, userSkillId).ifPresentOrElse(i -> {
                    userSkillsRepository.delete(i);
                    log.info("skill {} deleted from {}", userSkillId, userID);
                },
                () -> {
                    throw new RuntimeException("not found");
                });
    }
}
