/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.csic.iiia.planes.patrolling;

import es.csic.iiia.planes.Location;
import es.csic.iiia.planes.Plane;
import es.csic.iiia.planes.Task;
import es.csic.iiia.planes.behaviors.AbstractBehavior;
import es.csic.iiia.planes.behaviors.AbstractBehaviorAgent;
import java.util.List;
import org.omg.CORBA.TIMEOUT;

/**
 *
 * @author Mohammad Reza
 */
public class PatrollingBehavior extends AbstractBehavior<PatPlane> {

    Plane avr;
    List<Task> task;
    List<Location> lt;
    
    
    public PatrollingBehavior(PatPlane agent) {
        super(agent);
        avr=agent;
        task=avr.getTasks();
        Location_Of_Tasks();
        patrolling();
    }
    
    
    
 
    
    
    
    
    

    @Override
    public Class[] getDependencies() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    private void patrolling()
    {
        int i=0;
        for(;;)
        {
            if(avr.getTasks().size()<10)
                avr.addTask(new Task(lt.get(i)));
            if(i<lt.size()-1)
                i++;
            else
                i=0;
        }
    }
    
    
    private void Location_Of_Tasks()
    {
       double total1=task.get(0).getLocation().getX(),total2=task.get(1).getLocation().getX();
       double scale=(task.get(3).getLocation().getX()-task.get(0).getLocation().getX())/10;
       lt.add(new Location(task.get(0).getLocation().getX(), task.get(0).getLocation().getY()));
       lt.add(new Location(task.get(1).getLocation().getX(),task.get(1).getLocation().getY()));
       for(int i=1;i<11;i++)
       {
           total1+=scale;
           total2+=scale;
           lt.add(new Location(total1,calculate0_3(total1)));
           lt.add(new Location(total2, calculate1_2(total2)));
       }
       
    }
    
    
    private double calculate0_3(double x)
    {
        double m=(double)((task.get(3).getLocation().getY()-task.get(0).getLocation().getY())/(task.get(3).getLocation().getX()-task.get(0).getLocation().getX()));
        double y=m*(x-task.get(0).getLocation().getX())+task.get(0).getLocation().getY();
        return y;
    }
    
    
    
    
      private double calculate1_2(double x)
    {
        double m=(double)((task.get(2).getLocation().getY()-task.get(1).getLocation().getY())/(task.get(2).getLocation().getX()-task.get(1).getLocation().getX()));
        double y=m*(x-task.get(1).getLocation().getX())+task.get(1).getLocation().getY();
        return y;
    }
}
