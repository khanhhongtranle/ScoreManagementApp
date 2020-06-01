package Model;

import Model.Entities.Account;
import Util.HibernateUtil;
import org.hibernate.*;

import java.util.List;
import java.util.Queue;

public class ManageAccount {

    private Session session = HibernateUtil.getSessionFactory().openSession();
    private Transaction transaction;

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

    //READ 'password' column in 'taikhoan' table where 'username' = un
    public String getPasswordAt(String un){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        List pw = null;
        try{
            String hql = "SELECT a.password FROM Account as a where a.username =" + un;
            Query query= session.createQuery(hql);
            pw = query.list();
            transaction.commit();
            return pw.get(0).toString();
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

    //UPDATE 'password' column in 'taikhoan' table
    public void updatePassword(Account account){
        if (!session.isOpen()){
            session = HibernateUtil.getSessionFactory().openSession();
        }
        transaction = session.beginTransaction();
        try{
            String hql = "UPDATE Account set password = " +account.getPassword()
                    + " WHERE username ="+account.getUsername();
            Query query= session.createQuery(hql);
            //query.setParameter("username")
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
