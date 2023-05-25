package WorldPackage;

import MainClassesAndOther.MonsterUnit;
import MainClassesAndOther.PlayerUnit;

public class Battle {
    int countHeroHit = 0;
    int countHeroDamage = 0;
    int countHeroMiss = 0;
    int countMonsterHit = 0;
    int countMonsterDamage = 0;
    int countMonsterMiss = 0;
    boolean heroIsWin = true;
    PlayerUnit playerUnit;
    MonsterUnit monsterUnit;

    public Battle(int hHits, int hDamage, int hMiss,
                  int mHits, int mDamage, int mMiss,
                  boolean heroIsWin, PlayerUnit playerHero, MonsterUnit monster) {
        this.countHeroHit = hHits;
        this.countHeroDamage = hDamage;
        this.countHeroMiss = hMiss;
        this.countMonsterHit = mHits;
        this.countMonsterDamage = mDamage;
        this.countMonsterMiss = mMiss;
        this.playerUnit = playerHero;
        this.monsterUnit = monster;
        this.heroIsWin = heroIsWin;
    }
    public String toStringWin() {
        return "The battle results: \n" +
                "The " + playerUnit.getClassName() + " \"" + playerUnit.getName() + "\" hits " + countHeroHit + " times and deals " + countHeroDamage + " damage.\n" +
                "The " + monsterUnit.getClassName() + " hits " + countMonsterHit + " times and deals " + countMonsterDamage + " damage.\n" +
                "\"" + playerUnit.getName() + "\" take " + monsterUnit.getAwardExp() + " exp, and " + monsterUnit.getAwardGold() + " gold.\n" +
                "Hit points = " + playerUnit.getCurrentHP() + "/" + playerUnit.getMaxHP() + ".\n" +
                "Experience = " + playerUnit.getCurrentExp() + "/" + playerUnit.getRequiredExp() + ".";
    }

    public String toStringLose() {
        return "The battle results: \n" +
                "The " + playerUnit.getClassName() + " \"" + playerUnit.getName() + "\" hits " + countHeroHit + " times and deals " + countHeroDamage + " damage. Missed " + countHeroMiss + " times.\n" +
                "The " + monsterUnit.getClassName() + " hits " + countMonsterHit + " times and deals " + countMonsterDamage + " damage. Missed " + countMonsterMiss + " times.\n" +
                "Hit points = " + playerUnit.getCurrentHP() + "/" + playerUnit.getMaxHP() + ".\n" +
                "Experience = " + playerUnit.getCurrentExp() + "/" + playerUnit.getRequiredExp() + ".\n" +
                "\"" + playerUnit.getName().toUpperCase() + "\" IS DEAD.";
    }


    public static Battle startTheBattle(PlayerUnit playerHero, MonsterUnit monster) {

        int countHitsOfHero = 0;
        int countOfHeroDamage = 0;
        int countHeroMiss = 0;

        int countHitsOfMonster = 0;
        int countOfMonsterDamage = 0;
        int countMonsterMiss = 0;
        boolean heroIsWin = true;

        while (true) {
            if (playerHero.getHitOrMiss()) {
                int hitDamage = playerHero.getHitDamage();

                countHitsOfHero++;
                countOfHeroDamage += hitDamage;

                monster.takeDamage(hitDamage);
                if (monster.getCurrentHP() <= 0) {
                    heroIsWin = true;
                    playerHero.takeExp(monster.getAwardExp());
                    playerHero.plusGold(monster.getAwardGold());
                    break;
                }
            } else countHeroMiss++;

            if (monster.getHitOrMiss()) {
                int hitDamage = monster.getHitDamage();

                countHitsOfMonster++;
                countOfMonsterDamage += hitDamage;

                playerHero.takeDamage(hitDamage);
                if (playerHero.getCurrentHP() <= 0) {
                    heroIsWin = false;
                    break;
                }
            } else countMonsterMiss++;
        }
        return new Battle(countHitsOfHero, countOfHeroDamage, countHeroMiss,
                countHitsOfMonster, countOfMonsterDamage, countMonsterMiss,
                heroIsWin, playerHero, monster);
    }
}
