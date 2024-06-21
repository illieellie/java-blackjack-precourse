import domain.InputView;
import domain.OutputView;
import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Game;
import domain.user.Gamer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

    public static final int HEAD = 0;
    public static final int DEALER_RULE = 16;

    public static void main(String[] args) {

        Game game = new Game();

        // 참가자 입력
        OutputView.printMent1();
        List<String> temp = InputView.getGamers();
        // 딜러, 플레이어 생성
        List<Gamer> gamers = new ArrayList<>(); // 게임 참가자들
        gamers.add(new Dealer("딜러", 0));
        for (int i = 0; i < temp.size(); i++) {
            gamers.add(new Player(temp.get(i), 0));
        }

        // 모든 유저는 카드를 랜덤으로 2장씩 받는다
        for (int i = 0; i < 2; i++) {
            game.addCardAllUsers(gamers);
        }

        // 카드 출력
        for (int i = 0; i < gamers.size(); i++) {
            OutputView.printMent5(gamers.get(i).getName(), gamers.get(i).getAllCards());
        }
        // 돌아가면서 카드를 받을지 물어보며 카드를 받는다
        // 플레이어
        for (int i = 1; i < gamers.size(); i++) {
            Gamer player = gamers.get(i);
            OutputView.printMent3(player.getName());
            String answer = InputView.getCard();
            if (answer.equals("y")) {
                game.addCardOneUser(player);
            }
            OutputView.printMent5(player.getName(), player.getAllCards());
        }
        // 딜러는 가지고 있는 카드합이 16이하이면 한장의 카드를 더 받는다.
        Gamer dealer = gamers.get(HEAD);
        int sum = dealer.checkCard();
        if (sum <= DEALER_RULE) {
            game.addCardOneUser(dealer);
            OutputView.printMent6();
        }

        // set score
        game.setScoreAllUsers(gamers);

        // 결과 출력
        for (int i = 0; i < gamers.size(); i++) {
            Gamer gamer = gamers.get(i);
            OutputView.printResultDetail(gamer.getName(), gamer.getAllCards(), gamer.getResultScore());
        }

        int[] result = game.makeResult(gamers);

        // 최종 승패 출력 
        OutputView.printResultSummary(gamers, result);
    }
}
