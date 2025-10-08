package coupon.second.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import coupon.second.api.dto.QUserResponse;
import coupon.second.api.dto.UserResponse;
import coupon.second.api.dto.UserSearchCondition;
import coupon.second.common.enums.UserGrade;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static coupon.second.domain.entity.QUser.user;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    // 사기다 사기
    @Override
    public Page<UserResponse> searchAll(UserSearchCondition condition, Pageable pageable) {

        List<UserResponse> results = queryFactory
                .select(new QUserResponse(
                        user.id,
                        user.email,
                        user.gradeLevel
                ))
                .where(
                        statusEq(condition.getUserStatus()),
                        emailEq(condition.getEmail()),
                        gradeGoe(condition.getGradeGoe()),
                        gradeLoe(condition.getGradeLoe())
                )
                .from(user)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(user.count())
                .from(user)
                .where(
                        statusEq(condition.getUserStatus()),
                        emailEq(condition.getEmail()),
                        gradeGoe(condition.getGradeGoe()),
                        gradeLoe(condition.getGradeLoe())
                );

        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
    }

    private BooleanExpression emailEq(String email) {
        return email != null ? user.email.eq(email) : null;
    }

    private BooleanExpression statusEq(Boolean status) {
        return status != null ? user.activate.eq(status) : null;
    }

    private BooleanExpression gradeGoe(UserGrade grade) {
        return grade != null ? user.gradeLevel.goe(grade.getLevel()) : null;
    }

    private BooleanExpression gradeLoe(UserGrade grade) {
        return grade != null ? user.gradeLevel.loe(grade.getLevel()) : null;
    }
}
