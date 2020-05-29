import Util.HibernateUtil;
import Manager.ManageAccount;
import org.hibernate.SessionFactory;

public class App {
    private static SessionFactory sessionFactory;

    public static void main(String[] agrs){
        sessionFactory = HibernateUtil.getSessionFactory();

        App mainApp = new App();

        ManageAccount manageAccount = new ManageAccount();

        manageAccount.listAllAccounts();
    }
}
