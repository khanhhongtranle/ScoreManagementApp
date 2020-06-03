package Model;

import Model.Entities.AClass;
import Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ManageClass {

    private Session session = HibernateUtil.getSessionFactory().openSession();
    private Transaction transaction;

    public AClass getClassAtClassName(String name){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        List<AClass> aclass = null;
        try{
            String hql = "SELECT a FROM AClass as a where a.className = :tenlop";
            Query query= session.createQuery(hql);
            query.setParameter("tenlop",name);
            aclass = query.list();
            transaction.commit();
            if (aclass.size() == 0){
                return null;
            }
            return aclass.get(0);
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

    public void insertInto(AClass aClass){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        try{
            session.save(aClass);
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
}
