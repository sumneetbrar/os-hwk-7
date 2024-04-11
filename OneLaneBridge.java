/**
 * One lane bridge class
 * 
 * @author Sumneet
 */
public class OneLaneBridge extends Bridge{
  private final int bridgeCapacity;

  public OneLaneBridge(int capcity) {
    super(); // call bridge.java
    this.bridgeCapacity = capcity;
  }

  public synchronized void arrive(Car car) throws InterruptedException{
    // if the bridge is full or the car has the opposite direction of the bridge, wait.
    while(bridge.size() >= bridgeCapacity || (bridge.size() > 0 && car.getDirection() != direction)) {
      wait();
    }

    car.setEntryTime(currentTime);
    direction = car.getDirection(); // set the bridge's direction
    bridge.add(car);
    currentTime++;

    System.out.println("Bridge (dir=" + direction + "): " + bridge);
    notifyAll(); // notify all cars that bridge's direction has changed
  };

  public synchronized void exit(Car car) throws InterruptedException {
    // maintain FIFO order
    while(bridge.indexOf(car) != 0) {
      wait();
    }

    bridge.remove(car);
    System.out.println("Bridge (dir=" + direction + "): " + bridge);
    notifyAll(); // notify all cars that bridge's direction has changed
  };
}
