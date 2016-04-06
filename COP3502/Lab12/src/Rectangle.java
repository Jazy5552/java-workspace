
public class Rectangle extends Shape {
	double height, width;
	
	Rectangle(String color, double height, double width) {
		this.color = color;
		this.height = height;
		this.width = width;
	}
	
	@Override
	public double calculateArea() {
		return height*width;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder(80);
		sb.append("Color: " + color);
		sb.append("\nHeight: " + height);
		sb.append("\nWidth: " + width);
		sb.append("\nArea: " + calculateArea());
		return sb.toString();
	}
}
