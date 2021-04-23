package mountain;

public class Side {
	
	Point p1, p2;		//skapar två punkter som utgör sidornas ändpunkter
	
	public Side(Point p1, Point p2) {
		this.p1=p1;
		this.p2=p2;
	}
	
	public Point getP1() {
		return p1;
	}

	public Point getP2() {
		return p2;
	}
	
	//kollar om sidorna är samma
	public boolean equals(Object x) {		//java förväntar sig alltid ett object i equalsmetoder
		Side s = (Side) x;
		if (x instanceof Side) {
			return((s.getP1().equals(this.p1)&&s.getP2().equals(this.p2))
					||(s.getP2().equals(this.p1)&&s.getP1().equals(this.p2)));
			
		}
		
		
		return false;
		
	}
	
	@Override
	public int hashCode() {
	return p1.hashCode() + p2.hashCode();
	}
	
}