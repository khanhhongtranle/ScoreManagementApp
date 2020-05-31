package Manager;

import Entities.Account;
import Util.HibernateUtil;
import org.hibernate.*;

import java.util.Iterator;
import java.util.List;

public class ManageAccount {
    //private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Session session = HibernateUtil.getSessionFactory().openSession();
    private Transaction transaction;

    //READ all accounts in 'taikhoan' table
    public void listAllAccounts(){
        //Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        try{
            String sql = "SELECT a FROM Account as a";
            List accounts = session.createQuery(sql).list();
            for (Iterator iterator = accounts.iterator(); iterator.hasNext(); ){
                Account account = (Account) iterator.next();
                System.out.println(account.getUsername());
                System.out.println(account.getPassword());
                System.out.println(account.getMSSV());
            }
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
