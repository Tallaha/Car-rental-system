package gui;



public class Meny {
	private Tekstgrensesnitt ui;



	public Meny() {
		
		ui = new Tekstgrensesnitt();
	}

	public void start() {
		while (true) {
			int choice = ui.showMainMenu();

			switch (choice) {
			case 1:
				ui.searchAndMakeReservation();
				break;

			case 2:
				ui.searchAndReturnCar();
				break;

			case 3:
				ui.exit();
				break;

			default:
				System.out.println("Error: The input is not a valid choice");
				break;
			}
		}
	}
}