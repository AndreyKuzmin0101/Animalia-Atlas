package ru.kpfu.itis.kuzmin.animalswebapp.model.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.kuzmin.animalswebapp.model.dao.SpecificUserRepo;
import ru.kpfu.itis.kuzmin.animalswebapp.model.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class SpecificUserRepoImpl implements SpecificUserRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void update(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }
}
