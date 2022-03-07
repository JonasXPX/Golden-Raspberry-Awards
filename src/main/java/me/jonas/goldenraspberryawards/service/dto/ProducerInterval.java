package me.jonas.goldenraspberryawards.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProducerInterval {

    public ProducerInterval(final String producer, final int previousWin, final int followingWin) {
        this.producer = producer;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
        this.interval = followingWin - previousWin;
    }

    private String producer;

    private int interval;

    private int previousWin;

    private int followingWin;

    public int getInterval() {
        return followingWin - previousWin;
    }
}
