package gameProject;

public class AIPlayer {

	public static void main(String[] args) {
		int AIBaseHealth = 1000;
		int AIMoney = 0;
		int AIChoice = (int) (Math.random() * 10);
		int AICharacterChoice = (int) (Math.random() * 3);

		if (AIChoice != 5)
		{
			if (AICharacterChoice == 1 && AIMoney >= 30)
			{
				AIMoney = AIMoney - 30;

			}
			else if (AICharacterChoice == 2 && AIMoney >= 80)
			{
				AIMoney = AIMoney - 80;

			} 
			else if (AICharacterChoice == 3 && AIMoney >= 250)
			{
				AIMoney = AIMoney - 250;

			}
		}
		else 
		{
			if (AICharacterChoice == 1 && AIMoney >= 100)
			{
				AIMoney = AIMoney - 100;

			}
			else if (AICharacterChoice == 2 && AIMoney >= 200)
			{
				AIMoney = AIMoney - 200;

			}
			else if (AICharacterChoice == 3 && AIMoney >= 300)
			{
				AIMoney = AIMoney - 300;

			}
		}
	}
}