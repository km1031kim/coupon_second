package coupon.second.domain.entity;

import coupon.second.common.enums.UserGrade;
import coupon.second.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private int gradeLevel;

    private boolean activate;

    public static User from(String email, String password, UserGrade grade) {
        return new User(email, password, grade);
    }

    private User(String email, String password, UserGrade grade) {
        this.email = email;
        this.password = password;
        this.gradeLevel = grade.getLevel();
        this.activate = true;
    }

    public void changeGrade(UserGrade grade) {
        this.gradeLevel = grade.getLevel();
    }

    public void deactivate() {
        activate = false;
    }

    public void activate() {
        activate = true;
    }

    public boolean isActive() {
        return activate;
    }

}
