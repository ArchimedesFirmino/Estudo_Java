package com.mycompany.cadastro_de_trecos.crud;

import static com.mycompany.cadastro_de_trecos.Cadastro_de_trecos.clearScreen;
import static com.mycompany.cadastro_de_trecos.Cadastro_de_trecos.exitProgram;
import static com.mycompany.cadastro_de_trecos.Cadastro_de_trecos.mainMenu;
import com.mycompany.cadastro_de_trecos.db.DbConnection;
import com.mycompany.cadastro_de_trecos.setup.AppSetup;
import java.sql.SQLException;

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

            // Recebe o Id do teclado.
            System.out.print("Digite sua busca: ");
            searchString = scanner.next().trim();
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
            // sql = "SELECT * FROM " + DBTABLE + " WHERE name LIKE '%" + searchString + "%' OR description LIKE '%" + searchString + "%'";
            sql = "SELECT * FROM " + DBTABLE + " WHERE " + DBFIELDS[1] + " LIKE ? OR " + DBFIELDS[2] + " LIKE ? OR " + DBFIELDS[3] + " LIKE ? OR " + DBFIELDS[4] + " LIKE ? AND " + DBFIELDS[5] + " != '0'";
            System.out.println(sql);
            conn = DbConnection.dbConnect();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + searchString + "%");
            pstm.setString(2, "%" + searchString + "%");
            pstm.setString(3, "%" + searchString + "%");
            pstm.setString(4, "%" + searchString + "%");
            res = pstm.executeQuery();

            System.out.println(" ");
            if (res.next()) {

                // Se encontrou registros.
                do {

                    // Exibe registro na view.
                    System.out.println(
                            "ID: " + res.getString(DBFIELDS[0]) + "\n"
                            + "  Nome: " + res.getString(DBFIELDS[2]) + "\n"
                            + "  Descrição: " + res.getString(DBFIELDS[3]) + "\n"
                            + "  Localização: " + res.getString(DBFIELDS[4]) + "\n"
                            + "  Data de Cadastro: " + res.getString(DBFIELDS[1]) + "\n"
                    );
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
