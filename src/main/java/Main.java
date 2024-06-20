import domain.InputView;
import domain.OutputView;
import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Gamer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static domain.OutputView.printMent5;

public class Main {

    public static final int GOAL_VALUE = 21;
    public static void main(String[] args){
        // 카드 준비
        List<Card> cards = new ArrayList<>(CardFactory.create());
        Collections.shuffle(cards); // 한번 섞고
        // 참가자 입력
        OutputView.printMent1();
        List<String> temp = InputView.getGamers();
        // 딜러, 플레이어 생성
        List<Gamer> gamers = new ArrayList<>(); // 게임 참가자들
        gamers.add(new Dealer("딜러", 0));
        for(int i = 0; i<temp.size(); i++){
            gamers.add(new Player(temp.get(i),10000));
        }
        // 카드를 랜덤으로 2장 받는다
        for(int i = 0, j=0; i<gamers.size()*2; i++, j++){
            if(j==gamers.size()){j=0;}
            gamers.get(j).addCard(cards.get(j));
            cards.remove(j);
        }
        // 카드 출력
        for(int i = 0; i<gamers.size(); i++){
            OutputView.printMent5(gamers.get(i).getName(), gamers.get(i).getAllCards());
        }

//
//        while(!cards.isEmpty()){
//
//
//        }


    }
}
