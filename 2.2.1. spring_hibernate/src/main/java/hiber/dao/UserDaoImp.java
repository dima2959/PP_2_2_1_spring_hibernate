package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        String hql = "from User";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    public Car getCar(Long id) {
        return sessionFactory.getCurrentSession().get(Car.class, id);
    }

    public User getUserByCar(String model, int series) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from User as user where user.car.model = :model and user.car.series = :series");
        query.setParameter("model", model);
        query.setParameter("series", series);
        return (User) query.getSingleResult();
    }
}