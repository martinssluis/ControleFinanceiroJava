import entidades.Transacao;
import entidades.Conta;
import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o seu nome: ");
        String titular = scanner.nextLine();

        System.out.print("Informe o seu salário: ");
        double salario = scanner.nextDouble();

        Conta conta = new Conta(titular, salario);

        boolean continuar = true;

        while (continuar) {
            System.out.println("\nEscolha uma opção: ");
            System.out.println("1 - Crédito");
            System.out.println("2 - Débito");
            System.out.println("3 - Exibir saldo atual");
            System.out.println("4 - Exibir transações");
            System.out.println("5 - Planejamento Mensal (isso não afeta o extrato) ");
            System.out.println("6 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    // Creditar
                    System.out.println("Digite o valor a ser creditado: ");
                    double valorCredito = scanner.nextDouble();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.println("Digite a descrição da transação: ");
                    String descricaoCredito = scanner.nextLine();
                    System.out.println("Digite a categoria da transação (Ex: lazer, estudos): ");
                    String categoriaCredito = scanner.nextLine();
                    conta.creditar(valorCredito, descricaoCredito, categoriaCredito);
                    System.out.println("Valor creditado com sucesso.");
                    break;

                case 2:
                    // Debitar
                    System.out.println("Digite o valor a ser debitado: ");
                    double valorDebito = scanner.nextDouble();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.println("Digite a descrição da transação: ");
                    String descricaoDebito = scanner.nextLine();
                    System.out.println("Digite a categoria da transação (Ex: lazer, estudos): ");
                    String categoriaDebito = scanner.nextLine();
                    conta.debitar(valorDebito, descricaoDebito, categoriaDebito);
                    System.out.println("Valor debitado com sucesso.");
                    break;

                case 3:
                    // Exibir saldo
                    System.out.println("Saldo atual: $" + conta.getSaldo());
                    break;

                case 4:
                    // Exibir transações e calcular percentuais
                    System.out.println("\nTransações realizadas:");
                    if (conta.getTransacoes().isEmpty()) {
                        System.out.println("Nenhuma transação realizada até o momento.");
                    } else {
                        double totalCredito = 0;
                        double totalDebito = 0;

                        for (Transacao transacao : conta.getTransacoes()) {
                            if ("Crédito".equals(transacao.getTipoOperacao())) {
                                totalCredito += transacao.getValor();
                            } else if ("Débito".equals(transacao.getTipoOperacao())) {
                                totalDebito += transacao.getValor();
                            }
                        }

                        double totalTransacoes = totalCredito + totalDebito;

                        for (Transacao transacao : conta.getTransacoes()) {
                            double percentual = 0;
                            if ("Crédito".equals(transacao.getTipoOperacao())) {
                                percentual = (transacao.getValor() / totalCredito) * 100;
                                System.out.println("\033[0;32m" + transacao.toString() + " - " + String.format("%.2f", percentual) + "% \033[0m");
                            } else if ("Débito".equals(transacao.getTipoOperacao())) {
                                percentual = (transacao.getValor() / totalDebito) * 100;
                                System.out.println("\033[0;31m" + transacao.toString() + " - " + String.format("%.2f", percentual) + "% \033[0m");
                            }
                        }

                        System.out.println("\nResumo:");
                        System.out.println("Crédito total: $" + totalCredito + " (" + String.format("%.2f", (totalCredito / totalTransacoes) * 100) + "%)");
                        System.out.println("Débito total: $" + totalDebito + " (" + String.format("%.2f", (totalDebito / totalTransacoes) * 100) + "%)");
                    }
                    break;

                case 5:
                    System.out.print("Informe o valor das despesas fixas que você tem durante o mês (contas, mensalidades etc): ");
                    double despesa = scanner.nextDouble();
                    scanner.nextLine(); // Limpar o buffer

                    String resposta;
                    do {
                        System.out.print("Deseja adicionar mais uma despesa? (s/n): ");
                        resposta = scanner.nextLine();

                        if (resposta.equalsIgnoreCase("s")) { // Comparação correta
                            System.out.print("Informe o valor da nova despesa: ");
                            double novaDespesa = scanner.nextDouble();
                            despesa += novaDespesa; // Acumula a despesa
                            scanner.nextLine(); // Limpa o buffer
                        }

                    } while (resposta.equalsIgnoreCase("s")); // Continua enquanto a resposta for "s"

                    double dinheiroDisponivel = salario - despesa;
                    System.out.println("Você tem disponível este mês: R$" + dinheiroDisponivel);
                    System.out.println("Você tem disponivel por dia para gastar em média: R$ " + dinheiroDisponivel/30);
                    break;


                case 6:
                    // Sair
                    System.out.println("Saindo do sistema...");
                    continuar = false;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }
}
