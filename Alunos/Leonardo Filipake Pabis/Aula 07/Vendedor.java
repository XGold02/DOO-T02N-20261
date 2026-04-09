
public class Vendedor extends Funcionario{
    static private final double bonus = 0.2;
    public Vendedor() {

    }

    public Vendedor (String nome, int idade, Lojas loja, Double salarioBase, String cidade, String bairro, String rua) {
        super(nome, idade, loja, salarioBase, cidade, bairro, rua);
    }


/*     public void setLoja(Lojas loja) {
        if ((loja != null) && (!loja.isBlank())){
            this.loja = loja;
        }
        else {
            System.out.println("Digite uma loja válida:");
            Lojas novaLoja = Main.scan.nextLine();
            setLoja(novaLoja);
        }
    }
 */

    public void apresentarse() {
        System.out.println("Vendedor: " + this.getNome() + " | Idade: "+ this.getIdade() + " | Loja: " + this.loja.getNomeFantasia());
    }
    
    public static void main(String[] args) {
        Lojas loja = new Lojas();
        loja.setNomeFantasia("ABC");
        Vendedor v = new Vendedor("ve", 18, loja, 800.0, "Casca", "bair", "rua");
        v.apresentarse();
        System.out.println(v.getLoja());
        v.endereco.apresentarLogradouro();
        System.out.println(v.endereco);
        v.receberSalario(800);
        v.receberSalario(900);
        v.receberSalario(850);
        System.out.println(v.calcularBonusSalarial(bonus));
        System.out.println(v.calcularMediaSalarial());
    }
    
}
