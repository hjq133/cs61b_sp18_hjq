public class Planet{
	public static double G = 6.67e-11;
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		return Math.sqrt(Math.pow(this.xxPos-p.xxPos,2)+Math.pow(this.yyPos-p.yyPos,2));
	}

	public double calcForceExertedBy(Planet p){
		return Planet.G*(this.mass*p.mass)/Math.pow(this.calcDistance(p),2); 
	}

	public double calcNetForceExertedByX(Planet[] ppp){
		double sumx = 0;
		for(int i =0; i < ppp.length; i++){
			if(this.equals(ppp[i])){
				continue;
			}
			else{
				sumx = this.calcForceExertedBy(ppp[i])*(ppp[i].xxPos-this.xxPos)/this.calcDistance(ppp[i]) + sumx;
			}
		}
		return sumx;
	}

	public double calcNetForceExertedByY(Planet[] ppp){
		double sumy = 0;
		for(int i =0; i < ppp.length; i++){
			if(ppp[i].equals(this)){
				continue;
			}
			else{
				sumy = this.calcForceExertedBy(ppp[i])*(ppp[i].yyPos-this.yyPos)/this.calcDistance(ppp[i]) + sumy;
			}
		}
		return sumy;
	}

	public void update(double dt,double fx,double fy){
		double ax = fx/this.mass;
		double ay = fy/this.mass;
		this.xxVel = this.xxVel + dt*ax;
		this.yyVel = this.yyVel + dt*ay;
		this.xxPos = this.xxVel*dt + this.xxPos;
		this.yyPos = this.yyVel*dt + this.yyPos;
	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}
