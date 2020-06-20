package Model;

import Model.Entities.ScoreSheet;
import Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ManageScoreSheet {

    private Session session = HibernateUtil.getSessionFactory().openSession();
    private Transaction transaction;


    public void insertInto(ScoreSheet scoreSheet){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        try{
            session.save(scoreSheet);
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

    public List<ScoreSheet> getScoreSheetInAClass( String MaMonHoc){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        List<ScoreSheet> list = null;
        try{
            String hql = "SELECT a FROM ScoreSheet as a where a.key.subNo = :s";
            Query query= session.createQuery(hql);
            query.setParameter("s", MaMonHoc);
            list = query.list();
            transaction.commit();
            if (list.size() == 0){
                return null;
            }
            return list;
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

    public ScoreSheet getARecord( String MaMonHoc, String MSSV){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        List<ScoreSheet> record = null;
        try{
            String hql = "SELECT a FROM ScoreSheet as a where a.key.subNo = :s and a.key.MSSV = :ms";
            Query query= session.createQuery(hql);
            query.setParameter("s", MaMonHoc);
            query.setParameter("ms", MSSV);
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

    public ScoreSheet getARecordBySubNoAndMSSV(String MaMonHoc, String MSSV){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        List<ScoreSheet> record = null;
        try{
            String hql = "SELECT a FROM ScoreSheet as a where  a.key.subNo = :s and a.key.MSSV = :ms";
            Query query= session.createQuery(hql);
            query.setParameter("s", MaMonHoc);
            query.setParameter("ms", MSSV);
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

    public void dropARecord( String MaMonHoc, String MSSV){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        try{
            String hql = "DELETE ScoreSheet as a WHERE  a.key.subNo = :m and a.key.MSSV = :ms ";
            Query query = session.createQuery(hql);
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
}
