package com.game.cards.client;

import com.game.cards.dto.DeckResponseDTO;
import com.game.cards.dto.DrawResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(
        name = "ApiDeck",
        url = "${feign-client.url.api}")
public interface ApiDeckFeignClient {

    @GetMapping(value = "/deck/new/shuffle/", produces = "application/json")
    ResponseEntity<DeckResponseDTO> createOrder(@RequestParam(name = "deck_count",
            required = false, defaultValue = "1") Integer deckCount);

    @GetMapping(value = "/deck/{deck_id}/draw/", produces = "application/json")
    ResponseEntity<DrawResponseDTO> drawCards(@RequestParam(name = "count") Integer count,
                                              @RequestParam(name = "deck_id") String deckId);

}
