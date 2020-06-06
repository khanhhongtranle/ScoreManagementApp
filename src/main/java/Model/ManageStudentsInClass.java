package Model;

import Model.Entities.Student;
import Model.Entities.StudentsInClass;
import Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}
