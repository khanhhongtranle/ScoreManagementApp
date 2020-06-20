package Model;

import Model.Entities.Student;
import Model.Entities.StudentsInClass;
import Model.Entities.Subject;
import Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ManageStudentsInClass {
    private Session session = HibernateUtil.getSessionFactory().openSession();
    private Transaction transaction;

    public void insertInto(StudentsInClass student){
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

    public List<String> getListMSSVInAClass(String MaMonHoc){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        List<String> std = null;
        try{
            String hql = "SELECT a.key.MSSV FROM StudentsInClass as a where a.key.subNo = :m";
            Query query= session.createQuery(hql);
            query.setParameter("m",MaMonHoc);
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

    public List<String> getListSubjectsOfAStudent(String mssv){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        List<String> std = null;
        try{
            String hql = "SELECT a.key.subNo FROM StudentsInClass as a where a.key.MSSV = :mssv";
            Query query= session.createQuery(hql);
            query.setParameter("mssv",mssv);
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

    public void dropARecord(int STTLop, String MaMonHoc, String MSSV){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        try{
            String hql = "DELETE StudentsInClass as a WHERE a.key.classNo = :s and a.key.subNo = :m and a.key.MSSV = :ms ";
            Query query = session.createQuery(hql);
            query.setParameter("s", STTLop);
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
