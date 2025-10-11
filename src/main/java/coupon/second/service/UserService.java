package coupon.second.service;

import coupon.second.common.exception.UserAlreadyInactiveException;
import coupon.second.domain.entity.User;
import coupon.second.repository.UserRepository;
import coupon.second.service.dto.UserServiceRequest;
import coupon.second.service.dto.UserServiceResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;


    @Transactional
    public UserServiceResponse save(UserServiceRequest request) {
        String email = request.getEmail();
        checkDuplicatedEmail(email);
        User user = User.from(email, passwordEncoder.encode(request.getPassword()), request.getGrade());

        return UserServiceResponse.of(userRepository.save(user));
    }


    @Transactional
    public UserServiceResponse update(Long userId, UserServiceRequest request) {
        User findUser = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Optional.ofNullable(request.getGrade()).ifPresent(findUser::changeGrade);

        return UserServiceResponse.of(findUser);
    }

    @Transactional
    public void delete(Long userId) {
        User findUser = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

        if (!findUser.isActive()) {
            throw new UserAlreadyInactiveException(findUser.getEmail());
        }

        findUser.deactivate();
    }

    private void checkDuplicatedEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EntityExistsException();
        }
    }
}
