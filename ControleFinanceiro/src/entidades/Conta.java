package entidades;

import java.util.ArrayList;
import java.util.List;


public class Conta {

    private String titular;
    private double salario;
    private double saldo;
    private List<Transacao> transacoes;

    public Conta(String titular, double salario) {
        this.titular = titular;
        this.salario = salario;
        this.saldo = salario;
        this.transacoes = new ArrayList<>();
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void creditar(double valor, String descricao, String categoria) {
        saldo += valor;
        transacoes.add(new Transacao("Crédito", descricao, categoria, valor));
    }

    public void debitar(double valor, String descricao, String categoria) {
        saldo -= valor;
        transacoes.add(new Transacao("Débito", descricao, categoria, valor));
    }


    @Override
    public String toString() {
        return "Titular: "
                + titular
                + " Salário: $"
                + String.format("%.2f", salario)
                + " Saldo: $"
                + String.format("%.2f", saldo);
    }

}
