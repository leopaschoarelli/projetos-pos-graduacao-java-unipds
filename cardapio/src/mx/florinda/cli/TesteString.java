package mx.florinda.cli;

public class TesteString {

    void main() {
        String nomeItem1 = "Refresco do Chaves";
        IO.println(nomeItem1.toUpperCase());
        IO.println(nomeItem1.toLowerCase());
        IO.println(nomeItem1.length());
        IO.println(nomeItem1.charAt(0));
        IO.println(nomeItem1.charAt(1));
        IO.println(nomeItem1.replace(" ", "-"));
        IO.println(nomeItem1.toLowerCase().replace(" ", "-"));
        IO.println(nomeItem1.contains("Chaves"));
        IO.println(nomeItem1.contains("Kiko"));
        IO.println(nomeItem1.contains("chaves"));
        IO.println("startWith: ".concat(""+nomeItem1.startsWith("Chaves")));
        IO.println("endsWith: "+nomeItem1.endsWith("Chaves"));
        IO.println("concat: "+nomeItem1.concat(" teste"));
        IO.println("substring: " + nomeItem1.substring(0,8));
        IO.println("substring: " + nomeItem1.substring(12));

        String[] pedacos = nomeItem1.split(" ");
        IO.println(pedacos.length);
        for (String pedaco : pedacos) {
            IO.println(pedaco);
        }

        IO.println(nomeItem1 == "Refresco do Chaves");
        String nomeItemDigitado = IO.readln("Digite: ");
        IO.println(nomeItem1 == nomeItemDigitado); // == para classes (ItemCardapio, String, etc), compara se é o mesmo objeto e se a String é hard-coded no código sempre vai ser o mesmo objeto.
        IO.println(nomeItem1.equals(nomeItemDigitado)); // case-sensitive
        IO.println(nomeItem1.equalsIgnoreCase(nomeItemDigitado)); // case-insensitive
    }

}
