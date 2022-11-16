package gov.sp.fatec.laboratorio.model;

public class Produto {

    private String nomeProduto;

    private String imagemSource;

    private double preçoProduto;

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

    public double getPreçoProduto() {
        return preçoProduto;
    }

    public void setPreçoProduto(double preçoProduto) {
        this.preçoProduto = preçoProduto;
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
                ", preçoProduto=" + preçoProduto +
                ", cor='" + cor + '\'' +
                '}';
    }
}
