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


public class Main {

    public static final int GOAL_VALUE = 21;

    public static void main(String[] args) {
        // 카드 준비
        List<Card> cards = new ArrayList<>(CardFactory.create());
        Collections.shuffle(cards); // 한번 섞고
        // 참가자 입력
        OutputView.printMent1();
        List<String> temp = InputView.getGamers();
        // 딜러, 플레이어 생성
        List<Gamer> gamers = new ArrayList<>(); // 게임 참가자들
        gamers.add(new Dealer("딜러", 0));
        for (int i = 0; i < temp.size(); i++) {
            gamers.add(new Player(temp.get(i), 0));
        }
        // 카드를 랜덤으로 2장 받는다
        for (int i = 0, j = 0; i < gamers.size() * 2; i++, j++) {
            if (j == gamers.size()) {
                j = 0;
            }
            gamers.get(j).addCard(cards.get(j));
            cards.remove(j);
        }
        // 카드 출력
        for (int i = 0; i < gamers.size(); i++) {
            OutputView.printMent5(gamers.get(i).getName(), gamers.get(i).getAllCards());
        }
        // 돌아가면서 카드를 받을지 물어보며 카드를 받는다
        int head = 0;
        // 플레이어
        for (int i = 1; i < gamers.size(); i++) {
            Gamer gamer = gamers.get(i);
            OutputView.printMent3(gamer.getName());
            String answer = InputView.getCard();
            if (answer.equals("y")) {
                gamer.addCard(cards.get(head));
            }
            cards.remove(head);
            OutputView.printMent5(gamer.getName(), gamer.getAllCards());
        }
        // 딜러는 가지고 있는 카드합이 16이하이면 한장의 카드를 더 받는다.
        Gamer dealer = gamers.get(head);
        int sum = dealer.checkCard();
        if (sum <= 16) {
            dealer.addCard(cards.get(head));
            cards.remove(head);
            OutputView.printMent6();
        }
        // 결과 출력


    }
}
