package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLDatabase implements Database {

    @Override
    public List<ItemCardapio> listaDeItensCardapio() {
        List<ItemCardapio> itens = new ArrayList<>();

        String sql = "SELECT id, nome, descricao, categoria, preco, preco_promocional FROM item_cardapio";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cardapio", "root", "senha123");
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                String categoriaStr = rs.getString("categoria");
                BigDecimal preco = rs.getBigDecimal("preco");
                BigDecimal precoPromocional = rs.getBigDecimal("preco_promocional");

                ItemCardapio.CategoriaCardapio categoria = ItemCardapio.CategoriaCardapio.valueOf(categoriaStr);

                var itemCardapio = new ItemCardapio(id, nome, descricao, categoria, preco, precoPromocional);
                itens.add(itemCardapio);
            }

            return itens;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int totalItensCardapio() {
        String sql = "SELECT COUNT(*) FROM item_cardapio;";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cardapio", "root", "senha123");
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {

            int total = 0;
            if (rs.next()) {
                total = rs.getInt(1);
            }

            return total;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void adicionaItemCardapio(ItemCardapio itemCardapio) {
        String sql = "INSERT INTO item_cardapio (nome, descricao, categoria, preco, preco_promocional) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cardapio", "root", "senha123");
             PreparedStatement ps = connection.prepareStatement(sql);) {

            ps.setString(1, itemCardapio.nome());
            ps.setString(2, itemCardapio.descricao());
            ps.setString(3, itemCardapio.categoria().name());
            ps.setBigDecimal(4, itemCardapio.preco());
            ps.setBigDecimal(5, itemCardapio.precoPromocional());

            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ItemCardapio> itemCardapioPorId(Long id) {
        Optional<ItemCardapio> item = Optional.empty();

        String sql = "SELECT id, nome, descricao, categoria, preco, preco_promocional FROM item_cardapio WHERE id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cardapio", "root", "senha123");
             PreparedStatement ps = connection.prepareStatement(sql);) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Long idItem = rs.getLong("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                String categoriaStr = rs.getString("categoria");
                BigDecimal preco = rs.getBigDecimal("preco");
                BigDecimal precoPromocional = rs.getBigDecimal("preco_promocional");

                ItemCardapio.CategoriaCardapio categoria = ItemCardapio.CategoriaCardapio.valueOf(categoriaStr);

                return Optional.of(new ItemCardapio(idItem, nome, descricao, categoria, preco, precoPromocional));
            }

            return item;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeItemCardapio(Long itemId) {
        String sql = "DELETE FROM item_cardapio WHERE id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cardapio", "root", "senha123");
             PreparedStatement ps = connection.prepareStatement(sql);) {

            ps.setLong(1, itemId);
            ps.execute();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean alterarPrecoItemCardapio(Long itemId, BigDecimal novoPreco) {
        String sql = "UPDATE item_cardapio SET preco = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cardapio", "root", "senha123");
             PreparedStatement ps = connection.prepareStatement(sql);) {

            ps.setBigDecimal(1, novoPreco);
            ps.setLong(2, itemId);
            ps.execute();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long maximoId() {
        String sql = "SELECT MAX(id) FROM item_cardapio";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cardapio", "root", "senha123");
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {

            Long maxId = 0L;
            if (rs.next()) {
                maxId = rs.getLong(1);
            }

            return maxId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
