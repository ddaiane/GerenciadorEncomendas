package view;

import controle.Comando;
import model.Movimento;
import model.dao.MovimentoDAO;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class InterfacePesquisarMovimentosData implements Comando {
    @Override
    public void executar() {
        boolean teste = true;
        String dataPesquisada = "";
        do {
            try {
                dataPesquisada = leData();
                teste = false;
            } catch (ParseException pe) {
                JOptionPane.showMessageDialog(null, "Data em Formato errado!");
            }
        } while (teste);

        mostraMovimentosPorData(dataPesquisada);
    }

    private String leData() throws ParseException{
        String dataPesquisada = JOptionPane.showInputDialog(null, "Digite a Data: ","dd/MM/aaaa");
        new SimpleDateFormat("dd/MM/yyyy").parse(dataPesquisada);
        return dataPesquisada;
    }

    private void mostraMovimentosPorData(String dataPesquisada) {
        MovimentoDAO dao = new MovimentoDAO();
        List<Movimento> movimentos = dao.pesquisar(dataPesquisada);

        StringBuilder todasMovimentacoes = new StringBuilder();

        if(movimentos.isEmpty()) {
            todasMovimentacoes.append("Não há movimentações registrados para a data: ").append(dataPesquisada);
        } else {
            for(Movimento movimento : movimentos) {
                todasMovimentacoes.append(movimento.toString()).append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, todasMovimentacoes.toString(), "Todos Movimentos", JOptionPane.INFORMATION_MESSAGE);
    }




}
