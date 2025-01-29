package entidades;

public class Transacao {
    private String tipoOperacao;
    private String descricao;
    private String categoria;
    private double valor;

    public Transacao(String tipoOperacao, String descricao, String categoria, double valor){
        this.tipoOperacao = tipoOperacao;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public String getDescricao() {return descricao;}

    public void setDescricao(String descricao) {this.descricao = descricao;}

    public String getCategoria(){
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    public double getValor(){
        return valor;
    }


    @Override
    public String toString(){
        return "Tipo de Operação: " + tipoOperacao + ", Descrição: " + descricao + ", Categoria: " + categoria + ", Valor: " + String.format("%.2f", valor);
    }
}