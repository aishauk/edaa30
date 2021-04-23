package mountain;

import java.util.HashMap;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {
	
	private Point a, b, c;
	private double dev;
	TurtleGraphics turtle;
	HashMap<Side, Point> hash;
	
	public Mountain(Point a, Point b, Point c, double dev) {
		super();
		this.a=a;
		this.b=b;
		this.c=c;
		this.dev=dev;
		hash = new HashMap<Side, Point>();
		
	}

	@Override
	public String getTitle() {
		return "Mountain triangle";
	
	}

	@Override
	public void draw(TurtleGraphics g) {
		hash.clear();
		fractalTriangle(g, order, a, b, c, dev);
	}
	
	//skapar metod för att beräkna mittpunkten mellan två  punkter
	private Point middlePoint(Point one, Point two, double dev) {
		Side side = new Side(one, two);
		if (hash.containsKey(side)) {
			return hash.get(side);
		}
		
		
			Point mitt=new Point((one.getX()+two.getX())/2, (int)((one.getY()+two.getY())/2+RandomUtilities.randFunc(dev)));
			hash.put(side, mitt);
			return mitt;
		
		
				
	}
	
	
		
	
	private void fractalTriangle(TurtleGraphics turtle, int order, Point a1, Point b1, Point c1, double dev) {
		this.turtle=turtle;
		if (order == 0) {								
			turtle.moveTo(a1.getX(), a1.getY());		//hämtar metoder från point
			turtle.penDown();							//sätt ner pennan
			turtle.forwardTo(b1.getX(), b1.getY());
			turtle.forwardTo(c1.getX(), c1.getY());
			turtle.forwardTo(a1.getX(), a1.getY());
			turtle.penUp();
		} else {
			
			Point midAB = this.middlePoint(a1, b1, dev);
			Point midBC = this.middlePoint(b1, c1, dev);
			Point midAC = this.middlePoint(a1, c1, dev);

			
			fractalTriangle(turtle, order-1, a1, midAB, midAC, (dev/2));
			fractalTriangle(turtle, order-1, midAB, b1, midBC, (dev/2));
			fractalTriangle(turtle, order-1, midBC, midAB, midAC, (dev/2));
			fractalTriangle(turtle, order-1, midAC, midBC, c1, (dev/2));
		}
	}
	
	
	
	
}
