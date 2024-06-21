package domain.user;

import domain.card.Card;
import domain.card.CardFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    public static final int GOAL_VALUE = 21;
    public static final int HEAD = 0;
    List<Card> cards;

    public Game() {
        // 카드 준비
        prepareCard();
    }

    private void prepareCard() {
        cards = new ArrayList<>(CardFactory.create());
        Collections.shuffle(cards); // 한번 섞기
    }

    public void addCardAllUsers(List<Gamer> gamers) {
    // 유저 전체에게 카드 한장을 돌린다.
        for (int i = 0; i < gamers.size() ; i++) {
            Gamer gamer = gamers.get(i); // user
            gamer.addCard(cards.get(i));
            cards.remove(HEAD);
        }
    }

    public void addCardOneUser(Gamer gamer) {
        // 유저 한명에게 카드를 돌린다.
        gamer.addCard(cards.get(HEAD));
        cards.remove(HEAD);
    }


    public int[] makeResult(List<Gamer> gamers) {
        // 결과를 낸다
        int[] result = new int[gamers.size()];
        int dealerScore = gamers.get(HEAD).getResultScore();

        for(int i = 1; i<gamers.size(); i++){
            int playerScore = gamers.get(i).getResultScore();

            // GOAL_VALUE 를 초과하는 사람은 진다
            // 그게 아니라면 더 숫자가 골밸류에 가까운사람이 이김

            if(dealerScore>GOAL_VALUE){
                // 딜러 루즈
                result[i]++;
            } else if (playerScore>GOAL_VALUE) {
                // 플레이어 루즈
                result[HEAD]++;
            } else if (dealerScore>playerScore) {
                // 플레이어 루즈
                result[HEAD]++;
            } else if (dealerScore<playerScore) {
                // 딜러 루즈
                result[i]++;
            }
        }
        return result;
    }

    public void setScoreAllUsers(List<Gamer> gamers) {
        for (int i = 0; i < gamers.size(); i++) {
            Gamer gamer = gamers.get(i);
            gamer.setResultScore(gamer.checkCard());
        }
    }
}
