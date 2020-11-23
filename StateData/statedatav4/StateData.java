package statedatav4;

public class StateData {
	public String name;
	public String capital;
	public double area;
	public int year;
	public int order;
	
	public StateData(String sn, String sc, double sa, int sy, int so) {
		this.name = sn;
		this.capital = sc;
		this.area = sa;
		this.year = sy;
		this.order = so;
	}

	public String toString() {
		return String.format("%20s%20s", this.capital, this.name);
	}
	
}