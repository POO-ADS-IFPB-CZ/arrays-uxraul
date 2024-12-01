package model;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Venda {

    private Item[] itens;          
    private LocalDateTime dataHora; 
    private int quantidadeItens;   

    // Construtor da classe Venda
    public Venda() {
        itens = new Item[3];        
        dataHora = LocalDateTime.now(); 
        quantidadeItens = 0;        
    }

   
    public double getTotal() {
        double total = 0;
        for (int i = 0; i < quantidadeItens; i++) {
            total += itens[i].getSubtotal();
        }
        return total;
    }


    public boolean adicionarItem(Item item) {
        if (quantidadeItens == itens.length) {
            itens = Arrays.copyOf(itens, itens.length + 3); 
        }
        itens[quantidadeItens++] = item;
        return true;
    }


    public boolean removerItem(int index) {
        if (index < 0 || index >= quantidadeItens) {
            return false; 
        }

        for (int i = index; i < quantidadeItens - 1; i++) {
            itens[i] = itens[i + 1]; 
        }

        itens[--quantidadeItens] = null; 
        return true;
    }


    public Item[] getItens() {
        return Arrays.copyOf(itens, quantidadeItens);
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
