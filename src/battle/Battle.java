package battle;

import droids.Droid;

import java.util.ArrayList;
import java.util.Random;

public class Battle {
    public static String battleFight(ArrayList<Droid> team1, ArrayList<Droid> team2) {
        StringBuilder data = new StringBuilder();

        int startSizeTeam1 = team1.size();
        int startSizeTeam2 = team2.size();
        boolean isOneVsOne = startSizeTeam1 == 1 && startSizeTeam2 == 1;

        if (isOneVsOne) {
            data.append("Бій 1 vs 1\n");
            data.append("\tУчасник 1: ").append(team1.get(0)).append("\n");
            data.append("\tУчасник 2: ").append(team2.get(0)).append("\n");
        } else {
            data.append("Бій команда 1 vs команда 2\n");
            data.append("\tУчасники команди 1:\n");
            for (Droid droid : team1) {
                data.append("\t\t").append(droid).append("\n");
            }
            data.append("\tУчасники команди 2:\n");
            for (Droid droid : team2) {
                data.append("\t\t").append(droid).append("\n");
            }
        }

        if (!isOneVsOne) {
            data.append("Процес битви:\n");
        }

        int counterRound = 1;
        while (!team1.isEmpty() && !team2.isEmpty()) {
            // Визначення учасників бою на певному етапі.
            int index1 = new Random().nextInt(team1.size());
            Droid droid1 = team1.get(index1);
            int index2 = new Random().nextInt(team2.size());
            Droid droid2 = team2.get(index2);

            if (!isOneVsOne) {
                data.append("\tРаунд ").append(counterRound).append("\n");
                data.append("\t\tУчасник 1: ").append(droid1).append("\n");
                data.append("\t\tУчасник 2: ").append(droid2).append("\n");
            }
            data.append("\t\tПроцес їх битви:").append("\n");

            // Процес бою між визначеними учасниками.
            boolean doHitFirstDroid = new Random().nextBoolean();
            while (droid1.isAlive() && droid2.isAlive()) {
                if (doHitFirstDroid) {
                    data.append("\t\t\tАтакує учасник 1 й наніс удар: ").append(droid1.attack(droid2)).append("\n");
                } else {
                    data.append("\t\t\tАтакує учасник 2 й наніс удар: ").append(droid2.attack(droid1)).append("\n");
                }
                doHitFirstDroid = !doHitFirstDroid;
            }

            // Визначення програвшого (його вилучення і відновлення дефолтних параметрів) і виграшного.
            if (!droid1.isAlive()) {
                team1.get(index1).setMaxHealth();
                team1.remove(index1);
                data.append("\t\t\tРЕЗУЛЬТАТ ПЕРЕМІГ УЧАСНИК 2: ").append(droid2).append("\n");
            }
            if (!droid2.isAlive()) {
                team2.get(index2).setMaxHealth();
                team2.remove(index2);
                data.append("\t\t\tРЕЗУЛЬТАТ ПЕРЕМІГ УЧАСНИК 1: ").append(droid1).append("\n");
            }

            counterRound += 1;
        }

        // Визначення програвшої і виграшної команди
        if (!isOneVsOne) {
            if (team1.isEmpty()) {
                data.append("РЕЗУЛЬТАТ КОМАНДНОЇ БИТВИ: Команда 2 перемогла!").append("\n");
            } else {
                data.append("РЕЗУЛЬТАТ КОМАНДНОЇ БИТВИ: Команда 1 перемогла!").append("\n");
            }
        }

        // Відновлення дефолтних параметрів для дроїдів, які лишилися.
        for (Droid droid : team1) {
            droid.setMaxHealth();
        }
        for (Droid droid : team2) {
            droid.setMaxHealth();
        }

        return data.toString();
    }

}
