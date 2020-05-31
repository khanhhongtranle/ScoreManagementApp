import Util.HibernateUtil;
import Manager.ManageAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class App {
    private static SessionFactory sessionFactory;

    public static void main(String[] agrs){

        ManageAccount manageAccount = new ManageAccount();

        manageAccount.listAllAccounts();

    }
}
