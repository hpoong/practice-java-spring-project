package com.hopoong.userservice.api;

import com.hopoong.userservice.domain.User;
import com.hopoong.userservice.event.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {


    /*
        ::::: Producer 코드
        회원가입 이벤트 → "user.registered" 메시지 발행
        비밀번호 변경 이벤트 → "user.password.changed" 메시지 발행
     */

    private final UserRepository userRepository;
    private final EventPublisher eventPublisher;


    @Transactional
    public void register(UserModel.RegisterUserModel registerUserModel) {
        var user = new User().toEntity(registerUserModel);
        userRepository.save(user);
        eventPublisher.publishUserRegistered(user.getId());
    }

    @Transactional
    public void resetPassword(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        eventPublisher.publishUserPasswordChanged(user.getId());
    }

}
