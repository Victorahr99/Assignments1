import java.util.*;

public class ProducerConsumer{
    
    public static void main(String[] args){
        
        ArrayList<Integer> shelf = new ArrayList<Integer>();
        
        System.out.println("Potter 1 (Harry) has started");
        System.out.println("Potter 2 (Beatrix) has started");
        System.out.println("The Packer (Macca) has started");
        
        Thread harry = new Thread(new Craftperson1(shelf));
        Thread beatrix = new Thread(new Craftperson2(shelf));
        Thread macca = new Thread(new Packer(shelf));
        harry.start();
        beatrix.start();
        macca.start();
    }
}

class Craftperson1 implements Runnable{
    
    ArrayList<Integer> shelf = null;
    final int limit = 5;
    int i = 0;
    int counter1 = 0;
	volatile boolean fin = true;
    
    public Craftperson1(ArrayList<Integer> shelf){
        super();
        this.shelf = shelf;
    }
    
    @Override
    public void run(){
		
		int a = 0;
        while(fin == true){
			
			if (a==10){
				fin = false;
			}
			
            try{
                produce1(i++);
                Thread.sleep(400);
            }
            catch(InterruptedException exception){
                
            }
			
			a++;
		}
        
    }
    
    public void produce1(int i) throws InterruptedException{
        
        synchronized(shelf) {
            
			if(counter1 == 10){
                System.out.println("Harry has made 10 pots");
			}
			
			while(counter1 == 10){
                shelf.wait();
            }
        }
        
        synchronized(shelf){
            while(shelf.size() == limit){
                System.out.println("Shelf is full. Waiting to insert...");
                shelf.wait();
            }
        }
        
        synchronized(shelf){
            System.out.println("Harry has made a pot");
            shelf.add(i);
            counter1++;
            System.out.println("Inserting pot. There are now " + shelf.size() + " pots on the shelf");
            System.out.println("Harry has put his pot on the shelf");
            shelf.notify();
        }
    }
}

class Craftperson2 implements Runnable{
    
    ArrayList<Integer> shelf = null;
    final int limit2 = 5;
    int j = 0;
    int counter2 = 0;
	volatile boolean end = true;
	
    
    public Craftperson2(ArrayList<Integer> shelf){
        super();
        this.shelf = shelf;
    }
    
    @Override
    public void run(){
		
		int f = 0;
		
        while(end == true){
			
			if (f==10){
				end =false;
			}
			
            try{
                produce2(j++);
                Thread.sleep(600);
            }
            catch(InterruptedException exception){
                
            }
			f++;
        }
        
    }
    
    public void produce2(int j) throws InterruptedException{
        
        synchronized(shelf) {
            
			if(counter2 == 10){
                System.out.println("Beatrix has made 10 pots");
			}
			
			while(counter2 == 10){
                shelf.wait();
            }
        }
        
        synchronized(shelf){
            while(shelf.size() == limit2){
                System.out.println("Shelf is full. Waiting to insert...");
                shelf.wait();
            }
        }
        
        synchronized(shelf){
            System.out.println("Beatrix has made a pot");
            shelf.add(j);
            counter2++;
            System.out.println("Inserting pot. There are now " + shelf.size() + " pots on the shelf");
            System.out.println("Beatrix has put her pot on the shelf");
            shelf.notify();
        }
    }
}

class Packer implements Runnable{
    
    ArrayList<Integer> shelf = null;
    int counter3 = 0;
	volatile boolean loco = true;
    
    public Packer(ArrayList<Integer> shelf){
        super();
        this.shelf = shelf;
    }
    
    public void run(){
		
		int b = 0;
		
        while(loco == true){
			
			if (b==20){
				loco = false;
			}
			
            try{
                pack();
                Thread.sleep(400);
            }
            catch(InterruptedException exception){
                
            }
			b++;
        }
        
    }
    
    public void pack() throws InterruptedException{
        
        synchronized(shelf) {
            while(counter3 == 20){
                System.out.println("Macca has packed 20 pots");
                shelf.wait();
            }
        }
        
        synchronized(shelf){
            while(shelf.isEmpty()){
                System.out.println("Shelf is empty. Waiting to remove...");
                shelf.wait();
            }
        }
        
        synchronized(shelf){
            shelf.remove(0);
            counter3++;
            System.out.println("Macca has packed a pot.");
            System.out.println("Macca is ready to pack");
            System.out.println("Removing pot. There are now " + shelf.size() + " pots on the shelf");
            shelf.notify();
        }
    }
}
