package Model;

import Model.Entities.Account;
import Model.Entities.Student;
import Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ManageStudent {

    private Session session = HibernateUtil.getSessionFactory().openSession();
    private Transaction transaction;

    public void insertInto(Student student){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        try{
            session.save(student);
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

    public List<Student> getAllOfStudentInClass(int stt){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        List<Student> list = null;
        try{
            String hql = "SELECT a FROM Student as a where a.classNo = :stt";
            Query query= session.createQuery(hql);
            query.setParameter("stt",stt);
            list = query.list();
            transaction.commit();
        }catch (HibernateException e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;
    }

}
