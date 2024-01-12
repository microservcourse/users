package ru.gk.users.service;

public interface UserSkillsService {

    void addSkill(Long userID, String skill);

    void removeSkill(Long userID, Long userSkillId);
}
