package com.mycompany.cadastro_de_trecos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Tools {

    public static void showRes(ResultSet res) {
        try {
            //Conversão da data  
            String sepDH[] = res.getString("data").split(" ");
            String sepD[] = sepDH[0].split("-"); 
            String novoDH = sepD[2] + "/" + sepD[1] + "/" + sepD[0] + " às " + sepDH[1];
            String nStatus = null;
            if(res.getString("status").equals("1")){nStatus="BLOQUEADO";}
            if(res.getString("status").equals("2")){nStatus="ATIVO";}
                                                          
            System.out.println(
                    "ID: " + res.getString("id") + "\n"
                    + "  data: " + novoDH + "\n"
                    + "  nome: " + res.getString("nome") + "\n"
                    + "  descrição: " + res.getString("descricao") + "\n"
                    + "  localização: " + res.getString("localizacao") + "\n"
                    + "  status: " + nStatus + "\n"
            );
                    
        } catch (SQLException error) {

            // Tratamento de erros.
            System.out.println("Oooops! " + error.getMessage());
            System.exit(0);
        }

    }

}