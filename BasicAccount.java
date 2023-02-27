package ContaCorrenteRefeita;

public class BasicAccount {

    ClientData owner;
    private double balance;
    public AccountHistory history = new AccountHistory();
    public static int accNumGen = 1000000000;
    public String number;

    public BasicAccount(String nome, String cpf, String rg, String password, String address, int balance) {
        owner = new ClientData(nome, cpf, rg, password, address);
        number = Integer.toString(accNumGen++);
        withDraw(balance);
        history.addItem("Conta criada em: ", "");
    }

    public boolean withDraw(double value) {
        if (value >= 0 && this != null) {
            this.balance += value;
            history.addItem("Deposito de " + value + " realizado em", "");
            return true;
        }
        history.addItem("Deposito de " + value + " nao finalizado", "");
        return false;
    }

    public boolean transfer(BasicAccount other, double value) {
        other.deposit(value);
        this.withDraw(value);
        history.addItem("Deposito de " + value + " nao finalizado", "");
        return true;
    }

    public boolean deposit(double value) {
        this.balance += value;
        return true;
    }

    public String getCpf() {
        return this.owner.cpf;
    }

    public double getBalance() {
        return this.balance;
    }
}
