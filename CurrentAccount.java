package ContaCorrente;

import java.util.Scanner;

public class CurrentAccount {

    private String clientName;
    private String clientCPF;
    private String clienteAccNumber;
    private String clientPassword;
    private double clientBalance;
    private boolean isLoged = false;
    private static int numGenerator = 10000000;

    /**
     * @param _name
     * @param _cpf
     * @param _password
     * @param _balance
     */
    public CurrentAccount(String _name, String _cpf, String _password, double _balance) {

        this.clientName = _name;
        this.clientCPF = _cpf;
        this.clienteAccNumber = Integer.toString(CurrentAccount.numGenerator++);
        this.clientPassword = _password;
        this.clientBalance = _balance;
    }

    public String getClientName() {
        return this.clientName;
    }

    public String getAccNumber() {
        return this.clienteAccNumber;
    }

    public void login() {

        Scanner input = new Scanner(System.in);
        // input.nextLine();
        String cpf, password;

        log("Para entrar na sua conta, digite suas credenciais.\n\nNome de usuario (CPF): ");
        cpf = input.nextLine();

        if (cpf.equalsIgnoreCase(clientCPF)) {

            log("\n\nSenha: ");
            password = input.nextLine();

            if (password.equalsIgnoreCase(clientPassword)) {
                this.isLoged = true;
                log(this.clientName + " realizou login.");
            } else {
                log("Senha incorreta.");
                return;
            }
        } else {
            log("CPF incorreto.");
            return;
        }
    }

    public void getBalance() {

        Scanner input = new Scanner(System.in);
        String password;

        if (this.isLoged) {

            log("\nForneca sua senha para ver o saldo disponivel: ");

            password = input.nextLine();

            if (password.equalsIgnoreCase(clientPassword)) {
                log("\nSaldo disponivel: " + this.clientBalance);
            }

        }

        else {
            log("\nUsuario nao esta conectado.");
        }
    }

    public void deposit(double value) {

        if (value <= 0)
            return;

        this.clientBalance += value;
    }

    public void balanceTransfer(double value, CurrentAccount other) {
        Scanner input = new Scanner(System.in);

        if (this.isLoged) {

            if (value <= this.clientBalance && value > 0) {

                other.deposit(value);
                this.clientBalance -= value;
            } else {
                log("\n[ERRO]: Saldo insuficiente ou valor digitado e invalido.");
            }
        } else {
            log("Usuario nao efetuou login.");
            return;
        }
    }

    private void log(String x) {
        System.out.println(x);
    }
}