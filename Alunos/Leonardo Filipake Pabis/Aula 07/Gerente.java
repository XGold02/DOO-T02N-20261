
public class Gerente extends Funcionario {
    static private final double bonus = 0.35;
    public Gerente (){

    }

    public Gerente(String nome, int idade, Lojas loja, double salarioBase, String cidade, String bairro, String rua){
        super(nome, idade, loja, salarioBase, cidade, bairro, rua);
    }


    public void apresentarse() {
        System.out.println("Gerente: " + this.getNome() + " | Idade: " + this.getIdade() + " | Loja: " + loja.getNomeFantasia());
    }

    public double getBonus() {
        return this.bonus;
    }

    public static void main(String[] args) {
        Lojas loja = new Lojas();
        loja.setNomeFantasia("ABC");
        Gerente g = new Gerente("ge", 25, loja, 1000, "cascavel", "country,", "acre");
        g.apresentarse();
        System.out.println(g.getLoja());
        g.endereco.apresentarLogradouro();
        System.out.println(g.endereco);
        g.receberSalario(1000);
        g.receberSalario(1500);
        g.receberSalario(1250);
        System.out.println(g.calcularMediaSalarial());
        System.out.println(g.calcularBonusSalarial(bonus));
        
    }
}

