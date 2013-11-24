package Labirinto;

import java.io.*;
import java.util.Scanner;

class Mapa {

private String [][] sala = new String[1000][7];
private String [][] cor = new String[1000][7];
private String [][] trap = new String[1000][7];
private String [][] inimigo = new String[1000][7];
private String [][] item = new String[1000][7];
private String [][] dadosInimigo = new String[1000][7];

// guarda a posi��o do personagem.
private int posicao = 0;
private int nDirecao= 0;

Inventario i = new Inventario();
Item ite = new Item();
Inimigo ini = new Inimigo();
Personagem p = new Personagem();

    // Abre o arquivo e monta a matriz do labirinto
    public void abrir(){
        
        //zera o mapa (salas, armadilhas e inimigos)
        for (int i=0; i<1000; i++){
            for (int j=0; j<7; j++){
                sala[i][j]="N";
                trap[i][j]="N";
                inimigo[i][j]="N";
                cor[i][j]="N";
                item[i][j]="N";
                dadosInimigo[i][j]="N";
            }
        }        

        // garanto a linha zero da matriz das salas para as sa�das.        
        for (int j=0; j<7; j++){
            sala[0][j]="SAIDA";
        }
        
        String comeco=""; // serve para pegar o 'caminhonome' do arquivo
        int n=0;                  // serve para guardar o n�mero da sala em String
        String aux="";           // serve para auxiliar as convers�es de int para String
        String direcao="";       // serve para guardar a dire��o da conex�o
        
        Scanner entra = new Scanner(System.in);
        while(comeco==""){
            System.out.println("Digite o nome e extens�o do arquivo do labirinto:");
            comeco = entra.next();            
        }

        try {
            FileInputStream fis = new FileInputStream(comeco);  
            Scanner     scanner = new Scanner(fis);  
   
            while (scanner.hasNext("room")) {  
                scanner.next();
                // guarda o n�mero da sala
                n = scanner.nextInt();
                item[n][1]=ite.arma();
                item[n][2]=ite.armadura();
                item[n][3]=ite.Chave();
                while (scanner.hasNext("south")||scanner.hasNext("north")||scanner.hasNext("east")||scanner.hasNext("west")||scanner.hasNext("up")||scanner.hasNext("down")){
                    // pula e guarda a pr�xima dire��o
                    direcao = scanner.next();
                    if(direcao.equals("north")){
                        nDirecao=1;
                    }
                    if(direcao.equals("south")){
                        nDirecao=2;
                    }
                    if(direcao.equals("east")){
                        nDirecao=3;
                    }
                    if(direcao.equals("west")){
                        nDirecao=4;
                    }
                    if(direcao.equals("up")){
                        nDirecao=5;
                    }
                    if(direcao.equals("down")){
                        nDirecao=6;
                    }
                    // busco o n�mero da sala de conex�o e guardo na sala:
                    aux = scanner.next();
                     sala[n][nDirecao] = aux;
                     cor[n][nDirecao] = cor();
                    if(scanner.hasNext("TRAP")){
                        // se tiver TRAP, somo ao n�mero da sala de conex�o a String 'TRAP' e guardo na matriz trap:
                        aux = " "+scanner.next();
                        trap[n][nDirecao] = aux;
                    }
                    // crio inimigo para a conex�o
                    inimigo[n][nDirecao]=ini.Tipo();
                    if(!inimigo[n][nDirecao].equals("N")){
                        geraInimigo(inimigo[n][nDirecao], n, nDirecao);
                    }
                    // limpo a aux para pr�ximo uso
                    aux="";                    
                }
                // limpo a n para pr�ximo uso
                n=0;
            }  
        } 
        catch (Exception e) {  
            e.printStackTrace();  
        }
        for (int i=0; i<40; i++) {  
            System.out.println("Sala " + i + " Norte " +sala[i][1] + trap[i][1] + inimigo[i][1] + " Sul " +sala[i][2] + trap[i][2] + inimigo[i][2] + " Leste " +sala[i][3] + trap[i][3] + inimigo[i][3] + " Oeste " +sala[i][4] + trap[i][4] + inimigo[i][4] + " Acima " +sala[i][5] + trap[i][5] + inimigo[i][5] + " Abaixo " +sala[i][6] + trap[i][6] + inimigo[i][6]);  
        } 
    }
    
    // cria a localiza��o incial
    public void LugarInicial(){
        int w=0;
        int k=1;
        while(w==0){
            k = (int) (1+(Math.random() * 1000));
            if(!sala[k][1].equals("N") || !sala[k][2].equals("N") || !sala[k][3].equals("N") || !sala[k][4].equals("N") || !sala[k][5].equals("N") || !sala[k][6].equals("N")){
                w=1;
            }
        }
        
        posicao = k;
        i.criaInventario();
        salaPresa();
        
        descrever();
    }
    
