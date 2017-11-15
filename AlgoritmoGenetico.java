/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IC;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Estágio
 */
public class AlgoritmoGenetico {
    
    Random rn = new Random();
    public int numeroCromossomosx =5;
    public int numeroCromossomosy = numeroCromossomosx;
    public int maximoGeracoes = 100;
    public int tamanhoPopulacao = 100;
    public float taxaCruzamento = 0.65f;
    public float taxaMutacao = 0.008f;
    
    public ArrayList<Individuo> populacao = new ArrayList<>();
    
    public static void main(String[] args) {
         AlgoritmoGenetico ag = new AlgoritmoGenetico();
         int geracao=0;
         ag.inicializapopulacao();
         while(geracao<ag.maximoGeracoes){
              ag.selecao();
              ag.cruzamento();
              ag.mutacao();
              geracao++;
              Collections.sort(ag.populacao);
         }
         System.out.print("Melhor Cromossomo X para minimizar a funcao: ");
        for(int i=0;i<ag.numeroCromossomosx;i++){
            System.out.print(ag.populacao.get(0).cromossomox[i]);       
        }
        System.out.println("\n");
        System.out.print("Melhor cromossomo Y para minimizar a funcao: ");
         for(int k=0;k<ag.numeroCromossomosy;k++){
             System.out.print(ag.populacao.get(0).cromossomoy[k]);
         }
         System.out.println("");
    }
    
    
    public void inicializapopulacao(){
        for(int i=0;i<tamanhoPopulacao;i++){
       int[] crx = new int[numeroCromossomosx];
       int[] cry = new int[numeroCromossomosy];
        for(int j=0;j<numeroCromossomosx;j++){
           int rd = rn.nextInt(500);

            crx[j]=rd%2;
           
          
        }
       for(int k=0;k<numeroCromossomosx;k++){
            cry[k]=rn.nextInt(500)%2;
           
      
}
        populacao.add(new Individuo(crx,cry));
       
            
    }
    }
    
    public void selecao(){
     Collections.sort(populacao);
     ArrayList<Individuo> copia = new ArrayList<>();
     int tor = torneio();
     for(int i=this.tamanhoPopulacao-1;i>=tor;i--){
         copia.add(populacao.get(i));
     }
     populacao.clear();
     for(int i=0;i<copia.size();i++){
         populacao.add(copia.get(i));
         
     }
     this.tamanhoPopulacao=this.tamanhoPopulacao-tor;
     copia.clear();
    }
 public int torneio(){
ArrayList list = new ArrayList();
 int n1 =rn.nextInt(this.tamanhoPopulacao);
  int n2=rn.nextInt(this.tamanhoPopulacao);
    
 
   list.add(n1)  ;
   list.add(n2);

 int min=(int)java.util.Collections.min(list);


return min;  
    }
 
 public void mutacao(){
     int qtdeMutada =(int) this.taxaMutacao*this.tamanhoPopulacao;
     for(int i=0;i<qtdeMutada;i++){
         int posicaoMutacao = rn.nextInt(this.numeroCromossomosx);
         int indRandom = rn.nextInt(this.tamanhoPopulacao);
         
         if(populacao.get(indRandom).cromossomox[posicaoMutacao]==0){
             populacao.get(indRandom).cromossomox[posicaoMutacao]=1;
         }else{
              populacao.get(indRandom).cromossomox[posicaoMutacao]=0;
         }
         if(populacao.get(indRandom).cromossomoy[posicaoMutacao]==0){
             populacao.get(indRandom).cromossomoy[posicaoMutacao]=1;
         }else{
              populacao.get(indRandom).cromossomoy[posicaoMutacao]=0;
         }
     }
     
 }
    public void cruzamento(){
        int tamanhopar = (int) (this.tamanhoPopulacao / 2 * this.taxaCruzamento);
        ArrayList<Individuo> filhos = new ArrayList<>();
        int contagem=0;
        while(contagem<tamanhopar){
        int pontodecorte = (rn.nextInt(this.numeroCromossomosx)-2)+1;
        int[] filhoax = new int[this.numeroCromossomosx];
        int[] filhoay = new int[this.numeroCromossomosy];
        int[] filhobx = new int[this.numeroCromossomosx];
        int[] filhoby = new int[this.numeroCromossomosy];
        Individuo pai1 = populacao.get(rn.nextInt(this.tamanhoPopulacao));
        Individuo pai2 = populacao.get(rn.nextInt(this.tamanhoPopulacao)); 
        for (int i = 0; i < this.numeroCromossomosx; i++) {

			if (i <= pontodecorte) {
				filhoax[i] = pai1.cromossomox[i];
				filhoay[i] = pai1.cromossomoy[i];
                                filhobx[i]= pai2.cromossomox[i];
                                filhoby[i] = pai2.cromossomoy[i];
			} else {
				filhoax[i] = pai2.cromossomox[i];
				filhoay[i] = pai2.cromossomoy[i];
                                filhobx[i]= pai1.cromossomox[i];
                                filhoby[i] = pai1.cromossomoy[i];
			}

		}
        filhos.add(new Individuo(filhoax, filhoay));
        filhos.add(new Individuo(filhobx, filhoby));
           
        contagem++;
        }
     
       this.tamanhoPopulacao=this.tamanhoPopulacao+filhos.size();
       for(int i=0;i<filhos.size();i++){
           populacao.add(filhos.get(i));
       }
        Collections.sort(populacao);
        
    }
    
}

