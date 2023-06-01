package com.mycompany.cadastro_de_trecos.crud;

import java.sql.SQLException;
import static com.mycompany.cadastro_de_trecos.Cadastro_de_trecos.clearScreen;
import static com.mycompany.cadastro_de_trecos.Cadastro_de_trecos.exitProgram;
import static com.mycompany.cadastro_de_trecos.Cadastro_de_trecos.mainMenu;
import static com.mycompany.cadastro_de_trecos.Tools.showRes;
import com.mycompany.cadastro_de_trecos.db.DbConnection;
import com.mycompany.cadastro_de_trecos.setup.AppSetup;

public class LockUnlock extends AppSetup {

    public static void lockUnlock() {

        // Reserva recursos para o banco de dados.
        int id = 0;
        String sql;
        String newStatus;

        // Cabeçalho da seção.
        System.out.println(appName + "\n" + appSep);
        System.out.println("Bloqueia/desbloqueia um registro");
        System.out.println(appSep);

        try {

            // Recebe o Id do teclado.
            System.out.print("Digite o ID ou [0] para retornar: ");
            id = Integer.parseInt(scanner.next());
            if (id == 0) {
                clearScreen();
                mainMenu();
            }
        } catch (NumberFormatException e) {

            // Quando opção é inválida.
            clearScreen();
            System.out.println("Oooops! Opção inválida!\n");
            lockUnlock();
        }

        try {

            // Verifica se o registro existe.
            sql = "SELECT * FROM " + DBTABLE + " WHERE id = ? AND " + DBFIELDS[5] + " != '0'";
            conn = DbConnection.dbConnect();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            res = pstm.executeQuery();

            if (res.next()) {

                // Se tem registro, exibe na view.
                showRes(res);

                if (res.getString("status").equals("1")) {
                    System.out.print("Tem certeza que deseja ativar o registro? [s/N] ");
                    newStatus = "2";
                } else {
                    System.out.print("Tem certeza que deseja bloquear o registro? [s/N] ");
                    newStatus = "1";
                }
                if (scanner.next().trim().toLowerCase().equals("s")) {

                    sql = "UPDATE " + DBTABLE + " SET " + DBFIELDS[5] + " = ? WHERE id = ?";
                    System.out.println(id);
                    pstm = conn.prepareStatement(sql);
                    pstm.setString(1, newStatus);
                    pstm.setInt(2, id);

                    if (pstm.executeUpdate() == 1) {
                        // Registro apagado.
                        if (newStatus.equals("2")) {
                            System.out.println("\nRegistro ativado!");
                        } else {
                            System.out.println("\nRegistro bloqueado!");
                        }
                    } else {
                        System.out.println("Oooops! Algo deu errado!");
                    }
                } else {
                    System.out.println("\nNada aconteceu!");
                }

            } else {
                clearScreen();
                System.out.println("Oooops! Não achei nada!\n");
                lockUnlock();
            }

            // Fecha banco de dados.
            DbConnection.dbClose(res, stmt, pstm, conn);

            // Menu inferior da seção.
            System.out.println(appSep);
            System.out.println("Menu:\n\t[1] Menu principal\n\t[2] Bloquear/desbloquear outro\n\t[0] Sair");
            System.out.println(appSep);

            // Recebe opção do teclado.            
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
                    lockUnlock();
                }
                default -> {
                    clearScreen();
                    System.out.println("Oooops! Opção inválida!\n");
                    lockUnlock();
                }
            }

        } catch (SQLException error) {

            // Tratamento de erros.
            System.out.println("Oooops! " + error.getMessage());
            System.exit(0);
        }

    }
}