package ru.gk.users.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET is_deleted = true WHERE user_id=?")
@Where(clause = "is_deleted is false")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "city")
    private String city;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "about_me", length = 2048)
    private String aboutMe;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number", length = 64)
    private String phoneNumber;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "is_deleted")
    private boolean isDeleted;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<UserSkills> userSkills;

    @OneToMany(mappedBy = "subscriber", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Subscription> subscriptions;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "userId = " + userId + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "middleName = " + middleName + ", " +
                "gender = " + gender + ", " +
                "dateOfBirth = " + dateOfBirth + ", " +
                "city = " + city + ", " +
                "avatarUrl = " + avatarUrl + ", " +
                "aboutMe = " + aboutMe + ", " +
                "nickname = " + nickname + ", " +
                "email = " + email + ", " +
                "phoneNumber = " + phoneNumber + ", " +
                "creationDate = " + creationDate + ", " +
                "isDeleted = " + isDeleted + ")";
    }
}
