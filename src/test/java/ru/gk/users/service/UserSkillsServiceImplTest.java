package ru.gk.users.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.gk.users.entity.Skills;
import ru.gk.users.entity.User;
import ru.gk.users.entity.UserSkills;
import ru.gk.users.repository.SkillsRepository;
import ru.gk.users.repository.UserSkillsRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserSkillsServiceImplTest {

    @Mock
    private SkillsRepository skillsRepository;

    @Mock
    private UserSkillsRepository userSkillsRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserSkillsServiceImpl userSkillsService;

    @Test
    void testAddSkill() {
        Long userId = 1L;
        String skillName = "test";
        User user = new User();
        Skills skill = new Skills();

        when(userService.getUserById(userId)).thenReturn(user);
        when(skillsRepository.getBySkillName(skillName)).thenReturn(Optional.of(skill));
        userSkillsService.addSkill(userId, skillName);

        verify(skillsRepository, times(1)).save(any(Skills.class));
        verify(userSkillsRepository, times(1)).save(any(UserSkills.class));
    }

    @Test
    void testRemoveSkill() {
        Long userId = 1L;
        Long userSkillId = 2L;
        UserSkills userSkill = new UserSkills();

        when(userSkillsRepository.findByUserUserIdAndSkillSkillId(userId, userSkillId))
                .thenReturn(Optional.of(userSkill));

        userSkillsService.removeSkill(userId, userSkillId);


        verify(userSkillsRepository, times(1)).delete(userSkill);
    }

    @Test
    void testRemoveSkillNotFound() {
        Long userId = 1L;
        Long userSkillId = 2L;
        when(userSkillsRepository.findByUserUserIdAndSkillSkillId(userId, userSkillId))
                .thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userSkillsService.removeSkill(userId, userSkillId));
    }

}