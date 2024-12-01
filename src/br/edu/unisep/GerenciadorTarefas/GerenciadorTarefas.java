package br.edu.unisep.GerenciadorTarefas;

import javax.swing.*;
import java.util.ArrayList;

public class GerenciadorTarefas {

    private ArrayList<String> tarefas;

    public GerenciadorTarefas() {
        tarefas = new ArrayList<>();
        executar();
    }

    private void executar() {
        boolean continuar = true;

        while (continuar) {
            String menu = "Gerenciador de Tarefas\n\n"
                    + "Escolha uma opção:\n"
                    + "1. Adicionar Tarefa\n"
                    + "2. Marcar Tarefa como Concluída\n"
                    + "3. Remover Tarefa\n"
                    + "4. Exibir Todas as Tarefas\n"
                    + "5. Sair";

            String entrada = JOptionPane.showInputDialog(menu);

            if (entrada == null) { // Usuário fechou ou cancelou o diálogo
                continuar = confirmarSaida();
                continue;
            }

            switch (entrada) {
                case "1":
                    adicionarTarefa();
                    break;
                case "2":
                    marcarTarefaConcluida();
                    break;
                case "3":
                    removerTarefa();
                    break;
                case "4":
                    exibirTodasTarefas();
                    break;
                case "5":
                    continuar = confirmarSaida();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida! Por favor, tente novamente.");
            }
        }

        JOptionPane.showMessageDialog(null, "Programa encerrado. Até logo!");
    }

    private boolean confirmarSaida() {
        int escolha = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
        return escolha != JOptionPane.YES_OPTION;
    }

    private void adicionarTarefa() {
        String tarefa = JOptionPane.showInputDialog("Digite a nova tarefa:");
        if (tarefa != null && !tarefa.trim().isEmpty()) {
            tarefas.add(tarefa);
            JOptionPane.showMessageDialog(null, "Tarefa adicionada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Tarefa inválida! Por favor, insira um texto.");
        }
    }

    private void marcarTarefaConcluida() {
        if (tarefas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma tarefa disponível para marcar como concluída.");
            return;
        }

        String listaTarefas = obterListaTarefas();
        String entrada = JOptionPane.showInputDialog("Tarefas:\n" + listaTarefas + "\nEscolha o número da tarefa a marcar como concluída:");
        try {
            int indice = Integer.parseInt(entrada) - 1;
            if (indice >= 0 && indice < tarefas.size()) {
                String tarefa = tarefas.get(indice);
                if (!tarefa.contains("(Concluída)")) {
                    tarefas.set(indice, tarefa + " (Concluída)");
                    JOptionPane.showMessageDialog(null, "Tarefa marcada como concluída!");
                } else {
                    JOptionPane.showMessageDialog(null, "Esta tarefa já está concluída!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Número de tarefa inválido!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida! Por favor, insira um número.");
        }
    }

    private void removerTarefa() {
        if (tarefas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma tarefa disponível para remover.");
            return;
        }

        String listaTarefas = obterListaTarefas();
        String entrada = JOptionPane.showInputDialog("Tarefas:\n" + listaTarefas + "\nEscolha o número da tarefa a remover:");
        try {
            int indice = Integer.parseInt(entrada) - 1;
            if (indice >= 0 && indice < tarefas.size()) {
                tarefas.remove(indice);
                JOptionPane.showMessageDialog(null, "Tarefa removida com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Número de tarefa inválido!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida! Por favor, insira um número.");
        }
    }

    private void exibirTodasTarefas() {
        if (tarefas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma tarefa cadastrada.");
        } else {
            StringBuilder listaTarefas = new StringBuilder("Tarefas:\n");
            for (int i = 0; i < tarefas.size(); i++) {
                listaTarefas.append(i + 1).append(". ").append(tarefas.get(i)).append("\n");
            }
            JOptionPane.showMessageDialog(null, listaTarefas.toString());
        }
    }

    private String obterListaTarefas() {
        StringBuilder listaTarefas = new StringBuilder();
        for (int i = 0; i < tarefas.size(); i++) {
            listaTarefas.append(i + 1).append(". ").append(tarefas.get(i)).append("\n");
        }
        return listaTarefas.toString();
    }

    public static void main(String[] args) {
        new GerenciadorTarefas();
    }
}

