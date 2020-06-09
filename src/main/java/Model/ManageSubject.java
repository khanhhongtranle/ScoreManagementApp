package Model;

import Model.Entities.Subject;
import Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ManageSubject {
    private Session session = HibernateUtil.getSessionFactory().openSession();
    private Transaction transaction;

    public void insertInto(Subject subject){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        try{
            session.save(subject);
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

    public Subject getSubjectAtSubNo(String MaMonHoc){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        List<Subject> std = null;
        try{
            String hql = "SELECT a FROM Subject as a where a.subNo = :m";
            Query query= session.createQuery(hql);
            query.setParameter("m",MaMonHoc);
            std = query.list();
            transaction.commit();
            if (std.size() == 0){
                return null;
            }
            return std.get(0);
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

    public Subject getSubjectAtSubName(String TenMonHoc){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        List<Subject> std = null;
        try{
            String hql = "SELECT a FROM Subject as a where a.subName = :m";
            Query query= session.createQuery(hql);
            query.setParameter("m",TenMonHoc);
            std = query.list();
            transaction.commit();
            if (std.size() == 0){
                return null;
            }
            return std.get(0);
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
