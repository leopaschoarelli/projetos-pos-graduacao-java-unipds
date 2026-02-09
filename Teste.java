void main() {

  /*
  IO.println("---------------------------------------------");
  IO.println("Valores padrões para atributos");
  IO.println("Nome: " + item1.nome + " - String: null");
  IO.println("Em promoção: " + item1.emPromocao + " - boolean: false");
  IO.println("Preço: " + item1.preco + " - double: 0.0");
  IO.println("Id: " + item1.id + " - long: 0");
  IO.println("Categoria: " + item1.categoria + " - int: 0");
  IO.println("---------------------------------------------");
  */

  /*
  ItemCardapio item1 = new ItemCardapio();
  item1.nome = "Refresco do Chaves";
  item1.descricao = "Suco de limão que parece de tamarindo e tem gosto de groselha.";
  item1.emPromocao = false;
  item1.preco = 2.99;
  item1.id = 1L;
  item1.categoria = 4;
  */

  /*
  var item2 = new ItemCardapio();
  item2.nome = "Sanduíche de Presunto do Chaves";
  item2.descricao = "Sanduíche de presunto simples, mas feito com muito amor.";
  item2.emPromocao = true;
  item2.preco = 3.50;
  item2.precoComDesconto = 2.99;
  item2.id = 3_000_000_000L;
  item2.categoria = 2;
  */

  var item1 = new ItemCardapio(1L, "Refresco do Chaves", "Suco de limão que parece de tamarindo e tem gosto de groselha.", 2.99, 4);

  var item2 = new ItemCardapio(2L, "Sanduíche de Presunto do Chaves", "Sanduíche de presunto simples, mas feito com muito amor.", 3.50, 2);
  item2.definePromocao(2.99);

  var item3 = new ItemCardapio(3L, "Torta de Frango da Dona Florinda", "Torta de frango com recheio cremoso e massa crocante.", 12.99, 2);
  item3.definePromocao(10.99);

  var item4 = new ItemCardapio(4L, "Pipoca do Quico", "Balde de pipoca preparado com carinho pelo Quico.", 4.99, 2);
  item4.definePromocao(3.99);

  var item5 = new ItemCardapio(5L, "Água de Jamaica", "Água aromatizada com hibisco e toque de açúcar.", 2.50, 4);
  item5.definePromocao(2.00);

  var item6 = new ItemCardapio(6L, "Churros do Chaves", "Churros recheados com doce de leite, clássicos e irresistíveis.", 4.99, 3);
  item6.definePromocao(3.99);

  var item7 = new ItemCardapio(7L, "Tacos de Carnitas", "Tacos recheados com carne tenra.", 25.90, 2);

  var item8 = new ItemCardapio(8L, "Item 11", "Item 11", 2.99, 4);

  IO.println("---------------------------------------------");
  IO.println("Nome: " + item2.nome);
  IO.println("Em promoção: " + item2.emPromocao);
  IO.println("Preço com Desconto: " + item2.precoComDesconto);

  if (item2.emPromocao) {
    var porcentagemDesconto = item2.calculaPorcentagemDesconto();
    IO.println("Porcentagem de desconto: " + porcentagemDesconto);
    IO.println("Preço: de " + item2.preco + " por " + item2.precoComDesconto);
  } else {
    IO.println("Preço: " + item2.preco);
    IO.println("Item não está em promoção");
  }

  IO.println("Categoria: " + item2.obtemNomeCategoria());

  IO.println("---------------------------------------------");
  IO.println("Arrays");

  double[] precos = new double[7];

  precos[0] = 2.99;
  precos[1] = 3.50;
  precos[2] = 12.99;
  precos[3] = 4.99;
  precos[4] = 2.50;
  precos[5] = 4.99;
  precos[6] = 25.90;

  boolean[] emPromocao = {false, true, true, true, true, true, false};

  IO.println("Preço do Item 3: " + precos[2]);
  IO.println("Tamanho do Array de Preços: " + precos.length);
  IO.println("Tamanho do Array emPromocao: " + emPromocao.length);
  IO.println("O segundo item tem promoção? " + emPromocao[1]);

  double totalDePrecos = 0.0;
  int i = 0;
  while (i < precos.length) {
    //IO.println("Loop: " + i);
    double preco = precos[i];
    totalDePrecos = totalDePrecos + preco;
    i++;
  }

  IO.println("Soma dos preços com While: " + totalDePrecos);

  int totalDeItensEmPromocao = 0;
  int j = emPromocao.length - 1;
  do {

    // IO.println(j);
    if (emPromocao[j]) {
      totalDeItensEmPromocao++;
    }

    j--;
  } while (j >= 0);

  IO.println("Total de itens em promoção: " + totalDeItensEmPromocao);

  double totalDePrecosComFor = 0.0;
  for (int k = 0; k < precos.length;  k++) {
    double preco = precos[k];
    totalDePrecosComFor += preco;
  }

  IO.println("Soma dos preços com For: " + totalDePrecosComFor);

  // IO.println(preco) // Não visivel
  // IO.println(i);
  // IO.println(j);
  // IO.println(k); // Não visivel

  // forEach
  double totalDePrecosComForEach = 0.0;
  for (double preco : precos) {
    totalDePrecosComForEach += preco;
  }

  IO.println("Soma dos preços com ForEach: " + totalDePrecosComForEach);

  // achar o primeiro preco que é maior que 10.0
  double precoLimite = 10.0;
  double precoMaiorQueLimite = -1.0;
  for (double preco : precos) {

    if (preco > precoLimite) {
      precoMaiorQueLimite = preco;
      break;
    }

  }

  IO.println("O primeiro preço que é maior que " + precoLimite + ": " + precoMaiorQueLimite);
  IO.println("---------------------------------------------");

  // Imprimir todos os precos menores ou iguais ao limite
  for (double preco : precos) {

    if (preco <= precoLimite) {
      IO.println("Preço menor que " + precoLimite + ": " + preco);
      continue;
    }

    IO.println("Processamento pesado para: " + preco); // so queria processar para valores maiores que 10.0
    
  }

  IO.println("---------------------------------------------");

}

class ItemCardapio {

  // atributos
  long id;
  String nome;
  String descricao;
  boolean emPromocao;
  double preco;
  double precoComDesconto;
  int categoria;

  // construtor
  ItemCardapio(long id, String nome, String descricao, double preco, int categoria) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
    this.preco = preco;
    this.categoria = categoria;
  }

  // metodos
  double calculaPorcentagemDesconto() {
    return (preco - precoComDesconto) / preco * 100;
  }

  String obtemNomeCategoria() {
    /*
    categorias:
      1 - Entradas
      2 - Pratos Principais
      3 - Sobremesas
      4 - Bebidas
    */
    String nomeCategoria;

    switch (categoria) { 
      case 1:
        nomeCategoria = "Entradas";
        break;
      case 2:
        nomeCategoria = "Pratos Principais";
        break;
      case 3:
        nomeCategoria = "Sobremesas";
        break;
      case 4:
        nomeCategoria = "Bebidas";
        break;
      default:
        nomeCategoria =  "Não encontrada...";
    }

    return nomeCategoria;
  }

  void definePromocao(double precoComDesconto) {
    emPromocao = true;
    this.precoComDesconto = precoComDesconto;
  }

}