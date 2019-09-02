
import java.util.Iterator;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int count = 0;

    private void resize(int capcacity){

        Item[] temp = (Item[])new Object[capcacity];
        for(int i = 0; i < count; i++)
            temp[i] = queue[i];
        queue = temp;

    }

    public RandomizedQueue(){
        queue = (Item[]) new Object[1];
    }

    public boolean isEmpty(){return count == 0;}
    public int size(){return count;}


    public void enqueue(Item item){
        if(item == null) throw new IllegalArgumentException("IllegalArgumentException");
        if (count == queue.length) resize(queue.length*2);
        queue[count] = item;
        count++;
    }

    public Item dequeue(){
        if(count == 0) throw new NoSuchElementException("NoSuchElementException");
        int index=StdRandom.uniform(count);
        Item item = queue[index];
        queue[index] = queue[count - 1];
        queue[count -1] = null;
        count--;
        if(count>0&&count<=queue.length/4){
            resize(queue.length/2);
        }
        return item;

    }

    public Item sample(){
        if(count == 0) throw new NoSuchElementException("NoSuchElementException");
        int index=StdRandom.uniform(count);
        return queue[index];
    }

    public Iterator<Item> iterator(){
        return new RandomizedQueueIterator();
    }


    private class RandomizedQueueIterator implements Iterator<Item>{

        private int temppointer = count-1;
        private Item[] temp_queue;

        public RandomizedQueueIterator(){
            temp_queue =(Item []) new Object[count];

            for(int i=0;i<count;i++)
                temp_queue[i] = queue[i];

            StdRandom.shuffle(temp_queue);
        }

        public boolean hasNext() {
            return temppointer>=0;
        }

        public Item next(){
            Item item = temp_queue[temppointer];
            temppointer--;
            return item;
        }

        public void remove(){ throw new UnsupportedOperationException("UnsupportedOperationException"); }

    }

     public static void main(String[] args){
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(StdIn.readString());
        randomizedQueue.enqueue(StdIn.readString());
        randomizedQueue.enqueue(StdIn.readString());
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.enqueue(StdIn.readString());
        randomizedQueue.dequeue();
        StdOut.println(randomizedQueue.count);

         for (String s: randomizedQueue
              ) {
             StdOut.println(s);
         }
   }
}
