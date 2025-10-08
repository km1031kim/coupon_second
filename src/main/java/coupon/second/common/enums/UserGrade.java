package coupon.second.common.enums;

import lombok.Getter;

@Getter
public enum UserGrade {
    BRONZE(1),
    SILVER(2),
    GOLD(3),
    PLATINUM(4),
    DIAMOND(5);

    private final int level;

    UserGrade(int level) {
        this.level = level;
    }

    public static UserGrade fromLevel(int level) {
        for (UserGrade grade : values()) {
            if (grade.getLevel() == level) {
                return grade;
            }
        }
        throw new IllegalArgumentException("Invalid level : " + level);
    }
}
