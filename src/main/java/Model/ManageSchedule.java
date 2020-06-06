package Model;

import Model.Entities.Schedule;
import Model.Entities.Student;
import Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ManageSchedule {

    private Session session = HibernateUtil.getSessionFactory().openSession();
    private Transaction transaction;

    public void insertInto(Schedule schedule){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        try{
            session.save(schedule);
            transaction.commit();
        }catch (HibernateException e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public List<Schedule> getAtClass(int classNo){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        List<Schedule> std = null;
        try{
            String hql = "SELECT a FROM Schedule as a where a.key.classNo = :m";
            Query query= session.createQuery(hql);
            query.setParameter("m",classNo);
            std = query.list();
            transaction.commit();
            if (std.size() == 0){
                return null;
            }
            return std;
        }catch (HibernateException e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
