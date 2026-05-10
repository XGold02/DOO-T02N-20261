import java.util.ArrayList;
import java.util.List;

public class CalculadoraLogica {
    private List<String> historico = new ArrayList<>();

    public double calcular(double n1, double n2, String operacao) throws CalculadoraException {
        double resultado;

        switch (operacao) {
            case "+":
                resultado = n1 + n2;
                break;
            case "-":
                resultado = n1 - n2;
                break;
            case "×":
                resultado = n1 * n2;
                break;
            case "÷":
                if (n2 == 0)
                    throw new CalculadoraException("Erro: Divisão por zero não permitida!");
                resultado = n1 / n2;
                break;
            default:
                throw new CalculadoraException("Operação inválida!");
        }

        historico.add(String.format("%.2f %s %.2f = %.2f", n1, operacao, n2, resultado));
        return resultado;
    }

    public List<String> getHistorico() {
        return historico;
    }
}