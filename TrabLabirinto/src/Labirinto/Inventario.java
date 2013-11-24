package Labirinto;

import java.util.Scanner;

public class Inventario {
    
    public String inventario[][]=new String[2][4];
    // 1 - arma;
    // 2 - tesouro;
    // 3 - armadura;

    public void criaInventario(){
        inventario[1][1]="Adaga";
        inventario[1][2]="Chave";
        inventario[1][3]="";
    }

    public String listaarma(){
        String aux =inventario[1][1]; 
        return aux;
    }
    public String listaitem(){
        String aux =inventario[1][2];
        return aux;
    }
    public String listaarmadura(){
        String aux =inventario[1][3];
        return aux;
    }

    public String getarma(String a){
        Scanner entra = new Scanner(System.in);
        String aux ="";
        if (inventario[1][1].equals("")){
            inventario[1][1] = a;
        }
        else{
            aux +="Voce esta carregando "+inventario[1][1]+"\n";
            aux +="Deseja trocar por "+a+"? \n";
            aux +="Digite: S -> Sim; N-> Nao \n";
            System.out.println(aux);
            aux = entra.next();
            if(aux.equals("S")){
                inventario[1][1] = a;
            }
        }
        return inventario[1][1];
    }
    public String getitem(String a){
        Scanner entra = new Scanner(System.in);
        String aux ="";
        if (inventario[1][2].equals("")){
            inventario[1][2] = a;
        }
        else{
            aux +="Voce esta carregando "+inventario[1][2]+"\n";
            aux +="Deseja trocar por "+a+"? \n";
            aux +="Digite: S -> Sim; N-> Nao \n";
            System.out.println(aux);
            aux = entra.next();
            if(aux.equals("S")){
                inventario[1][2] = a;
            }
        }
        return inventario[1][2];
    }
    public String getarmadura(String a){
        Scanner entra = new Scanner(System.in);
        String aux ="";
        if (inventario[1][3].equals("")){
            inventario[1][3] = a;
        }
        else{
            aux +="Voce esta carregando "+inventario[1][3]+"\n";
            aux +="Deseja trocar por "+a+"? \n";
            aux +="Digite: S -> Sim; N-> Nao \n";
            System.out.println(aux);
            aux = entra.next();
            if(aux.equals("S")){
                inventario[1][3] = a;
            }
        }
        return inventario[1][3];
    }
}