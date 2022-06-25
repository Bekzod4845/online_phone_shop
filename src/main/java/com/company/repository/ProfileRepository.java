package com.company.repository;

import com.company.entity.ProfileEntity;
import com.company.enums.ProfileStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity,String> {
    Optional<ProfileEntity> findByPhoneNumber(String number);

    @Modifying
    @Transactional
    @Query("update ProfileEntity p set p.name =:name ,p.surname =:surname,p.phoneNumber =:phoneNumber where p.id=:profileId")
    void update(@Param("profileId") String profileId, @Param("name") String name, @Param("surname") String surname, @Param(value = "phoneNumber") String phoneNumber);


    Optional<ProfileEntity> findById(String id);

    @Modifying
    @Transactional
    @Query("update  ProfileEntity p set p.status=?2 where p.phoneNumber=?1")
    void updateStatusByPhone(String phone, ProfileStatus status);
}
