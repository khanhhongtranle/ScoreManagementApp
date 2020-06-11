package Model;

import Model.Entities.PrimaryKey_StudentsInClass;
import Model.Entities.Schedule;
import Model.Entities.ScoreSheet;
import Model.Entities.Student;
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

    public List<ScoreSheet> getScoreSheetInAClass(int SttLop, String MaMonHoc){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        List<ScoreSheet> list = null;
        try{
            String hql = "SELECT a FROM ScoreSheet as a where a.key.classNo = :m and a.key.subNo = :s";
            Query query= session.createQuery(hql);
            query.setParameter("m",SttLop);
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
}
