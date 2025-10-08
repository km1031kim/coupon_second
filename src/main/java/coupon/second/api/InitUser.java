package coupon.second.api;

import coupon.second.common.enums.UserGrade;
import coupon.second.domain.entity.User;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitUser {

    private final InitUserService initUserService;

    @PostConstruct
    public void init() {
        initUserService.init();
    }


    @Component
    static class InitUserService {
        @PersistenceContext private EntityManager em;

        @Transactional
        public void init() {

            for (int i = 0; i < 20; i++) {
                User user = User.from("km" + i + "@gmail.com", "1234", UserGrade.BRONZE);
                em.persist(user);
            }


            for (int i = 20; i < 40; i++) {
                User user = User.from("km" + i + "@gmail.com", "1234", UserGrade.SILVER);
                em.persist(user);
            }

            for (int i = 40; i < 60; i++) {
                User user = User.from("km" + i + "@gmail.com","1234", UserGrade.GOLD);
                em.persist(user);
            }


            for (int i = 60; i < 80; i++) {
                User user = User.from("km" + i + "@gmail.com","1234", UserGrade.PLATINUM);
                em.persist(user);
            }

            for (int i = 80; i < 100; i++) {
                User user = User.from("km" + i + "@gmail.com", "1234", UserGrade.DIAMOND);
                em.persist(user);
            }

        }

    }
}
