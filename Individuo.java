
package IC;


public class Individuo implements Comparable<Individuo>{

    public  int numeroCromossomos;
   public int[] cromossomox;
   public int[] cromossomoy;
    public double fitness;

 public Individuo(int[] indx,int[]indy){
     this.numeroCromossomos = indx.length;
     this.cromossomox = indx;
     this.cromossomoy=indy;
     this.fitness = avalia(cromossomox,cromossomoy);
             }
    
  public double avalia(int[] indx, int[] indy){
      double decimalx = 0;
      double decimaly=0;

		for (int i = 0; i < indx.length; i++){
			decimalx += indx[i] * Math.pow(2, indx.length - i - 1);
                }
                for (int i = 0; i < indy.length; i++){
			decimaly += indy[i] * Math.pow(2, indy.length - i - 1);
                }
                
 double funcao =  (0.5-(Math.pow(Math.sin(Math.sqrt(Math.pow(decimalx,2)+Math.pow(decimaly,2))),2) -0.5)/(Math.pow(1+0.001*(Math.pow(decimalx,2)+Math.pow(decimaly,2)),2)));
 fitness=funcao;
 return fitness;
  }

    @Override
    public int compareTo(Individuo o) {
       if(this.fitness<o.fitness){
           return -1;
       }else if(this.fitness>o.fitness){
           return 1;
           
       }else{
           return 0;
       }
    }
}

    



