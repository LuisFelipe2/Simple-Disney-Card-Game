package disney.cardGame.controller;

import com.google.gson.Gson;
import io.reflectoring.api.CardsApi;
import io.reflectoring.model.CardPageable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class CardController implements CardsApi {

	@Value("${openapi.disneyCardGame.url-mock-card}")
	private String url;

	@Override
	public ResponseEntity<CardPageable> getAllCards(Integer page, Integer size, String sort) {
		try {
			URL url = new URL(this.url);
			Path path = Paths.get(url.toURI());
			String json = Files.readString(path);

			CardPageable cardPageable = new Gson().fromJson(json, CardPageable.class);

			return ResponseEntity.ok(cardPageable);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();

			return (ResponseEntity<CardPageable>) ResponseEntity.internalServerError();
		}
	}
}
