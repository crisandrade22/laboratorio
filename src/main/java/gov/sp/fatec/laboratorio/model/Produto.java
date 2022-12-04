package gov.sp.fatec.laboratorio.model;

public class Produto {

    private String nomeProduto;

    private String imagemSource;

    private Double precoProduto;

    private Integer quantidade;

    private String valorTotal;

    public Produto(String nomeProduto, Double precoProduto, Integer quantidade, String valorTotal) {
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }


    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    private String cor;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getImagemSource() {
        return imagemSource;
    }

    public void setImagemSource(String imagemSource) {
        this.imagemSource = imagemSource;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", imagemSource='" + imagemSource + '\'' +
                ", preçoProduto=" + precoProduto +
                ", cor='" + cor + '\'' +
                '}';
    }
}
