package view;

import model.Categoria;
import model.Item;
import model.Produto;
import model.Venda;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Venda venda = new Venda();

        System.out.println("=== Sistema de Vendas ===");
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n1. Adicionar item");
            System.out.println("2. Remover item");
            System.out.println("3. Exibir total da venda");
            System.out.println("4. Finalizar venda");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1: 
                    adicionarItem(scanner, venda);
                    break;
                case 2: 
                    removerItem(scanner, venda);
                    break;
                case 3: 
                    System.out.println("Total da venda: R$ " + venda.getTotal());
                    break;
                case 4: 
                    finalizarVenda(venda);
                    continuar = false;
                    break;
                case 5: 
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        System.out.println("Sistema encerrado.");
        scanner.close();
    }

    private static void adicionarItem(Scanner scanner, Venda venda) {
        System.out.println("\n=== Adicionar Item ===");

        System.out.print("Código do item: ");
        int codigoItem = scanner.nextInt();

        System.out.print("Nome do produto: ");
        scanner.nextLine(); // Consumir o \n
        String nomeProduto = scanner.nextLine();

        System.out.print("Preço do produto: ");
        double precoProduto = scanner.nextDouble();

        System.out.println("Categoria do produto (1: ALIMENTICIO, 2: HIGIENE, 3: LIMPEZA, 4: HORTIFRUTI, 5: PADARIA): ");
        int categoriaIndex = scanner.nextInt();
        Categoria categoria = Categoria.values()[categoriaIndex - 1];

        System.out.print("Quantidade do produto: ");
        double quantidade = scanner.nextDouble();

        Produto produto = new Produto(codigoItem, nomeProduto, precoProduto, categoria);
        Item item = new Item(codigoItem, produto, quantidade);

        venda.adicionarItem(item);
        System.out.println("Item adicionado com sucesso!");
    }

    private static void removerItem(Scanner scanner, Venda venda) {
        System.out.println("\n=== Remover Item ===");
        Item[] itens = venda.getItens();

        if (itens.length == 0) {
            System.out.println("Não há itens para remover.");
            return;
        }

        System.out.println("Itens na venda:");
        for (int i = 0; i < itens.length; i++) {
            System.out.printf("%d. %s (Quantidade: %.2f, Subtotal: R$ %.2f)%n", 
                    i + 1, 
                    itens[i].getProduto().getDescricao(), 
                    itens[i].getQuantidade(), 
                    itens[i].getSubtotal());
        }

        System.out.print("Escolha o número do item para remover: ");
        int itemIndex = scanner.nextInt() - 1;

        if (itemIndex >= 0 && itemIndex < itens.length) {
            venda.removerItem(itemIndex);
            System.out.println("Item removido com sucesso!");
        } else {
            System.out.println("Número inválido.");
        }
    }

    private static void finalizarVenda(Venda venda) {
        System.out.println("\n=== Finalizar Venda ===");
        System.out.println("Itens vendidos:");
        for (Item item : venda.getItens()) {
            System.out.printf("- %s (Quantidade: %.2f, Subtotal: R$ %.2f)%n", 
                    item.getProduto().getDescricao(), 
                    item.getQuantidade(), 
                    item.getSubtotal());
        }
        System.out.println("Total da venda: R$ " + venda.getTotal());
    }
}
