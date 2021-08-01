package uwindsor.acc.textprocessing;

public class Node implements Comparable<Node>{
	
	private String fileName;
	private Integer numberOfOccurances;
	
	
	@Override
	public String toString() {
		return " [fileName=" + fileName.substring(0, fileName.length()-4)+".html" + ", numberOfOccurances=" + numberOfOccurances + "]";
	}
	public Node(String fileName, Integer numberOfOccurances) {
		super();
		this.fileName = fileName;
		this.numberOfOccurances = numberOfOccurances;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getNumberOfOccurances() {
		return numberOfOccurances;
	}
	public void setNumberOfOccurances(Integer numberOfOccurances) {
		this.numberOfOccurances = numberOfOccurances;
	}
	@Override
	public int compareTo(Node o) {
		if (this.numberOfOccurances > o.numberOfOccurances)
			return -1; 
		else if(this.numberOfOccurances < o.numberOfOccurances)
			return 1;
		else return 0;
	}
	
	
	
}
