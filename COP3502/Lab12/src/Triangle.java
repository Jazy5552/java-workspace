
public class Triangle extends Shape {
	double base;
	double height;
	
	Triangle(String color, double height, double base) {
		this.color = color;
		this.height = height;
		this.base = base;
	}
	
	@Override
	public double calculateArea() {
		return height*base/2;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder(80);
		sb.append("Color: " + color);
		sb.append("\nHeight: " + height);
		sb.append("\nBase: " + base);
		sb.append("\nArea: " + calculateArea());
		return sb.toString();
	}
}
