package cromosoma;
import java.util.Collections;
import java.util.Vector;


public class Cromosoma implements Comparable<Object> {

	private Vector<Integer> genes;
	private Double fitness; 
	
	public Cromosoma(Vector<Integer> genes) {
		super();
		this.genes = genes;
		this.fitness = 0.0;
	}
	
	public Cromosoma(Integer N){
		super();
		this.genes = new Vector<Integer>();
		for (int i = 1; i <= N ; i++){
			this.genes.add(i);
		}
		Collections.shuffle(this.genes);
		this.fitness = 0.0;
	}
	
	public Integer getSize(){
		return this.genes.size();
	}
	
	public Integer getGen(Integer pos){
		return this.genes.elementAt(pos);
	}
	
	public void setGen(Integer pos,Integer alelo){
		 this.genes.set(pos, alelo);
	}

	public Vector<Integer> getGenes() {
		return genes;
	}

	public void setGenes(Vector<Integer> genes) {
		this.genes = genes;
	}
	
	public void setFitness(Double valor){
		this.fitness = valor;
	}
	
	public Double getFitness(){
		return this.fitness;
	}

	@Override
	public int compareTo(Object arg0) {
		Cromosoma aComparar = (Cromosoma) arg0;
		return getFitness().compareTo(aComparar.getFitness());
	}
	
	public String toString(){
		return this.genes.toString();
	}
	
	public boolean equals(Object obj){
		if ( obj instanceof Cromosoma ){
			Cromosoma c = (Cromosoma) obj;
			for (int i = 0; i < genes.size() ; i++){
				if (! genes.elementAt(i).equals(c.getGenes().elementAt(i))){
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
