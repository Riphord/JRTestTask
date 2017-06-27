package com.riphord.dao;

import com.riphord.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao{
    @Override
    public User findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveUser(User user) {
        persist(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        Query query = getSession().createSQLQuery("DELETE FROM user WHERE id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria();
        return criteria.list();
    }

    @Override
    public List<User> finaAllUsersByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.in("name", new String[]{name}));
        return criteria.list();
    }

    @Override
    public List<User> finaAllUsersByAge(Integer age) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.in("age", new Integer[]{age}));
        return criteria.list();
    }
}
