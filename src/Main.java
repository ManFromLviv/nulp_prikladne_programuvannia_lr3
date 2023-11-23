import battle.Battle;
import droids.Droid;
import droids.type.AirDroid;
import droids.type.AttackDroid;

import java.util.ArrayList;

import static menu.Menu.runMenu;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static String testOneVsOne() {
        ArrayList<Droid> team1 = new ArrayList<>();
        ArrayList<Droid> team2 = new ArrayList<>();

        team1.add(new AttackDroid("0001at"));
        team2.add(new AttackDroid("0001at"));
        return Battle.battleFight(team1, team2);
    }

    public static String testCommandVsCommnand() {
        ArrayList<Droid> team1 = new ArrayList<>();
        ArrayList<Droid> team2 = new ArrayList<>();

        team1.add(new AttackDroid("0001at"));
        team1.add(new AttackDroid("0002at"));
        team1.add(new AttackDroid("0003at"));
        team1.add(new AttackDroid("0004at"));
        team1.add(new AttackDroid("0005at"));
        team1.add(new AttackDroid("0006at"));
        team1.add(new AttackDroid("0007at"));
        team2.add(new AttackDroid("1000at"));
        team2.add(new AttackDroid("2000at"));
        team2.add(new AttackDroid("3000at"));
        team2.add(new AttackDroid("4000at"));
        team2.add(new AirDroid("5000ai"));

        return Battle.battleFight(team1, team2);
    }

    public static void testMenu() throws InterruptedException {
        runMenu();
    }
    public static void main(String[] args) throws InterruptedException {
//        System.out.println(testOneVsOne());
//        System.out.println(testCommandVsCommnand());
        testMenu();
    }
}