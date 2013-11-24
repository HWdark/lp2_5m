package Labirinto;
 
import java.io.*;

public class Labirinto {
    
    public static void main(String[] args) {
        
        String a=""; 
        String aux="";
        
        BufferedReader entra = new BufferedReader(new InputStreamReader(System.in));  
        Mapa m = new Mapa();
        
        m.abrir();
        m.LugarInicial();
        
        while (!aux.equals("O")||!aux.equals("o")){
            try{
                System.out.println("Digite a ação desejada: \n");
                aux = entra.readLine();
                
                System.out.println(aux);
                
                if(aux.equals("Ajuda")){
                    a+="Norte        - Escolhe passar pela porta norte \n";
                    a+="Sul          - Escolhe passar pela porta sul \n";
                    a+="Leste        - Escolhe passar pela porta leste \n";
                    a+="Oeste        - Escolhe passar pela porta oeste \n";
                    a+="Subir        - Escolhe subir o andar \n";
                    a+="Descer       - Escolhe descer o andar \n";
                    a+="Itens        - Escolhe ver os itens carregados \n";
                    a+="Ajuda        - Escolhe ver os comandos de ajuda \n";
                    a+="Sala         - Descreve a sala onde você está \n";
                    a+="Desarmar Armadilha - Desarma a armadilha encontrada \n";
                    a+="Pegar Arma         - Pega Arma do tesouro \n";
                    a+="Pegar Armadura     - Pega Armadura do tesouro \n";
                    a+="Pegar Chave        - Pega Chave do tesouro \n";
                    a+="Fechar Jogo        - Escolhe fechar o jogo \n";
                }
                
                if(aux.equals("Norte")||aux.equals("Sul")||aux.equals("Leste")||aux.equals("Oeste")||aux.equals("Subir")||aux.equals("Descer")){
                    m.andar(aux);                
                }
                
                if(aux.equals("Itens")){
                    m.listaMochila();
                }                
                if(aux.equals("Sala")){
                    m.descrever();
                }
                if(aux.equals("Desarmar Armadilha")){
                    m.desarmarArmadilha();
                }
                if((aux.equals("Pegar Arma"))||(aux.equals("Pegar Armadura"))||(aux.equals("Pegar Chave"))){
                    m.pegarTesouro(aux);
                }
                if(aux.equals("Fechar Jogo")){
                    aux="O";
                }                                                
                
                System.out.println(a);
                a="";
            }catch (IOException exception) {  
                exception.printStackTrace();  
            }  
        }
    }
}