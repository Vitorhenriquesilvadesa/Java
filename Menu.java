package ContaCorrenteRefeita;

import java.util.ArrayList;

public class Menu {

    private boolean running = true;
    private ArrayList<BasicAccount> accounts = new ArrayList<BasicAccount>();

    public Menu() {
    }

    public void mainLoop() {

        DataStream.log("Ola, seja bem vindo ao banco do Vitor");

        while (running) {
            int input = DataStream.requestNumber(
                    "Selecione uma das opções abaixo.\n\n1 - Criar nova conta\n2 - Pedir extrato\n3 - Realizar saque\n4 - Realizar deposito\n5 - Realizar transferencia\n\n6 - Sair");

            switch (input) {
                case 1:
                    if (createNewAccount(accounts)) {
                        DataStream.log("Conta criada com sucesso!!!");
                        DataStream.log("Seu numero de conta é " + (BasicAccount.accNumGen - 1));
                    } else {
                        DataStream.log("Erro ao criar a conta!!!");
                    }

                    break;

                case 2:
                    getHistory();
                    break;

                case 3:
                    makeWithDraw();
                    break;

                case 4:
                    makeDeposit();
                    break;

                case 5:
                    makeTransfer();
                    break;

                case 6:
                    running = false;
                    break;
            }
        }
    }

    private boolean createNewAccount(ArrayList<BasicAccount> Accounts) {

        try {
            String nome = DataStream.requestString("Digite seu nome");
            String cpf = DataStream.requestString("Digite seu cpf");
            String rg = DataStream.requestString("Digite seu rg");
            String address = DataStream.requestString("Digite seu endereço");
            String password = DataStream.requestString("Crie uma senha para sua conta");
            int balance = Integer.parseInt(DataStream.requestString("Digite seu saldo inicial"));
            Accounts.add(new BasicAccount(nome, cpf, rg, password, address, balance));

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    private int accountValidate() {

        String cpf = DataStream.requestString("Digite seu numero de conta para prosseguir");
        String password = DataStream.requestString("Digite sua senha para prosseguir");
        int i;

        try {
            for (BasicAccount basicAccount : accounts) {
                if (cpf.equals(basicAccount.number)) {
                    break;
                }
                DataStream.log("Numero inexistente em nosso banco");
                return -1;
            }
            for (i = 0; i < accounts.size();) {
                if (password.equals(accounts.get(i).owner.getPassword())) {
                    return i;
                }
                DataStream.log("Senha incorreta");
                return -1;
            }
            DataStream.log("Erro, conta não registrada no sistema.");
            return -1;

        } catch (Exception e) {
            DataStream.log("Erro, conta não registrada no sistema.");

            return -1;
        }

    }

    private boolean makeDeposit() {

        int accIndex = accountValidate();
        if (accIndex != -1) {
            int value = DataStream.requestNumber("Digite o valor do deposito.");
            if (value <= 0) {
                DataStream.log("Valor invalido!!");
                return false;
            }

            accounts.get(accIndex).deposit(value);
            DataStream.log("Deposito relizado com sucesso!!!");
            return true;
        }
        DataStream.log("Deposito nao realizado!!!");
        return false;
    }

    private boolean makeWithDraw() {

        int accIndex = accountValidate();
        if (accIndex != -1) {
            int value = DataStream.requestNumber("Digite o valor do saque.");
            if (value <= 0) {
                DataStream.log("Valor invalido!!");
                return false;
            }

            accounts.get(accIndex).withDraw(value);
            DataStream.log("Saque relizado com sucesso!!!");
            return true;
        }
        DataStream.log("Deposito nao realizado!!!");
        return false;
    }

    private boolean getHistory() {

        int accIndex = accountValidate();
        if (accIndex != -1) {
            accounts.get(accIndex).history.getHistory();
            return true;
        }
        DataStream.log("Não foi possível exibir o extrato da conta");
        return false;
    }

    private boolean makeTransfer() {

        int accIndex = accountValidate();
        int otherIndex = 0;

        if (accIndex != -1) {
            String destinationNum = DataStream
                    .requestString("Digite o numero de conta do usuário que irá receber a transferencia.");
            double value = DataStream.requestNumber("Digite o valor da transferencia");

            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).owner.cpf.equals(destinationNum)) {
                    otherIndex = i;
                }
            }
            accounts.get(accIndex).transfer(accounts.get(otherIndex), value);
            DataStream.log("Transferencia de " + value + " realizado.");
            return true;
        }
        DataStream.log("Não foi possível realizar a transferencia");
        return false;
    }
}
