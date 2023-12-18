package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BurgerDaoImpl implements BurgerDao {

    private EntityManager entityManager;
    @Autowired
    public BurgerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Transactional
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public Burger findByID(long id) {
        return entityManager.find(Burger.class, id);
    }

    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> typedQuery = entityManager.createQuery("SELECT b FROM Burger b", Burger.class);
        return typedQuery.getResultList();
    }

    @Override
    public List<Burger> findByPrice(int price) {
        TypedQuery<Burger> typedQuery =
                entityManager.createQuery("SELECT b FROM Burger b WHERE b.price >" +
                        " :price ORDER BY b.price DESC", Burger.class)
                        .setParameter("breadType", price);
        return typedQuery.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        TypedQuery<Burger> typedQuery = entityManager.createQuery("SELECT b FROM Burger b WHERE b.breadType = " +
                ":breadType ORDER BY b.breadType DESC", Burger.class)
                .setParameter("breadType", breadType);
        return typedQuery.getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        TypedQuery<Burger> typedQuery = entityManager.createQuery("SELECT b FROM Burger b WHERE b.contents " +
                "LIKE CONCAT('%', :content, '%') ORDER BY b.name", Burger.class)
                .setParameter("content", content);
        return typedQuery.getResultList();
    }
    @Transactional
    @Override
    public Burger update(Burger burger) {
        return entityManager.merge(burger);
    }
    @Transactional
    @Override
    public Burger remove(Long id) {
        Burger burger = findByID(id);
        entityManager.remove(burger);
        return burger;
    }
}