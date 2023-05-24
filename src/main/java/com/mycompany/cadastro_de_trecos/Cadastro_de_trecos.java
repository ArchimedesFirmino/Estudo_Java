package com.mycompany.cadastro_de_trecos;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class Cadastro_de_trecos {
    
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        clearScreen();
        mainMenu();
    }
       
    public static void mainMenu(){
            System.out.println("Cadastro de Trecos:");
            System.out.println("");
            System.out.println("Menu:");
            System.out.println("\t[1] Listar todos");
            System.out.println("\t[2] Listar");
            System.out.println("\t[3] Novo");
            System.out.println("\t[4] Editar");
            System.out.println("\t[5] Apagar");
            System.out.println("\t[0] Sair"); 
            System.out.print("Opção:");
            String option = scanner.next();
            
            switch(option) {
                case "1":
                    listAll();
                    break;
                case "2":
                    listOne();
                    break;
                case "3":
                    newThing();
                    break;
                case "4":
                    editThing();
                    break;
                case "5":
                    deleteThing();
                    break;
                case "0":
                    clearScreen();
                    exitProgram();
                    break;
                default:
                    reloadMenu();
            }        
    }   
   
    // Encerra o programa.
    public static void exitProgram(){
    scanner.close();
    System.exit(0);
    }
    
    // Lista todos os trecos cadastrados.
    public static void listAll(){
        
        
    }
    // Lista um treco específico pelo id.
    public static void listOne(){
    
    
    }
    // Cria um novo treco.
    public static void newThing(){
    
    
    }
    // Edita um treco pelo id.
    public static void editThing(){
        
        
    }
    // Apaga um treco pelo id.
    public static void deleteThing(){
        
        
    }
    // Recarrega o menu principal.
    public static void reloadMenu(){
        System.out.println("Opção inválida.");
        mainMenu();
    }
    
    public static void clearScreen(){
        for (int i = 0; i < 100; i++) {
            System.out.println("\n");
        }
    }   
        
}