    public void salaPresa(){
        int r=0, n=0;
        String aux="";
        // testo se todas as portas est�o fechadas
        aux = item[posicao][3];
        for(int j=0;j<7;j++){
            // testo se h� chave no ch�o para abrir uma das portas da sala
            if(!cor[posicao][j].equals("N")){
                if(!cor[posicao][j].equals(aux)){
                    r++;
                }
                n++;
            }
        }
        for(int j=0;j<7;j++){
            if(cor[posicao][j].equals("Marrom")){
                r=0;
            }
        }
        if(r==n){
            descrever();
            System.out.println("\n Voc� est� preso nesta sala.");
            System.exit(0);
        }
    }
    
    // Descreve o lugar onde o personagem est�
    public void descrever(){
        String a="";
        if(posicao==0){
            System.out.println("Parab�ns! Voc� conseguiu escapar do Labirinto!");
            System.exit(0);
        }
        
        a += "Voc� est� na Sala " + posicao +"\n";
        
        if(!sala[posicao][1].equals("N")){
            a += "Porta "+cor[posicao][1]+" ao norte. ";
            if(!inimigo[posicao][1].equals("N")){
                a+="H� um "+inimigo[posicao][1]+" bloquando esta porta. ";
            }
            if(!trap[posicao][1].equals("N")){
                a+="H� uma armadilha nesta porta. ";
            }
            a+="\n";
        }
        if(!sala[posicao][2].equals("N")){
            a += "Porta "+cor[posicao][2]+" ao sul. ";
            if(!inimigo[posicao][2].equals("N")){
                a+="H� um "+inimigo[posicao][2]+" bloquando esta porta.";
            }
            if(!trap[posicao][2].equals("N")){
                a+="H� uma armadilha nesta porta. ";
            }
            a+="\n";
        }
        if(!sala[posicao][3].equals("N")){
            a += "Porta "+cor[posicao][3]+" ao leste; ";
            if(!inimigo[posicao][3].equals("N")){
                a+="H� um "+inimigo[posicao][3]+" bloquando esta porta.";
            }
            if(!trap[posicao][3].equals("N")){
                a+="H� uma armadilha nesta porta. ";
            }
            a+="\n";
        }
        if(!sala[posicao][4].equals("N")){
            a += "Porta "+cor[posicao][4]+" a oeste; ";
            if(!inimigo[posicao][4].equals("N")){
                a+="H� um "+inimigo[posicao][4]+" bloquando esta porta.";
            }
            if(!trap[posicao][4].equals("N")){
                a+="H� uma armadilha nesta porta. ";
            }
            a+="\n";
        }
        if(!sala[posicao][5].equals("N")){
            a += "Escada "+cor[posicao][5]+" para Subir; ";
            if(!inimigo[posicao][5].equals("N")){
                a+="H� um "+inimigo[posicao][5]+" bloquando esta Escada.";
            }
            if(!trap[posicao][5].equals("N")){
                a+="H� uma armadilha nesta Escada. ";
            }
            a+="\n";
        }
        if(!sala[posicao][6].equals("N")){
            a += "Escada "+cor[posicao][6]+" para descer; ";
            if(!inimigo[posicao][6].equals("N")){
                a+="H� um "+inimigo[posicao][6]+" bloquando esta Escada.";
            }
            if(!trap[posicao][6].equals("N")){
                a+="H� uma armadilha nesta Escada. ";
            }
            a+="\n";
        }
        
        for(int i=0; i<7; i++){
            if(!item[posicao][i].equals("N")){
                a +="Item: "+item[posicao][i]+"\n";
            }
            
        }
         System.out.println(a);
    }
    
    //move o personagem pelo labirinto
    public void andar(String comando){
        
        String aux="";
                
        if(comando.equals("Norte")){
            nDirecao=1;
        }
        if(comando.equals("Sul")){
            nDirecao=2;
        }
        if(comando.equals("Leste")){
            nDirecao=3;
        }
        if(comando.equals("Oeste")){
            nDirecao=4;
        }
        if(comando.equals("Subir Escada")){
            nDirecao=5;
        }
        if(comando.equals("Descer Escada")){
            nDirecao=6;
        }            
            
        // Testa se h� sala para onde o jogador quer se mover
        if(sala[posicao][nDirecao].equals("N")){
            System.out.println("N�o h� porta para esta dire��o. \n");
        }
        else{
            // testa se h� inimigo na porta e leva para o m�todo de combate
            if (!inimigo[posicao][nDirecao].equals("N")){
                aux=combate(posicao, nDirecao);
            
                if(!aux.equals(inimigo[posicao][nDirecao])){
                    inimigo[posicao][nDirecao]=aux;
                }
            }
            else{
                // testa se h� armadilha na porta e leva para o m�todo de dano da armadilha
                if(trap[posicao][nDirecao].equals("TRAP")){
                    danoArmadilha();
                }                
            }
            
            if((!cor[posicao][nDirecao].equals("D"))||(!cor[posicao][nDirecao].equals("Marrom"))){
                abrirPorta(nDirecao);
            }
            
            if(inimigo[posicao][nDirecao].equals("N")){
                if(cor[posicao][nDirecao].equals("Marrom")){
                    // comando que move o personagem para a pr�xima sala
                    posicao = Integer.parseInt(sala[posicao][nDirecao]);                    
                }
            }
        }
        salaPresa();
        descrever();            
    }
    // Este m�todo teste se h� chave para abrir a porta correspondente.
    public void abrirPorta(int nDirecao){
        // recupera a chave que o personagem carrega
        String aux = i.listaitem();
        // testa se a chave que o personagem carrega � igual � cor da porta
        if(cor[posicao][nDirecao].equals(aux)){
            cor[posicao][nDirecao]="Marrom";
            System.out.println("Voc� abriu esta porta.");
        }
        else{
            if(!cor[posicao][nDirecao].equals("Marrom")){
                System.out.println("Voc� n�o possui a chave para abrir esta porta.");
            }
        }
    }
    
