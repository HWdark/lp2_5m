package Labirinto;

class Item {
    
    public String Chave(){
        String chave="";
        while (chave.equals("")){
            int a = (int) (1+(Math.random() * 8));
            switch(a){
                case 1:  chave = "Azul";     break;
                case 3:  chave = "Amarela";  break;
                case 5:  chave = "Verde";    break;
                case 7:  chave = "Vermelha"; break;
                default: chave = "N";
            }
        }
        return chave;
    }
    public String arma(){
        String arma="";
        while (arma.equals("")){
            int a = (int) (1+(Math.random() * 14));
            switch(a){
                case 1:  arma = "Adaga";      break;
                case 3:  arma = "Faca";       break;
                case 5:  arma = "Espada";     break;
                case 7:  arma = "Adaga";      break;
                case 9:  arma = "Faca";       break;
                case 11: arma = "Adaga";      break;
                default: arma = "N";
            }
        }        
        return arma;
    }    
    
    public String armadura(){
        String armadura="";
        while (armadura.equals("")){
            int a = (int) (1+(Math.random() * 14));
            switch(a){
                case 1:  armadura = "Couro";     break;
                case 3:  armadura = "Metal";     break;
                case 5:  armadura = "Mithrill";  break;
                case 7:  armadura = "Couro";     break;
                case 9:  armadura = "Metal";     break;
                case 11: armadura = "Couro";     break;
                default: armadura = "N";
            }
        }
        return armadura;
    }
    
}