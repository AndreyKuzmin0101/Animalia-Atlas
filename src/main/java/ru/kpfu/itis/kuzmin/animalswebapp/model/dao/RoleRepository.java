package ru.kpfu.itis.kuzmin.animalswebapp.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.Role;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<User, Integer> {

    @Query("select u.roles from User u where u.id = :userId")
    List<Role> findRolesByUserId(@Param("userId") Integer userId);
}
