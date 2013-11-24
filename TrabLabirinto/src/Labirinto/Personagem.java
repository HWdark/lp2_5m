package Labirinto;

class Personagem {
    
    public String inventario="";
    public int vida=10, dano=3;
    
    public int vida(int dano, String Armadura){
        String a="";
        
        if(dano>0){
            
            a = Armadura;
            
            if(a.equals("Couro")){
                dano = dano - 1;
                System.out.println("Sua armadura de Couro absorveu 1 de dano \n");
            }
            if(a.equals("Metal")){
                dano = dano - 2;
                System.out.println("Sua armadura de Metal absorveu 2 de dano \n");
            }
            if(a.equals("Mithrill")){
                dano = dano - 3;
                System.out.println("Sua armadura de Mithrill absorveu 3 de dano \n");
            }                        
            vida = vida - dano;
        }
        
        System.out.println("Vida: "+vida);
        
        if(vida <= 0){
            System.out.println("Voce morreu");
            System.exit(0);
        }
        
        return vida;
    }

    public int dano(String arma){
        
        int dano=2;
        if(arma.equals("Adaga")){
            dano=1;
        }
        if(arma.equals("Faca")){
            dano=2;
        }
        if(arma.equals("Espada")){
            dano=4;
        }

        dano = (int) (Math.random() * dano );

        return dano;
    }
}