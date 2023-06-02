package com.mycompany.cadastro_de_trecos.crud;

import static com.mycompany.cadastro_de_trecos.Cadastro_de_trecos.clearScreen;
import static com.mycompany.cadastro_de_trecos.Cadastro_de_trecos.exitProgram;
import static com.mycompany.cadastro_de_trecos.Cadastro_de_trecos.mainMenu;
import static com.mycompany.cadastro_de_trecos.Tools.showRes;
import com.mycompany.cadastro_de_trecos.db.DbConnection;
import com.mycompany.cadastro_de_trecos.setup.AppSetup;
import java.sql.SQLException;
import java.util.Scanner;

public class Search extends AppSetup {
// Lista todos os registros.

    public static void search() {

        String searchString = "";
        String sql = "";
        // Cabeçalho da view.
        System.out.println(appName + "\n" + appSep);
        System.out.println("Lista todos os registros");
        System.out.println(appSep);

        try {

            // Recebe a palavra-chave do teclado.
            Scanner kbd = new Scanner(System.in);
            System.out.print("Digite sua busca: ");
            searchString = kbd.nextLine().trim();
            if (searchString.equals("")) {
                clearScreen();
                mainMenu();
            }
        } catch (NumberFormatException e) {

            // Quando opção é inválida.
            clearScreen();
            System.out.println("Oooops! Opção inválida!\n");
            search();
        }

        try {

            // Consulta o banco de dados.
            sql = "SELECT * FROM trecos WHERE status != 0 AND (nome LIKE ? OR descricao LIKE ? OR localizacao LIKE ?)";
            conn = DbConnection.dbConnect();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + searchString + "%");
            pstm.setString(2, "%" + searchString + "%");
            pstm.setString(3, "%" + searchString + "%");
            res = pstm.executeQuery();

            System.out.println(" ");
            if (res.next()) {

                // Se encontrou registros.
                do {

                    // Exibe registro na view.
                    showRes(res);
                } while (res.next());
            } else {

                // Se não encontrou registro.
                clearScreen();
                System.out.println("Oooops! Não achei nada!\n");
            }

            // Fecha recursos.
            DbConnection.dbClose(res, stmt, pstm, conn);

            // Menu inferior da seção.
            System.out.println(appSep);
            System.out.println("Menu:\n\t[1] Menu principal\n\t[2] Realizar outra pesquisa\n\t[0] Sair");
            System.out.println(appSep);

            // Recebe opção do teclaso.
            System.out.print("Opção: ");
            String option = scanner.next();

            // Executa conforme a opção.
            switch (option) {
                case "0" ->
                    exitProgram();
                case "1" -> {
                    clearScreen();
                    mainMenu();
                }
                case "2" -> {
                    clearScreen();
                    search();
                }
                default -> {
                    clearScreen();
                    System.out.println("Oooops! Opção inválida!\n");
                    search();
                }
            }

        } catch (SQLException error) {

            // Tratamento de erros.
            System.out.println("Oooops! " + error.getMessage());
            System.exit(0);
        }

    }
}
