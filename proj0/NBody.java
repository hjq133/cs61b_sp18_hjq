public class NBody{
	public static double readRadius(String args){  //这里为什么要加上static
		In in = new In(args);
		int n = in.readInt(); 
		double radius = in.readDouble();
		return radius;
	}

	public static void main(String[] args){
		int i = 0;
		int waitTimeMilliseconds = 10;
		double time = 0.0;
		double T = Double.valueOf(args[0]);
		double dt = Double.valueOf(args[1]);
		String filename = args[2];
		double radius = NBody.readRadius(filename);
		StdDraw.setScale(-radius,radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		Planet[] p = readPlanets(filename);
		for(i = 0; i < p.length; i++){
			p[i].draw();
			//StdDraw.show();
		}

		double[] xForces = new double[p.length];
		double[] yForces = new double[p.length];

		StdDraw.enableDoubleBuffering();
		for(time = 0.0; time < T; time = dt + time){
			for(i = 0; i < p.length; i++){
				xForces[i] = p[i].calcNetForceExertedByX(p);
				yForces[i] = p[i].calcNetForceExertedByY(p);
			}
			for(i = 0; i< p .length; i++){
				p[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(i = 0; i < p.length; i++){
				p[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(waitTimeMilliseconds);
		}
		StdOut.printf("%d\n", p.length);
		StdOut.printf("%.2e\n", radius);
		for(i = 0; i < p.length; i++){
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", p[i].xxPos, p[i].yyPos, p[i].xxVel, p[i].yyVel, p[i].mass, p[i].imgFileName);
		}
	}

	public static Planet[] readPlanets(String args){
		int i = 0;
		In in = new In(args);
		int n = in.readInt();
		Planet[] allPlanets = new Planet[n];  //注意这里初始化的对象数组是空的，需要挨个儿初始化
		double radius = in.readDouble(); 
		for(i = 0; i < n; i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			allPlanets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName); //挨个儿初始化
		}
		return allPlanets;
	}
	//public static 代表的是静态方法，可以不通过创建对象而直接访问
	//直接public代表的是非静态方法，需要new一个对象进行访问
}