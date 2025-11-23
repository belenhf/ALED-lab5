package es.upm.dit.aled.lab5;

import java.util.LinkedList;
import java.util.Queue;

import es.upm.dit.aled.lab5.gui.Position2D;

/**
 * Extension of Area that maintains a strict queue of the Patients waiting to
 * enter in it. After a Patient exits, the first one in the queue will be
 * allowed to enter.
 * 
 * @author rgarciacarmona
 */
public class AreaQueue extends Area {
	//Nuevo atributo: lista cola
	private Queue<Patient> waitQueue;
	
	//Constructor
	public AreaQueue(String name, int time, int capacity, Position2D position) {
		super(name, time, capacity, position);
		this.waitQueue = new LinkedList<Patient>(); //la creas vacía
		
	}
	
	@Override
	public synchronized void enter(Patient p) {
		System.out.println("Patient " + p.getNumber() + " trying to enter " + this.name);
		this.waiting++;
		//Añado a la cola
		this.waitQueue.add(p);
		//Mantenerlo en wait si no cabe o si no está el primero en la cola
		while(this.numPatients >=  this.capacity || this.waitQueue.peek() != p ) {
			//El profe añade algo más a la traza
			System.out.println("Patient " + p.getNumber() + " waiting for " + this.name + ".Front of the queue?:" + this.waitQueue.peek().equals(p));
			try {
				wait();
			} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
		//borrar de la cola
		this.waitQueue.remove();
		this.waiting--;
		this.numPatients++;
		System.out.println("Patient " + p.getNumber() + " has entered " + this.name);
	}	
	







}


