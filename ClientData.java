package ContaCorrenteRefeita;

public class ClientData {

    public String cpf;
    public String rg;
    public String nome;
    public String address;
    private String password;

    public ClientData(String nome, String cpf, String rg, String password, String address) {

        this.cpf = cpf;
        this.nome = nome;
        this.rg = rg;
        this.password = password;
        this.address = address;
    }

    public String getRg() {
        return this.rg;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPassword() {
        return password;
    }
}
