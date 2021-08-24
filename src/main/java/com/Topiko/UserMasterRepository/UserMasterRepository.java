package com.Topiko.UserMasterRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Topiko.UserMasterModels.Users;

import java.util.List;

@Repository
public interface UserMasterRepository extends JpaRepository<Users,Integer>
{
    @Query(value="select * from users a where a.mobile= :mobile and a.name=:name", nativeQuery=true)
    List<Users> getUserDetailsByNameAndMobileNumber(Long mobile, String name);



}
