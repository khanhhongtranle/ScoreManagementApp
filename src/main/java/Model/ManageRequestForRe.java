package Model;

import Model.Entities.Re_Examination;
import Model.Entities.RequestsForRe;
import Model.Entities.Schedule;
import Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ManageRequestForRe {
    private Session session = HibernateUtil.getSessionFactory().openSession();
    private Transaction transaction;

    public List<RequestsForRe> getRecordsById(int id_re){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        List<RequestsForRe> record = null;
        try{
            String hql = "SELECT a FROM RequestsForRe as a where a.key.id_re = :id";
            Query query= session.createQuery(hql);
            query.setParameter("id", id_re);
            record =  query.list();
            transaction.commit();
            if (record.size() == 0){
                return null;
            }
            return record;
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

    public void insertInto(RequestsForRe request){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        try{
            session.save(request);
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

    public void dropRecord(int id_re, String MSSV, String MaMonHoc){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        try{
            String hql = "DELETE RequestsForRe as a WHERE a.key.id_re = :i and a.key.subNo = :m and a.key.mssv = :ms ";
            Query query = session.createQuery(hql);
            query.setParameter("i", id_re);
            query.setParameter("m", MaMonHoc);
            query.setParameter("ms", MSSV);
            query.executeUpdate();
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

    public RequestsForRe getARecord(int id_re, String MSSV, String MaMonHoc){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        List<RequestsForRe> record = null;
        try{
            String hql = "SELECT a FROM RequestsForRe as a where a.key.id_re = :id and a.key.mssv = :m and a.key.subNo = :s";
            Query query= session.createQuery(hql);
            query.setParameter("id", id_re);
            query.setParameter("m", MSSV);
            query.setParameter("s", MaMonHoc);
            record =  query.list();
            transaction.commit();
            if (record.size() == 0){
                return null;
            }
            return record.get(0);
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
