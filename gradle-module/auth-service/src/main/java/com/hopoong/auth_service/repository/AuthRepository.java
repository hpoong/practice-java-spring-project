package com.hopoong.auth_service.repository;

import com.hopoong.auth_service.model.UserModel;
import com.hopoong.core_service.entity.QRoleEntity;
import com.hopoong.core_service.entity.QUsersEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthRepository {

    private final JPAQueryFactory queryFactory;

    public UserModel findUsersEntityById(Long userId) {
        QUsersEntity qUsersEntity = QUsersEntity.usersEntity;
        QRoleEntity qRoleEntity = QRoleEntity.roleEntity;

        return queryFactory.select(
                    Projections.constructor(
                            UserModel.class,
                            qUsersEntity.name,
                            qUsersEntity.password,
                            qUsersEntity.email,
                            qRoleEntity.roleCode
                    )
                )
                .from(qUsersEntity)
                .join(qRoleEntity)
                .on(qUsersEntity.roleId.eq(qRoleEntity.id))
                .where(qUsersEntity.id.eq(userId))
                .fetchFirst();
    }

}
