package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Gamer{
    private final double bettingMoney;
    private final String name;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }



//    public void addCard(Card card) {
//        cards.add(card);
//    }

    // TODO 추가 기능 구현

}
