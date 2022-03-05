package me.jonas.goldenraspberryawards.controller;

import me.jonas.goldenraspberryawards.service.ProducerService;
import me.jonas.goldenraspberryawards.service.dto.PrizeRange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
@RequestMapping("/producer")
public class ProducerController {

    private final ProducerService service;

    public ProducerController(final ProducerService service) {
        this.service = service;
    }

    @GetMapping("/min-and-max-awards")
    public ResponseEntity<PrizeRange> getMinAndMaxAwards() {
        return ResponseEntity.ok(service.getIntervals());
    }
}
