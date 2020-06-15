package Model;

import Model.Entities.Re_Examination;
import Model.Entities.Schedule;
import Model.Entities.ScoreSheet;
import Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ManageReExamination {
    private Session session = HibernateUtil.getSessionFactory().openSession();
    private Transaction transaction;

    public Re_Examination getARecord(String ngaybatdau, String ngayketthuc){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        List<Re_Examination> record = null;
        try{
            String hql = "SELECT a FROM Re_Examination as a where a.key.beginDate = :b and a.key.endingDate = :e";
            Query query= session.createQuery(hql);
            query.setParameter("b", ngaybatdau);
            query.setParameter("e", ngayketthuc);
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

    public void insertInto(Re_Examination record){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        try{
            session.save(record);
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
