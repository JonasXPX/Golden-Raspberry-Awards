package me.jonas.goldenraspberryawards.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class PrizeRange {
    private Collection<ProducerInterval> max;
    private Collection<ProducerInterval> min;
}
