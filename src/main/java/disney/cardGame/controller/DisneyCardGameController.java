package disney.cardGame.controller;

import io.reflectoring.api.MyDecksApi;
import io.reflectoring.model.Card;
import io.reflectoring.model.Deck;
import io.reflectoring.model.UserDeckRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DisneyCardGameController implements MyDecksApi {

	private final Deck deck = new Deck();

	@Override
	public ResponseEntity<Void> indicatesCardsIdToAddAtUserDeck(UserDeckRequest userDeckRequest) {

		List<Long> cardsListIds = userDeckRequest.getCardsList();

		List<Card> cards = findCardsByIds(cardsListIds);

		deck.setCardsList(cards);

		return ResponseEntity.ok().body(null);
	}

	@Override
	public ResponseEntity<Deck> myDecksGet() {
		return ResponseEntity.ok(deck);
	}

	private List<Card> findCardsByIds(List<Long> cardsListIds) {
		return cardsListIds.stream().map(cardId -> {
			Card card = new Card();
			card.setCode(cardId);
			card.setName("Elza");
			card.setDescription("Rainha de Arendelle e possui poderes de gelo");
			card.setEvent("Congela um outro card");
			card.setType(Card.TypeEnum.LIGHT);
			card.setNumber(24);

			return card;
		}).collect(Collectors.toList());
	}
}
