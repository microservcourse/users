package ru.gk.users.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subscription")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    @EqualsAndHashCode.Include
    private Long subscriptionId;

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    @JsonBackReference
    private User subscriber;

    @ManyToOne
    @JoinColumn(name = "target_user_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "userId")
    @JsonIdentityReference(alwaysAsId = true)
    private User targetUser;

}
