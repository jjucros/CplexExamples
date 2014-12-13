import ilog.concert.*;
import ilog.cplex.*;


public class App {
	
	public static void main(String args[]){
		model1();
		
	}
	
	public static void model1(){
		try{
			//Se crea una copia del Engine
			IloCplex cplex = new IloCplex();
			
			//Se crean un par de variables
			IloNumVar x = cplex.numVar(0, Double.MAX_VALUE);
			IloNumVar y = cplex.numVar(0, Double.MAX_VALUE);
			
			//Se crea una expresion para la F.O.
			IloLinearNumExpr objective = cplex.linearNumExpr();
			objective.addTerm(0.12, x);
			objective.addTerm(0.15, y);
			
			//Se define la expresión "objective" como la función obejtivo y se indica que es un problema de minimizacion
			cplex.addMinimize(objective);
			
			// Se definen las restricciones
			cplex.addGe(cplex.sum(cplex.prod(60, x),cplex.prod(60, y)) , 300);
			cplex.addGe(cplex.sum(cplex.prod(12, x),cplex.prod(6, y)) , 36);
			cplex.addGe(cplex.sum(cplex.prod(10, x),cplex.prod(30, y)) , 90);
			
			
			//Se resuelve el problema
			if(cplex.solve())
			{
				System.out.println("objective =\t "+ cplex.getObjValue());
				System.out.println("x =\t"+ cplex.getValue(x));
				System.out.println("y =\t"+ cplex.getValue(y));
				
			}
			else{
				System.out.println("Something goes wrong");
			}
			
			
		}catch(IloException exc)
		{
			exc.printStackTrace();
		}
		
	}

}
