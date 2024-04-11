/**
 * Runs all threads
 * 
 * @author Sumneet
 */

public class BridgeRunner {

	public static void main(String[] args) {

		// check command line inputs
		if(args.length != 2) {
			System.out.println("Usage: java BridgeRunner <bridge limit> <num cars>");
			return;
		}

		int bridgeCapacity = Integer.parseInt(args[0]);
		int numCars = Integer.parseInt(args[1]);

		if(bridgeCapacity <= 0 || numCars <= 0) {
			System.out.println("Error: bridge limit and/or num cars must be positive.");
			return;
		}

		// instantiate the bridge
		OneLaneBridge bridge = new OneLaneBridge(bridgeCapacity);
		
		// allocate space for threads
		Thread[] carThreads = new Thread[numCars];

		// start then join the threads
		for (int i = 0; i < numCars; i++) {
			Car car = new Car(i, bridge);
			carThreads[i] = new Thread(car);
			carThreads[i].start();
		}

		for (int i = 0; i < numCars; i++) {
			try {
				carThreads[i].join();
			}
			catch (InterruptedException e) {
				System.out.println("A car thread was interrupted.");
			}
		}

		System.out.println("All cars have crossed!!");
	}

}