public class MainClass {

    public static void main(String[] args) throws Exception {
        Produto p = new Produto(1, "Computador", 1000.0);
        ClassExplorer.exploreMetada(p);;

        Cliente c = new Cliente(1, "Leonardo", "0123456789", "leonardo.lhpr@gmail.com");
        ClassExplorer.exploreMetada(c);
    }

}
