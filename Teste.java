void main() {

  /*
  Tipos primitivos:
    - char
    - byte
    - short
    - int
    - long
    - float
    - double
    - boolean
  https://dev.java/learn/language-basics/primitive-types/
  */

  /*
  categorias:
    1 - Entradas
    2 - Pratos Principais
    3 - Sobremesas
    4 - Bebidas
  */
 int categoria = 2;

  // var = inferencia de tipos
  var nome = "Sanduíche de Presunto do Chaves";
  var descricao = "Sanduíche de presunto simples, mas feito com muito amor.";
  var emPromocao = true;

  //float preco = 3.50f; // float tem 32 bits de armazenamento e double tem 64 bits de armazenamento
  var preco = 3.50;
  var precoComDesconto = 2.99; 

  /*
  byte..:  8 bits = -2^7 (-2 elevado a 7) até 2^7-1 (2 elevado a 7 menos 1) = -128 até 127
  short.: 16 bits = -32768 até 32767
  int...: 32 bits = -2.147.483.648 até 2.147.483.647
  long..: 64 bits = -9.223.372.036.854.775.808 até 9.223.372.036.854.775.807
  long valor = 9_223_372_036_854_775_807L; underscore ( _ ) para representar o separador de milhares.
  */
  var id = 2;

  /*
  Operadores aritmeticos:
  + (soma)
  - (subtração)
  / (divisão)
  * (multiplicação)
  % (percentual)
  */

  IO.println("Nome: " + nome);

  if (emPromocao) {
    var porcentagemDesconto = (preco - precoComDesconto) / preco;
    var valorDesconto = preco * porcentagemDesconto;
    IO.println("Porcentagem de desconto: " + porcentagemDesconto);
    IO.println("Preço: de " + preco + " por " + precoComDesconto);
  } else {
    IO.println("Preço: " + preco);
  }

  if (categoria == 1) {
    IO.println("Categoria: Entradas");
  } else if (categoria == 2) {
    IO.println("Categoria: Pratos Principais");
  } else if (categoria == 3) {
    IO.println("Categoria: Sobremesas");
  } else if (categoria == 4) {
    IO.println("Categoria: Bebidas");
  } else {
    IO.println("Categoria não encontrada...");
  }

  IO.println("---------------------------------------------");

  switch (categoria) { 
    case 1:
      IO.println("Categoria: Entradas");
      break;
    case 2:
      IO.println("Categoria: Pratos Principais");
      break;
    case 3:
      IO.println("Categoria: Sobremesas");
      break;
    case 4:
      IO.println("Categoria: Bebidas");
      break;
    default:
      IO.println("Categoria não encontrada...");
  }

}
        