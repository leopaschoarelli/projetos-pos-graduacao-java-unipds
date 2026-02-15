import com.google.gson.Gson;

public class MainClass {

    public static void main(String[] args) {
        Produto produto = new Produto(1, "Computador", 1500.0, 5);

        Gson gson = new Gson();
        System.out.println(gson.toJson(produto));
    }

}
