package core;

public class ContaEspecial extends ContaBancaria{

    private double limite;

    public ContaEspecial(int numero, String titular, double limite) {
        super(numero, titular);
        this.limite = limite;
    }


    public boolean debitar(double valor) {
        if (super.saldo + this.limite >= valor) {
            super.saldo -= valor;
            return true;
        }
        return false;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public String toString() {
        return "ContaEspecial{" +
                "numero=" + numero +
                ", titular='" + titular + '\'' +
                ", saldo=" + saldo +
                ", limite=" + limite +

                '}';
    }
}
