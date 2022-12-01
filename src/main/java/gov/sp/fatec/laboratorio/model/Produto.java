package gov.sp.fatec.laboratorio.model;

public class Produto {

    private String nomeProduto;

    private String imagemSource;

    private double precoProduto;

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
