package com.phillips.noanalyticsdev.service.cms;

import com.phillips.noanalyticsdev.util.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class CmsService {
    @Autowired
    WebClient cmsWebClient;

    public Flux<String> getAllShops() {
        return cmsWebClient.get()
                .uri("/webshops")
                .retrieve()
                .bodyToFlux(String.class);
    }

    public List<ProductItem> getAllProducts() {
        Mono<List<ProductItem>> response = cmsWebClient.get()
                .uri("/no-beats-products")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProductItem>>() {});
        List<ProductItem> items = response.block();

        return items;
    }

    public List<ProductItem> getRandomAmountProducts(int amount) {
        Mono<List<ProductItem>> response = cmsWebClient.get()
                .uri("/no-beats-products")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProductItem>>() {});
        List<ProductItem> items = response.block();
        List<ProductItem> randomAmountItems = new ArrayList<>();


        int i = 0;
        while (i < amount) {
            int rand = (int) (Math.random() * items.size() + 0);
            randomAmountItems.add(items.get(rand));
            items.remove(items.get(rand));
            i++;
        }


        return randomAmountItems;
    }

    public List<ProductItem> getProductsByTag(String tag) {
        Mono<List<ProductItem>> response = cmsWebClient.get()
                .uri("/no-beats-products?tag=" + tag)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProductItem>>() {});
        List<ProductItem> items = response.block();

        return items;
    }

    public Flux<String> getAllAds() {
        return cmsWebClient.get()
                .uri("/no-beats-ads")
                .retrieve()
                .bodyToFlux(String.class);
    }

    public Flux<String> getAdsByTag(String tag) {
        return cmsWebClient.get()
                .uri("/no-beats-ads?tag=" + tag)
                .retrieve()
                .bodyToFlux(String.class);
    }

    public Flux<String> getAdsByAdType(String adType) {
        return cmsWebClient.get()
                .uri("/no-beats-ads?adType=" + adType)
                .retrieve()
                .bodyToFlux(String.class);
    }
}
