package Manager;

import Entities.Account;
import Util.HibernateUtil;
import org.hibernate.*;

import java.util.Iterator;
import java.util.List;

public class ManageAccount {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    //READ all accounts in 'taikhoan' table
    public void listAllAccounts(){
        Session session = sessionFactory.openSession();
        try{
            //String sql = "SELECT a FROM taikhoan as a";
            List accounts = session.createQuery("From Account").list();
            for (Iterator iterator = accounts.iterator(); iterator.hasNext(); ){
                Account account = (Account) iterator.next();
                System.out.println(account.getUsername());
                System.out.println(account.getPassword());
                System.out.println(account.getMSSV());
            }
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
