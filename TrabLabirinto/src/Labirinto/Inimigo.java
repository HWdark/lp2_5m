package Labirinto;

public class Inimigo {
    
    public String Tipo(){
        String inimigo="";
        int a = (int) (1+(Math.random() * 8));
        switch(a){
            case 1: inimigo = "Goblin"; break;
            case 2: inimigo = "Orc"; break;
            case 3: inimigo = "Trol"; break;
            case 4: inimigo = "N"; break;
            case 5: inimigo = "N"; break;
            case 6: inimigo = "N"; break;
            case 7: inimigo = "N"; break;
            case 8: inimigo = "N"; break;
        }
        return inimigo;
    }

    // gera atque do inimigo
    public int vida(String inimigo){
        int a=0;
        if(inimigo.equals("Goblin")){
            a = (int) (2 + (Math.random() * 2));
        }
        if(inimigo.equals("Orc")){
            a = (int) (3 + (Math.random() * 3));
        }
        if(inimigo.equals("Trol")){
            a = (int) (4 + (Math.random() * 4));
        }
        return a;
    }
    
    // Gera ataque do inimigo
    public int ataque(String inimigo){
        int a=0;
        if(inimigo.equals("Goblin")){
            a = (int) (1 + (Math.random() * 2));
        }
        if(inimigo.equals("Orc")){
            a = (int) (2 + (Math.random() * 3));
        }
        if(inimigo.equals("Trol")){
            a = (int) (4 + (Math.random() * 3));
        }
        return a;
    }
}