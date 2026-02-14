public class MainClass {

    public static void main(String[] args){
        ProdutoRepo repo = new ProdutoRepo();

        //Produto p = repo.findById(10).get();
        //Produto p = repo.findById(10).orElse(new Produto(-1, "Produto inexistente", 0));
        Produto p = repo.findById(10).orElseThrow(() -> new RuntimeException("Produto inexistente"));
        System.out.println(p.getNome());
    }

}
