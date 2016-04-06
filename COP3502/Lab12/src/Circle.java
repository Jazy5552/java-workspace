
public class Circle extends Shape{
	double radius;
	
	Circle(String color, double radius) {
		this.color = color;
		this.radius = radius;
	}
	
	@Override
	public double calculateArea() {
		return (Math.PI*Math.pow(radius, 2));
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder(80);
		sb.append("Color: ");
		sb.append(color);
		sb.append("\nRadius: ");
		sb.append(radius);
		sb.append("\nArea: ");
		sb.append(calculateArea());
		sb.append("\n");
		return sb.toString();
	}
	
}
