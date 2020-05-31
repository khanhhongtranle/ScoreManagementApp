package Model;

import Util.HibernateUtil;
import org.hibernate.*;

import java.util.List;

public class ManageAccount {
    //private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Session session = HibernateUtil.getSessionFactory().openSession();
    //private Session session;
    private Transaction transaction;

    /*public ManageAccount(Session session){
        this.session = session;
    }*/

    //READ all accounts in 'taikhoan' table
    public List listAllAccounts(){
        //session = session.getSessionFactory().openSession();
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        List accounts = null;
        try{
            String sql = "SELECT a FROM Account as a";
            accounts = session.createQuery(sql).list();
            transaction.commit();
            //return accounts;
        }catch (HibernateException e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return accounts;
    }
}
