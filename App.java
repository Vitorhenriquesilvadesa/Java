package ContaCorrente;

public class App {
    public static void main(String[] args) throws Exception {

        CurrentAccount acc01 = new CurrentAccount("User01", "00011122244", "12345678", 5000);
        CurrentAccount acc02 = new CurrentAccount("User02", "11122233344", "12345678", 5000);

        acc01.login();
        acc02.login();

        acc01.balanceTransfer(100, acc02);

        acc01.getBalance();
        acc02.getBalance();

    }
}
