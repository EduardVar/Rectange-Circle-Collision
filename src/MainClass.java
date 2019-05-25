public class MainClass {
	
	static int[] origin = {50, 300};
	
	static float currentAngle = -1f;
	
	static int laserW = 10;
	static int laserH = 600;
	
	static int[] A = {0, 0}, B = {0, 0}, C= {0, 0}, D = {0, 0};
	
	static int[] circleOrigin = {20, 250};
	static int circleRad = 5;

	public static void main(String[] args)
	{
		updateVertices();
		
		System.out.println(didItIntersect());
	}
	
	public static void updateVertices()
	{
		float tempAngle = 180f - currentAngle;
		int hypotenuse = laserW / 2;
		
		int xInitial = (int)(Math.cos(Math.toRadians(tempAngle)) * hypotenuse);
		int yInitial = (int)(Math.sin(Math.toRadians(tempAngle)) * hypotenuse);
		
		D[0] = origin[0] + xInitial;
		D[1] = origin[1] - yInitial;
		
		C[0] = origin[0] - xInitial;
		C[1] = origin[1] + yInitial;
		
		int xPrime = (int)(Math.sin(Math.toRadians(currentAngle)) * laserH);
		int yPrime = (int)(Math.cos(Math.toRadians(currentAngle)) * laserH);
		
		A[0] = D[0] + xPrime;
		A[1] = D[1] - yPrime;
		
		B[0] = C[0] + xPrime;
		B[1] = C[1] - yPrime;
	}
	
	public static boolean intersectCircle(int[] vertix1, int[] vertix2, 
			int[] cPoint, int cRadius)
	{
		// Line formula ax+by+c=0 needed
		int a = vertix1[1] - vertix2[1];
		int b = vertix2[0] - vertix2[0];
		int c = (vertix1[0] * vertix2[1]) - (vertix2[0] * vertix1[1]);
		
		// This calculates shortest distance from line to center of circle
		double distance = (Math.abs(a*cPoint[0] + b*cPoint[1] + c)) / 
				Math.sqrt((a * a) + (b * b));
		
		// Checks if distance from line to circle within the circle's radius
		if (distance <= cRadius)
		{
			// Line intersects!
			return true;
		}
		else 
		{	
			// Didn't intersect
			return false;
		}
	}
	
	public static boolean didItIntersect()
	{
		return (intersectCircle(A, B, circleOrigin, circleRad) ||
				intersectCircle(A, D, circleOrigin, circleRad) ||
				intersectCircle(B, C, circleOrigin, circleRad) ||
				intersectCircle(D, C, circleOrigin, circleRad));
	}
	
}