    public String cor(){
        String cor="";
        while (cor.equals("")){
            int a = (int) (1+(Math.random() * 10));
            switch(a){
                case 1:  cor = "Verde"; break;
                case 3:  cor = "Vermelha"; break;
                case 5:  cor = "Amarela"; break;
                case 7:  cor = "Azul"; break;
                default: cor = "Marrom"; break;
            }
        }
        return cor;        
    }
    public void pegarTesouro(String opcao){
        
        String aux = "";
        
        if(opcao.equals("Pegar Arma")){
            if(!item[posicao][1].equals("N")){
                aux=i.listaarma();
                i.getarma(item[posicao][1]);
                item[posicao][1]=aux;
            }
            else{
                System.out.println("N�o h� arma no tesouro");
            }
        }
        if(opcao.equals("Pegar Armadura")){
            if(!item[posicao][2].equals("N")){
                aux=i.listaarmadura();
                i.getarmadura(item[posicao][2]);
                item[posicao][2]=aux;
            }
            else{
                System.out.println("N�o h� armadura no tesouro");
            }
        }
        if(opcao.equals("Pegar Chave")){
            if(!item[posicao][3].equals("N")){
                aux=i.listaitem();
                i.getitem(item[posicao][3]);
                item[posicao][3]=aux;
            }
            else{
                System.out.println("N�o h� Chave");
            }
        }        
        }        
    // Desarma a armadilha.
    public void desarmarArmadilha(){
        
        int a = (int) (1+(Math.random() * 10));
        switch(a){
            case 5: 
                System.out.println("Voc� n�o conseguiu desarmar a armadilha. \n");break;
            default: 
                System.out.println("Voc� conseguiu desarmar a armadilha. \n");
                trap[posicao][nDirecao]="N";break;
        }
    }
    
    //gera o combate entre o personagem e os inimigos
    public String combate(int posicao, int nDirecao){
//        Scanner entra = new Scanner(System.in);
        
        String sa=""+posicao;
        String di=""+nDirecao;
        for(int i=0;i<1000;i++){
            if(dadosInimigo[i][2].equals(sa)){
                if(dadosInimigo[i][3].equals(di)){
                    posicao=i;
                    break;
                }
            }
        }
       
        String aux="";
        aux=i.listaitem();
        int a=0, vida=0;
        
        a = p.dano(i.listaarma());
        aux=dadosInimigo[posicao][4];
        int b = Integer.parseInt(aux);
        vida =  b - a;
        System.out.println("Voc� acertou o "+dadosInimigo[posicao][1]+" com "+a+" pontos de dano. \n");
        dadosInimigo[posicao][4]=""+vida;
        
        if(vida>0){
            a = ini.ataque(dadosInimigo[posicao][1]);
            System.out.println("O "+dadosInimigo[posicao][1]+" acertou voc� com "+a+" pontos de dano. \n");
            a = p.vida(a, i.listaarmadura());
            return dadosInimigo[posicao][1];
        }
        else{
            System.out.println("Voc� matou o "+dadosInimigo[posicao][1]+"\n");
            return "N";
        }
        
    }
    
    public void geraInimigo(String Tipo, int posicao, int nDirecao){
        
        int a=0;
        
        for(int i=0; i<1000; i++){
            if(dadosInimigo[i][1].equals("N")){
                dadosInimigo[i][1] = Tipo;
                dadosInimigo[i][2] = ""+posicao;
                dadosInimigo[i][3] = ""+nDirecao;
                a = ini.vida(Tipo);
                dadosInimigo[i][4] = ""+a;
                break;
            }
        }
    }
    
    public void listaMochila(){
        
        String aux="";
        String a="";
        aux=i.listaarma();
        a+="Arma: "+aux+"\n";
        aux=i.listaarmadura();
        a+="Armadura: "+aux+"\n";
        aux=i.listaitem();
        a+="Itens: "+aux+"\n";
        System.out.println(a);
    }
    
    // GERA O DANO POR PASSAR EM UM ARMADILHA N�O DESARMADA
    public void danoArmadilha(){
        
        System.out.println("Voc� n�o desarmou a armadilha e foi v�tima dela. \n");
        
        // calcula o dano
        int dano = (int) (1+(Math.random() * 10));
        
        // cria o objeto da classe personagem para enviar o dano inflingido pela armadilha
        Personagem p = new Personagem();
        dano = p.vida(dano, i.listaarmadura());
    }
    
}