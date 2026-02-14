package main;

import core.ContaBancaria;
import core.ContaEspecial;
import core.Produto;

public class MainClass {

    public static void main(String[] args) {

        //Produto p = new Produto(123, "Computador", 2000.0f, 10);

        ContaBancaria c1, c2;
        c1 = new ContaBancaria(123, "Leonardo");
        c2 = new ContaEspecial(124, "Henrique", 200);

        c1.creditar(100);
        c2.creditar(100);

        System.out.println(c1);
        System.out.println(c2);

        if (c1.debitar(150)) {
            System.out.println("Debito efetuado!");
            System.out.println(c1);
        } else {
            System.out.println("Saldo insuficiente para a conta: " + c2.getNumero());
        }

        if (c2.debitar(150)) {
            System.out.println("Debito efetuado!");
            System.out.println(c2);
        } else {
            System.out.println("Saldo insuficiente para a conta: " + c2.getNumero());
        }

    }

}
