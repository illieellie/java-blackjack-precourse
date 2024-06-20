package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

import static domain.card.Symbol.ACE;

public class Gamer {
    private static final int GOAL_VALUE = 21;
    private static final int ACE_VALUE_1 = 1;
    private static final int ACE_VALUE_11 = 11;

    final List<Card> cards = new ArrayList<>();
    final double bettingMoney;
    final String name;
    public String getName() {
        return name;
    }

    public Gamer(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    // 게이머들은 아래와 같은 공통 기능을 갖는다
    // 카드를 갖는다.
    public void addCard(Card card) {
        cards.add(card);
    }

    // 카드를 체크한다. // sum을 반환
    public int checkCard(){
        int sum = 0;
        // 자신의 카드 체크
        // this.cards
        boolean hasAce = false;
        for(Card card : cards){
            if(card.getSymbol()==ACE){
                // 가장 마지막에 계산해야할 카드
                hasAce = true;
                continue;
            }
            sum+=card.getSymbol().getScore();
        }
        if(hasAce==true){
            // 1을 더하는게 가까운지 11을 더하는게 가까운지
            sum+=choiceAceValue(sum);
        }
        return sum;
    }

    public int choiceAceValue(int sum){
        int result = 0;
        if(sum+ACE_VALUE_11<=GOAL_VALUE){
            result = ACE_VALUE_11;
        }
        else{
            result = ACE_VALUE_1;
        }
        return result;
    }

    // 모든 카드를 반환한다.
    public List<Card> getAllCards(){
        return cards;
    }
}
